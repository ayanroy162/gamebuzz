package com.TuornamentOrganizer.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity()
@Table(name="tournament")
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String tournamentName;

	@Column
	private String tournamentCreatedate;

	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Game> games;
	
	private boolean active;
	
	private boolean roundGenerated = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public String getTournamentCreatedate() {
		return tournamentCreatedate;
	}

	public void setTournamentCreatedate(String tournamentCreatedate) {
		this.tournamentCreatedate = tournamentCreatedate;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isRoundGenerated() {
		return this.roundGenerated;
	}

	public void setRoundGenerated(boolean roundGenerated) {
		this.roundGenerated = roundGenerated;
	}

	
}
