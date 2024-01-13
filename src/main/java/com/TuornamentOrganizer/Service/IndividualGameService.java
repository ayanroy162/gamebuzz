package com.TuornamentOrganizer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.IndividualGame;
import com.TuornamentOrganizer.Repository.IndividualGameRepo;

@Service
public class IndividualGameService {

	@Autowired
	private IndividualGameRepo individualGameRepo;

	public void addNewIndividualGame(final String individualGame) {
		IndividualGame newGame = new IndividualGame();
		newGame.setGameType(individualGame);
		individualGameRepo.save(newGame);
	}

	public List<IndividualGame> getAllIndividualGame() {
		List<IndividualGame> allIndividualGames = individualGameRepo.findAll();
		return allIndividualGames;
	}

	public void deleteIndividualGame(final Long individualGameId) {
		individualGameRepo.deleteById(individualGameId);
	}

	public Optional<IndividualGame> getIndividualGame(long parseLong) {

		return individualGameRepo.findById(parseLong);
	}

}
