package com.example.players.chatRoom;

import java.util.List;

import com.example.players.communication.CommunicationType;
import com.example.players.exceptions.PlayerException;
import com.example.players.model.Player;
import com.example.players.services.IMessageLogger;
import com.example.players.services.IPlayerService;
import com.example.players.services.PlayerService;

/**
 * This is the ChatRoom. 
 * It takes in the parameter the message max number that each player allowed to send or receive.
 * It uses playerService to set and get the players of the chat.
 * It uses the method startCommunication() to start the communication between the players 
 * return the exchanged messages list
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public abstract class ChatRoom {
	
	/* the message max number of message that each player allowed to send or receive during the chat */
	private static int maxMsgPerPlayer = 0;
	
	/* Chat Player service, which contains and manipulates the players */
	private IPlayerService playerService = new PlayerService();
	
	/* message logger service */
	private IMessageLogger messageLogger;
	
	/* used to create the communication between the players */
	private CommunicationType communication;

	/**
     * Constructor: initialize the chatRoom Class.
     * @param maxMsgPerPlayer the message max number that each player allowed to send or receive.
     */
	public ChatRoom(int maxMsgPerPlayer) {
		ChatRoom.maxMsgPerPlayer = maxMsgPerPlayer;		
	}
	
	/**
     * this method starts the communication between the players and
     * saves the exchanged messages notification in a list
     */
	public void startCommunication(String msg) {
		this.messageLogger = this.communication.startCommunication(msg);
	}
	
	/**
     * Display the list of exchanged messages notification in all the chat
     */
	public void displayChatMessages() {
		System.out.println("\n+-+-+-+-+-+-+-+-+-+ Chat Logger +-+-+-+-+-+-+-+-+-+\n");
		this.messageLogger.displayChatLogger();
	}
	
	/**
     * Display the chat statistics of all the players
     */
	public void displayPlayersStatistics() {
		System.out.println("\n+-+-+-+-+-+-+-+-+-+ Chat Statistics +-+-+-+-+-+-+-+-+-+\n");
		List<Player> playerList = this.playerService.getAllPlayers();
		this.getMessageLogger().displayPlayersStatistics(playerList);
	}
	
	/**
     * Display the list of exchanged messages notification for a specific player
     */
	public void displayPlayerMessages(Player player) {
		System.out.println("\n+-+-+-+-+-+-+-+-+-+ " + player.getName() + " Chat Logger +-+-+-+-+-+-+-+-+-+\n");
		this.messageLogger.displayPlayerLogger(player);
	}
	
	/**
     * Display the chat statistics for a specific player
     */
	public void displayPlayerStatistics(Player player) {
		System.out.println("\n+-+-+-+-+-+-+-+-+-+ " + player.getName() + "  Chat Statistics +-+-+-+-+-+-+-+-+-+\n");
		this.messageLogger.displayPlayerChatStatistic(player);
	}
	
	/**
     * Add the initiator player in the chat
	 * @throws PlayerException 
     */
	public void addInitiatorPlayer(String playerName) {
		this.playerService.setInitiatorPlayer(playerName);
	}
	
	/**
     * Add the other players in the chat
     */
	public void addPlayerList(List<String> playerName) {
		this.playerService.addPlayerList(playerName);
	}
	
	/**
     * Can start the chat if the initiator player not empty
     * and there is at least 1 observer player
     */
	public boolean canStartChat() {
		return !this.playerService.getInitiatorPlayer().empty()
				&& this.playerService.getObserverPlayers().size()>=1 ;
	}
	
	public static int getMaxMsgPerPlayer() {
		return ChatRoom.maxMsgPerPlayer;
	}

	public static void setMaxMsgPerPlayer(int maxMsgPerPlayer) {
		ChatRoom.maxMsgPerPlayer = maxMsgPerPlayer;
	}	

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public CommunicationType getCommunication() {
		return communication;
	}

	public void setCommunication(CommunicationType communication) {
		this.communication = communication;
	}

	public IMessageLogger getMessageLogger() {
		return messageLogger;
	}

	public void setMessageLogger(IMessageLogger messageLogger) {
		this.messageLogger = messageLogger;
	}	
}
