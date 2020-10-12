package com.example.players.repositories;

import java.util.List;

import com.example.players.exceptions.PlayerException;
import com.example.players.model.Player;

/**
 * This is the Player Repository interface allows to create, find and delete player.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface IPlayerRepository {

	/**
     * This method set the initiator player of the chat
     * @param player Player
     */
	public void setInitiatorPlayer(Player player) throws PlayerException;
	
	/**
     * This method add a player to the chat
     * @param player Player
     */
	public void addPlayer(Player player) throws PlayerException;
	
	/**
     * Get the initiator player of the chat
     */
	public Player getInitiatorPlayer();
	
	/**
     * Get the observer players of the chat
     */
	public List<Player> getPlayers();
	
	/**
     * Find a player by his name and return it back
     * @param playerName String
     */
	public Player findPlayerByName(String name);

}