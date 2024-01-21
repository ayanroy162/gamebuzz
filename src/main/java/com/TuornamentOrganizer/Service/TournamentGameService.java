package com.TuornamentOrganizer.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.TuornamentOrganizer.Dto.GameDto;
import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.Round;
import com.TuornamentOrganizer.Repository.GamerRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class TournamentGameService {
	
	@Autowired
	private GamerRepo gamerRepo;

	public GameDto getGameDetails(final String tournamentId, final String gameId, final String roundNumber) {
		GameDto dto = null;
		if (StringUtils.isNotEmpty(tournamentId) && StringUtils.isNotEmpty(gameId)) {
			Optional<Game> optionalGame = gamerRepo.findById(Long.parseLong(gameId));
			if (optionalGame.isPresent()) {
				Game game = optionalGame.get();
				dto = new GameDto();
				dto.setId(game.getId());
				dto.setName(game.getName());
				if (!CollectionUtils.isEmpty(game.getPlayer())) {
					dto.setNumberOfPlayers(String.valueOf(game.getPlayer().size()));
				}
				dto.setRules(game.getRules());
				dto.setRounds(null);
				dto.setRoundGenerated(false);
				dto.setActive(game.isActive());
				if (!CollectionUtils.isEmpty(game.getRounds())) {
					int longRoundNumber = Integer.parseInt(roundNumber)!=0 ? Integer.parseInt(roundNumber) : 1;
					Round round = game.getRounds().stream().filter(i -> i.getRoundnumber()== longRoundNumber)
							.findAny().orElse(null);
					if (null != round) {
						List<Round> rounds = new ArrayList<>(Arrays.asList(round));
						dto.setRounds(rounds);
					}
					dto.setRoundGenerated(true);
					dto.setNumberOfRounds(String.valueOf(game.getRounds().size()));
				}

			}
		} 
		return dto;
	}
	

}
