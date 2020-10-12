package com.example.players.communication;

import com.example.players.services.IMessageLogger;

/**
 * This interface allows to create and start the communication between the players.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface CommunicationType {
	
	/**
     * this method starts the communication between the players and
     * return the exchanged messages notification in a list
     */
	public IMessageLogger startCommunication(String msg);

}
