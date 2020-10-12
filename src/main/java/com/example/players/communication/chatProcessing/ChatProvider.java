package com.example.players.communication.chatProcessing;

import java.util.ArrayList;
import java.util.List;

import com.example.players.model.Player;
import com.example.players.services.IMessageLogger;
import com.example.players.services.MessageLogger;

/**
 * This class implements Subject interface from the Observer design pattern. 
 * Uses the Subject interface to chat with all Observers.
 * It takes the chat players list
 * return the messageLogger which contains the exchanged messages information
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class ChatProvider implements Subject{
	
	/* List of all the observers to chat with */
	private final List<Observer> observers= new ArrayList<Observer>();
	
	/* message logger service */
	private final IMessageLogger messageLogger = new MessageLogger();
	
	/**
     * Constructor: initialize the chat provider.
     */
	public ChatProvider() {
	}

	/**
     * Add a new observer to the List.
     * @param newObserver Observer to be added
     */
	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);		
	}

	/**
     * Delete an observer from the List.
     * @param deleteObserver Observer to be deleted
     */
	@Override
	public void unregister(Observer deleteObserver) {
		
		/*  Get the index of the observer to delete */		
		int observerIndex = observers.indexOf(deleteObserver);

		/* Print out message (Have to increment index to match) */
		System.out.println("Observer " + (observerIndex+1) + " deleted");

		/* Removes observer from the ArrayList */
		observers.remove(observerIndex);		
	}
	
	/**
     * chat and send message to all other observer
     * @param currentMsgWriter Observer the sender of the message
     * @param msg String the message content
     */
	@Override
	public void chat(Observer currentMsgWriter, String msg) {
		
		/* Send the message to all the other observer */
		synchronized(this) {
			notifyOtherObservers(currentMsgWriter, msg);
		}
	}

	/**
     * Log the message and send it to all other observer
     * @param currentMsgWriter Observer the sender of the message
     * @param msg String the message content
     */
	public void notifyOtherObservers(Observer currentMsgWriter, String msg) {
		for(Observer observer : observers){	
			/* If the observer is different from the currentMsgWriter 
			 * Then send to him the message
			 */
			if(!currentMsgWriter.getPlayer().equals(observer.getPlayer())) {
				
				/* log the message in the chat logger */
				logSentMessage(currentMsgWriter.getPlayer(), observer.getPlayer(), msg);
				
				/* send the message to the player */
				observer.receive(currentMsgWriter.getPlayer(), msg);
			}				
		}
	}
	
	/**
     * Log in the chat logger the sent messages
     * @param sender Player the sender of the message
     * @param receiver Player the receiver of the message
     * @param content String the message content
     */
	public void logSentMessage(Player sender, Player receiver, String content){
		this.messageLogger.logMessage(sender, receiver, 1, content);
	}

	/**
     * Log in the chat logger the received messages
     * @param sender Player the sender of the message
     * @param receiver Player the receiver of the message
     * @param content String the message content
     */
	public void logReceivedMessage(Player sender, Player receiver, String content){
		this.messageLogger.logMessage(sender, receiver, 0, content);
	}
	
	/**
     * Get the messageLogger service
     */
	public IMessageLogger getMessageLogger(){
		return this.messageLogger;
	}
}
