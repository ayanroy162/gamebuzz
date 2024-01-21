package com.TuornamentOrganizer.Dto;

import java.util.List;

import com.TuornamentOrganizer.Model.Round;

public class GameDto {

	private long id;
	private String name;
	private String rules;
	private String numberOfRounds;
	private String numberOfPlayers;
	private boolean roundGenerated;
	private boolean active;
	
	private List<Round> rounds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GameDto [id=" + id + ", name=" + name + ", rules=" + rules + "]";
	}

	public String getNumberOfRounds() {
		return this.numberOfRounds;
	}

	public void setNumberOfRounds(String numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public List<Round> getRounds() {
		return this.rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public String getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	public void setNumberOfPlayers(String numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public boolean isRoundGenerated() {
		return this.roundGenerated;
	}

	public void setRoundGenerated(boolean roundGenerated) {
		this.roundGenerated = roundGenerated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public GameDto(long id, String name, String rules, String numberOfRounds, String numberOfPlayers,
			boolean roundGenerated, boolean active, List<Round> rounds) {
		super();
		this.id = id;
		this.name = name;
		this.rules = rules;
		this.numberOfRounds = numberOfRounds;
		this.numberOfPlayers = numberOfPlayers;
		this.roundGenerated = roundGenerated;
		this.active = active;
		this.rounds = rounds;
	}

	public GameDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
