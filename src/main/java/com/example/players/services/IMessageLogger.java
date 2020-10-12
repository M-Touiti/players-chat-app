package com.example.players.services;

import java.util.List;

import com.example.players.model.Player;

public interface IMessageLogger {

	/**
     * Add new message to the MessageLogger
     * @param sender Player
     * @param receiver Player
     * @param direction int 1 for send and 0 for receive
     * @param content String message content
     */
	public void logMessage(Player sender, Player receiver, int direction, String content);
	
	/** 
     * Display all the messages sent and received by a specific player
     * @param player Player
     */
	public void displayPlayerLogger(Player player);
	
	/** 
     * Display all logged messages
     */
	public void displayChatLogger();
	
	/** 
     * Display some statistic for all the messages sent and received by a list of player
     * @param players Player List
     */
	public void displayPlayersStatistics(List<Player> playerList);
	
	/** 
     * Display some statistic for all the messages sent and received by a specific player
     * @param player Player
     */
	public void displayPlayerChatStatistic(Player player);	
}
