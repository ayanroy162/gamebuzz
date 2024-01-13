package com.TuornamentOrganizer.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.TuornamentOrganizer.Dto.PlayerResistrationDto;
import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.Player;
import com.TuornamentOrganizer.Repository.GamerRepo;
import com.TuornamentOrganizer.Repository.PlayerRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepo playerRepo;
	@Autowired
	private GamerRepo gamerRepo;

	public void addNewPlayer(final PlayerResistrationDto playerResistrationDto, final String selectedGame) {

		if(StringUtils.isNotEmpty(selectedGame)) {
		Player player = new Player();
		Optional<Game> game = gamerRepo.findById(Long.parseLong(selectedGame));
		player.setName(playerResistrationDto.getName());
		player.setPhoneNumber(playerResistrationDto.getPhoneNumber());
		player.setGame(game.get());
		playerRepo.save(player);
		List<Player> listOfplayer = game.get().getPlayer();
		if(CollectionUtils.isEmpty(listOfplayer)) {
			listOfplayer = new ArrayList<>();
		}
		listOfplayer.add(player);
		game.get().setPlayer(listOfplayer);
		gamerRepo.save(game.get());
		}
	}
}
