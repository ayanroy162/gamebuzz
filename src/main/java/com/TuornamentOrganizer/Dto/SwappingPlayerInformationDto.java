package com.TuornamentOrganizer.Dto;

public class SwappingPlayerInformationDto {

	private String playingPlayerId;
	private String playingPlayer;
	private String playingPlayerNumber;

	public String getPlayingPlayerId() {
		return playingPlayerId;
	}

	public void setPlayingPlayerId(String playingPlayerId) {
		this.playingPlayerId = playingPlayerId;
	}
	
	public String getPlayingPlayerNumber() {
		return playingPlayerNumber;
	}

	public void setPlayingPlayerNumber(String playingPlayerNumber) {
		this.playingPlayerNumber = playingPlayerNumber;
	}

	public String getPlayingPlayer() {
		return playingPlayer;
	}

	public void setPlayingPlayer(String playingPlayer) {
		this.playingPlayer = playingPlayer;
	}

	public SwappingPlayerInformationDto() {
		super();
	}
	public SwappingPlayerInformationDto(String playingPlayerId, String playingPlayer, String playingPlayerNumber) {
		super();
		this.playingPlayerId = playingPlayerId;
		this.playingPlayer = playingPlayer;
		this.playingPlayerNumber = playingPlayerNumber;
	}

}
