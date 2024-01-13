package com.TuornamentOrganizer.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class TournamentDto {

	private long id;
	private String tournamentName;

	private List<GameDto> games;

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public List<GameDto> getGames() {
		return games;
	}

	public void setGames(List<GameDto> games) {
		this.games = games;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TournamentDto [id=" + id + ", tournamentName=" + tournamentName + ", games=" + games + "]";
	}
	
	

}
