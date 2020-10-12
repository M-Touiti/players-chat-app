package com.example.players.communication.chatProcessing;

import com.example.players.model.Player;

/**
 * This interface allows to start chat and receives message from the Subject.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface Observer {

	/**
     * get the observer identifier
     */
	public Player getPlayer();
	
	/**
     * Initiate the chat and send the first message to all the other observers
     * @param msg String the first message be sent in the chat
     */
	public void startChat(String msg);

	/**
     * Receives a message and send back a new message to all the observers using the subject object
     * log the notification of the reception of the message
     * @param sender Player the sender of the message
     * @param msg String the message content
     */
	public void receive(Player sender, String msg);
	
}
