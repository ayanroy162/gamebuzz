package com.TuornamentOrganizer.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.TuornamentOrganizer.Dto.GameDto;
import com.TuornamentOrganizer.Dto.TournamentDto;
import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.IndividualGame;
import com.TuornamentOrganizer.Model.Tournament;
import com.TuornamentOrganizer.Repository.GamerRepo;
import com.TuornamentOrganizer.Repository.TournamentRepo;

@Service
public class TournamentService {

	@Autowired
	private TournamentRepo tournamentRepo;
	
	@Autowired
	private GamerRepo gamerRepo;
	
	@Autowired
	private IndividualGameService individualGameService;

	public TournamentDto getTournamentDto(final Tournament tournament) {
		TournamentDto dto = new TournamentDto();
		List<GameDto> listOfGames = new ArrayList<>();
		List<Game> games = tournament.getGames();
		if(!CollectionUtils.isEmpty(games)) {
			games.stream().forEach(i -> {
				GameDto gdto = new GameDto();
				gdto.setName(i.getName());
				listOfGames.add(gdto);
			});
		}
		dto.setGames(listOfGames);
		dto.setId(tournament.getId());
		return dto;
	}
	
	public List<TournamentDto> getAllTournamentDtos() {
		List<TournamentDto> dtos = new ArrayList<>();
		List<Tournament> tournament = tournamentRepo.findAll();
		if (!CollectionUtils.isEmpty(tournament)) {
			tournament.stream().forEach(i -> {
				TournamentDto dto = new TournamentDto();
				dto.setId(i.getId());
				dto.setTournamentName(i.getTournamentName());
				if (!CollectionUtils.isEmpty(i.getGames())) {
					List<GameDto> listOfGames = new ArrayList<>();
					i.getGames().stream().forEach(f -> {
						GameDto gdto = new GameDto();
						gdto.setName(f.getName());
						gdto.setRules(f.getName());
						gdto.setId(f.getId());
						gdto.setNumberOfRounds(f.getTotaNumberOfRounds());
						gdto.setRounds(f.getRounds());
						listOfGames.add(gdto);
					});
					dto.setGames(listOfGames);
				}
				dtos.add(dto);
			});
		}
		return dtos;
	}
	
	
	public void addNewTournament(final TournamentDto tournamentDto, final String[] selectedIndividualGames) {
		List<Tournament> allTournaments = tournamentRepo.findAll();
		
		if(!CollectionUtils.isEmpty(allTournaments)) {
		Optional<Tournament> exsistingTournament = allTournaments.stream()
				.filter(i -> i.getTournamentName().equalsIgnoreCase(tournamentDto.getTournamentName())).findAny();
		if(!exsistingTournament.isPresent()) {
			registerNewTournament(tournamentDto,selectedIndividualGames);
		}else {
			/* throw some error */
		}
		}else {
			registerNewTournament(tournamentDto,selectedIndividualGames);
		}
	}
	
	private void registerNewTournament(final TournamentDto tournamentDto, final String[] selectedIndividualGames) {
		Tournament tournament = new Tournament();
		final String date = new Date().toString();
		tournament.setTournamentName(tournamentDto.getTournamentName());
		tournament.setTournamentCreatedate(date);
		tournamentRepo.save(tournament);

		Tournament tournament1 = tournamentRepo.getTournamentByTournamentName(tournamentDto.getTournamentName(), date);
		List<IndividualGame> games = new ArrayList<>();
		for (String s : selectedIndividualGames) {
			Optional<IndividualGame> game = individualGameService.getIndividualGame(Long.parseLong(s));
			games.add(game.get());
		}
		games.stream().forEach(i -> {
			Game game = new Game();
			game.setName(i.getGameType());
			game.setTournament(tournament1);
			gamerRepo.save(game);
		});
	}
	
	public void addNewGameInTournament(final TournamentDto tournamentDto) {
		if (0 != tournamentDto.getId()) {
			Tournament tournament = tournamentRepo.getReferenceById(tournamentDto.getId());
			List<GameDto> gameDtos = tournamentDto.getGames();
			List<Game> games = tournament.getGames();
			gameDtos.forEach(i -> {
				Game game = new Game();
				game.setName(i.getName());
				game.setRules(i.getRules());
				games.add(game);
			});
			tournament.setGames(games);
			tournamentRepo.save(tournament);
		}
	}
	
	public List<Tournament> getAllTournaments(){
		return tournamentRepo.findAll();
	}

	public Optional<Tournament> getIndividualTournamet(String tournamentId) {
		return tournamentRepo.findById(Long.parseLong(tournamentId));
	}
}
