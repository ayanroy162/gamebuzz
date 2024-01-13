package com.TuornamentOrganizer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayingPlayer {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private int teamNumber;
	private String player1;
	private String player2;
	private String winPlayer;
	private String lossPlayer;
	private String player1Score;
	private String player2Score;
	
	@ManyToOne
    @JoinColumn(name = "round_id")
	private Round round;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getWinPlayer() {
		return winPlayer;
	}

	public void setWinPlayer(String winPlayer) {
		this.winPlayer = winPlayer;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public int getTeamNumber() {
		return this.teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getLossPlayer() {
		return this.lossPlayer;
	}

	public void setLossPlayer(String lossPlayer) {
		this.lossPlayer = lossPlayer;
	}

	public String getPlayer1Score() {
		return this.player1Score;
	}

	public void setPlayer1Score(String player1Score) {
		this.player1Score = player1Score;
	}

	public String getPlayer2Score() {
		return this.player2Score;
	}

	public void setPlayer2Score(String player2Score) {
		this.player2Score = player2Score;
	}

	

}
