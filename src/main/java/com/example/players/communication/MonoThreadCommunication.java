package com.example.players.communication;

import java.util.List;

import com.example.players.communication.chatProcessing.ChatProvider;
import com.example.players.communication.chatProcessing.MonoThreadPlayerObserver;
import com.example.players.communication.chatProcessing.Observer;
import com.example.players.communication.chatProcessing.Subject;
import com.example.players.model.Player;
import com.example.players.services.IMessageLogger;
import com.example.players.services.IPlayerService;

/**
 * This class implements CommunicationType interface. 
 * It takes in the parameter the chat playerService 
 * After, it uses the method startCommunication() to create 
 * and start the communication between them in the SAME java process
 * The Observer design pattern used to perform the communication of the message 
 * between the players
 * return the list of exchanged message notification
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MonoThreadCommunication implements CommunicationType{
	
	/* Chat Player service */
	private IPlayerService playerService;
	
	/**
     * Constructor: initialize the chat playerService.
     * @param playerService it contains the chat players
     */
	public MonoThreadCommunication(IPlayerService playerService) {
		this.playerService = playerService;
	}

	/**
     * This method starts the communication between the players in the same java process
     * The Observer design pattern used to perform and communicate the messages
     * between the players
     * return the exchanged messages notification in a list
     */
	@Override
	public IMessageLogger startCommunication(String msg) {
		
		/* instance of the Subject of the Observer design pattern */ 
		Subject chat = new ChatProvider();
		
		/* Create an Observer for the initiator player in the chat */
		Observer initiator = new MonoThreadPlayerObserver(chat, this.playerService.getInitiatorPlayer());
		
		/* Retrieve the observer players in the chat */
		List<Player> playerList  = this.playerService.getObserverPlayers();
		
		/* Create an Observer for each player in the chat */
		for(Player player : playerList) {			
			new MonoThreadPlayerObserver(chat, player);
		}
		
		/* the initiator Observer starts the chat */
		initiator.startChat(msg);
		
		/* return the MessageLogger Service which contains the exchanged messages information */
		return ((ChatProvider) chat).getMessageLogger();
	}

}
