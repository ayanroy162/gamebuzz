package com.TuornamentOrganizer.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.comparator.Comparators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.TuornamentOrganizer.Dto.GameDto;
import com.TuornamentOrganizer.Dto.LossingPlayerDto;
import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.Player;
import com.TuornamentOrganizer.Model.PlayingPlayer;
import com.TuornamentOrganizer.Model.Round;
import com.TuornamentOrganizer.Model.Tournament;
import com.TuornamentOrganizer.Repository.GamerRepo;
import com.TuornamentOrganizer.Repository.PlayingPlayerRepo;
import com.TuornamentOrganizer.Repository.RoundRepo;

import jakarta.transaction.Transactional;

@Service
public class RoundService {
	@Autowired
	private RoundRepo roundRepo;
	@Autowired
	private PlayingPlayerRepo playingPlayerRepo;
	@Autowired
	private GamerRepo gamerRepo;

	public void determindTotalNumberOfRounds(final Tournament tournament) {
		if (!tournament.isActive()) {
			tournament.getGames().stream().forEach(game -> {
				if (!CollectionUtils.isEmpty(game.getPlayer())) {
					int totalNumberOfPlayers = game.getPlayer().size();
					int totalNumberOfRoundsToBePlayed= calculateRounds(totalNumberOfPlayers);
					game.setTotaNumberOfRounds(String.valueOf(totalNumberOfRoundsToBePlayed));
					gamerRepo.save(game);
				}
			});
		}
	}
	
	private int calculateRounds(int numberOfPlayer) {
		int rounds = 0;
		while (numberOfPlayer > 1) {
			numberOfPlayer = (numberOfPlayer + 1) / 2;
			rounds++;
		}
		return rounds;
	}
	
	private int calculateNumberOfTeamsForEachRound(int numberOfPlayer) {
		int teamsForRound = 0;
		if (numberOfPlayer % 2 == 0) {
			teamsForRound = numberOfPlayer / 2;
		} else {
			teamsForRound = (numberOfPlayer + 1) / 2;
		}
		return teamsForRound;
	}
	
	@Transactional
	public void generateRounds(final String gameId) {
		Optional<Game> optionalGame = gamerRepo.findById(Long.parseLong(gameId));
		if (optionalGame.isPresent()) {
			Game game = optionalGame.get();
			int totalNumberOfPlayers = game.getPlayer().size();
			int totalNumberOfRounds = calculateRounds(totalNumberOfPlayers);
			List<Player> players = game.getPlayer();
			int numberOfPlayerAfterEachRound = totalNumberOfPlayers;
			for (int roundNumber = 1; roundNumber <= totalNumberOfRounds; roundNumber++) {
				int teamsForTheRound = calculateNumberOfTeamsForEachRound(numberOfPlayerAfterEachRound);
				Round round = new Round();
				round.setRoundnumber(roundNumber);
				round.setGame(game);
				round.setTotalNoumberOfTeams(teamsForTheRound);
				int teamCounter = 0;
				for (int teamNumber = 1; teamNumber <= teamsForTheRound; teamNumber++) {
					teamCounter+=1;
					PlayingPlayer team = new PlayingPlayer();
					team.setTeamNumber(teamCounter);
					if (roundNumber == 1) {
						assignPlayerToTeam(team,players);
					}
					team.setRound(round);
					round.getPlayingPlayer().add(team);
				}
				game.getRounds().add(round);
				game.setRoundGenerated(true);
				numberOfPlayerAfterEachRound = teamsForTheRound;
			}
			gamerRepo.save(game);
		}
	}
	
	
	private void assignPlayerToTeam(PlayingPlayer team, List<Player> players) {
		if (players.size() != 0) {
			for (int p = 0; p < 1; p++) {
				team.setPlayer1(players.get(p).getPhoneNumber());
				if (players.size() == 1) {
					team.setPlayer2("DUMMY_PLAYER");
				}else {
					team.setPlayer2(players.get(p + 1).getPhoneNumber());
					players.remove(p + 1);
				}
				players.remove(p);
			}
		}
	}
	
