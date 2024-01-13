package com.TuornamentOrganizer.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "round")
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int roundnumber;
	
	private int totalNoumberOfTeams;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
    @JoinColumn(name = "gameId")
	private Game game;
	
	@OneToMany(mappedBy = "round",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<PlayingPlayer> playingPlayer = new ArrayList<>();

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getRoundnumber() {
		return roundnumber;
	}

	public void setRoundnumber(int roundnumber) {
		this.roundnumber = roundnumber;
	}

	public List<PlayingPlayer> getPlayingPlayer() {
		return playingPlayer;
	}

	public void setPlayingPlayer(List<PlayingPlayer> playingPlayer) {
		this.playingPlayer = playingPlayer;
	}

	public int getTotalNoumberOfTeams() {
		return this.totalNoumberOfTeams;
	}

	public void setTotalNoumberOfTeams(int totalNoumberOfTeams) {
		this.totalNoumberOfTeams = totalNoumberOfTeams;
	}

    
	
	
	
}
