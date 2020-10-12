package com.example.players.services;

import java.util.List;

import com.example.players.exceptions.PlayerException;
import com.example.players.model.Player;

/**
 * This is the Player Service interface allows to create, find and delete player.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface IPlayerService {

	/**
     * This method set the initiator player of the chat by his name
     * @param playerName String
	 * @throws PlayerException 
     */
	public void setInitiatorPlayer(String playerName);
	
	/**
     * add the player list of the chat
     * @param playerListName playerName List
     */
	public void addPlayerList(List<String> playerListName);
	
	/**
     * Find a player by his name and return it back
     * @param playerName String
     */
	public Player find(String playerName);
	
	/**
     * Get the initiator player of the chat
     */
	public Player getInitiatorPlayer();
	
	/**
     * Get the player list of the chat
     */
	public List<Player> getObserverPlayers();
	
	/**
     * Get all the players of the chat
     */
	public List<Player> getAllPlayers();
}