	@Transactional
	public void setWinningPlayer(final String playerId, final String winingPlayer, final String player1score, final String player2score) {
		Optional<PlayingPlayer> playingPlayerOp = playingPlayerRepo.findById(Long.parseLong(playerId));
		if(playingPlayerOp.isPresent()) {
			PlayingPlayer pl = playingPlayerOp.get();
			final String lossePlayer = winingPlayer.equalsIgnoreCase(pl.getPlayer1()) ? pl.getPlayer2() : pl.getPlayer1();
			pl.setWinPlayer(winingPlayer);
			pl.setLossPlayer(lossePlayer);
			pl.setPlayer1Score(player1score);
			pl.setPlayer2Score(player2score);
			playingPlayerRepo.save(pl);
			setWinningPlayerToTheNextRound(pl,winingPlayer);
			if(pl.getRound().getRoundnumber() > 1) {
			setLastTeamPalyerAsDummyIfRequired(pl);
			}
		}
	}
	
	private void setLastTeamPalyerAsDummyIfRequired(PlayingPlayer pl) {
		List<PlayingPlayer> listOfPlayongPlayer = pl.getRound().getPlayingPlayer().stream()
				.filter(i -> null == i.getPlayer2()).collect(Collectors.toList());
		if(listOfPlayongPlayer.size() == 1) {
			listOfPlayongPlayer.get(0).setPlayer2("DUMMY_PLAYER");
			playingPlayerRepo.save(listOfPlayongPlayer.get(0));
		}
	}

	public void setWinningPlayerToTheNextRound(final PlayingPlayer playingPlayer, final String winingPlayer) {
		if (null != playingPlayer.getRound()) {
			Game game = playingPlayer.getRound().getGame();
			int presentRoundNumber = playingPlayer.getRound().getRoundnumber();
			int nxtRoundNumber = presentRoundNumber + 1;
			Round nextRound = game.getRounds().stream().filter(i -> i.getRoundnumber() == nxtRoundNumber).findFirst()
					.orElse(null);
			if (null != nextRound) {
				PlayingPlayer nextRoundPlayingPlayer = nextRound.getPlayingPlayer().stream()
						.filter(i -> (null != i.getPlayer1() && null == i.getPlayer2())
								|| (null == i.getPlayer1() && null == i.getPlayer2()))
						.findFirst().orElse(null);
				if (null != nextRoundPlayingPlayer) {
					if (null != nextRoundPlayingPlayer.getPlayer1()) {
						nextRoundPlayingPlayer.setPlayer2(winingPlayer);
					} else {
						nextRoundPlayingPlayer.setPlayer1(winingPlayer);
					}
					playingPlayerRepo.save(nextRoundPlayingPlayer);
				}
			}
		}
	}
	
	public List<LossingPlayerDto> getLoosingDtos(GameDto gameDto) {
		Round round = gameDto.getRounds().get(0);
		List<LossingPlayerDto> list = round.getPlayingPlayer().stream()
				.filter(i -> (null != i.getPlayer1() && null != i.getPlayer2()
						&& !"DUMMY_PLAYER".equalsIgnoreCase(i.getPlayer2())))
				.map(RoundService::mapToLossingPlayerDto).collect(Collectors.toList());
		return list;
	}
	
	@Transactional
	public void setDummyPlayer(final String playerItemId,final String playerForDummyPlayer) {
		Optional<PlayingPlayer> playingPlayerOp = playingPlayerRepo.findById(Long.parseLong(playerItemId));
		if(playingPlayerOp.isPresent()) {
			PlayingPlayer pl = playingPlayerOp.get();
			pl.setPlayer2(playerForDummyPlayer);
			playingPlayerRepo.save(pl);
		}
	}
	
	 public static LossingPlayerDto mapToLossingPlayerDto(PlayingPlayer playingPlayer) {
		 	String score = playingPlayer.getLossPlayer().equalsIgnoreCase(playingPlayer.getPlayer1()) ?
		 			playingPlayer.getPlayer1Score() : playingPlayer.getPlayer2Score();
	        return new LossingPlayerDto(playingPlayer.getLossPlayer(), score);
	    }

}
