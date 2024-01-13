package com.TuornamentOrganizer.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayerDto {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
