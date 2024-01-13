package com.TuornamentOrganizer.Dto;

public class LossingPlayerDto {

	private String name;
	private String score;
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return this.score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public LossingPlayerDto(String name, String score) {
		super();
		this.name = name;
		this.score = score;
	}
	public LossingPlayerDto() {
		super();
	}
	
	
	
}
