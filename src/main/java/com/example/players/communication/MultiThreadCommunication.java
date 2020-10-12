package com.example.players.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.example.players.communication.chatProcessing.ChatProvider;
import com.example.players.communication.chatProcessing.ChatThread;
import com.example.players.communication.chatProcessing.MultiThreadPlayerObserver;
import com.example.players.communication.chatProcessing.Observer;
import com.example.players.communication.chatProcessing.Subject;
import com.example.players.model.Player;
import com.example.players.services.IMessageLogger;
import com.example.players.services.IPlayerService;

/**
 * This class implements CommunicationType interface. 
 * It takes in the parameter the chat playerService 
 * After, it uses the method startCommunication() to create 
 * and start the communication between them in a SEPARATE java process
 * The Observer design pattern used to perform the communication of the message 
 * between the players
 * return the list of exchanged message notification
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MultiThreadCommunication implements CommunicationType{
	
	/* Chat Player service */
	private IPlayerService playerService;
	
	/**
     * Constructor: initialize the chat playerService.
     * @param playerService it contains the chat players
     */
	public MultiThreadCommunication(IPlayerService playerService) {
		this.playerService = playerService;
	}

	/**
     * this method starts the communication between the players in the same java process
     * The Observer design pattern used to perform and communicate the messages
     * between the players
     * The process is executed in a separate thread
     * return the exchanged messages notification in a list
     */
	@Override
	public IMessageLogger startCommunication(String msg) {
		
		/* instance of the Subject of the Observer design pattern */ 
		Subject chat = new ChatProvider();	
		
		/* Create an Observer for the initiator player in the chat */
		Observer initiatorObserver = new MultiThreadPlayerObserver(chat, this.playerService.getInitiatorPlayer());
		
		/* Retrieve the observer players in the chat */
		List<Player> playerList  = this.playerService.getObserverPlayers();
		
		/* Create a new list of observer */
		List<Observer> observerList  = new ArrayList<Observer>();
		
		/* Create an Observer for each player in the chat and add it in the observer list */
		for(Player player : playerList) {
			
			Observer observer = new MultiThreadPlayerObserver(chat, player);			
			observerList.add(observer);
		}
		
		
		/* Thread pools are  way of managing lots of threads at the same time
		 * We call ExecutorService to create n Threads (n = the number of all player in the chat)
		 */
		ExecutorService executor = Executors.newFixedThreadPool(observerList.size()+1);
		
		/* Give the initiator observer task to a worker (thread) */ 
		executor.submit(new ChatThread(initiatorObserver, msg));
		
		/* Give the observer tasks to these workers (threads) */
		for(Observer observer : observerList) {
			executor.submit(new ChatThread(observer));
		}
		
		/* Close the submit at the end of these process.*/
		executor.shutdown();
				
		/* Wait until the termination of these tasks */
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}	
				
		/* return the MessageLogger Service which contains the exchanged messages information */
		return ((ChatProvider) chat).getMessageLogger();
	}

}
