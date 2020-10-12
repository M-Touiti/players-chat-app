package com.example.players.chatRoom;

import com.example.players.communication.MultiThreadCommunication;

/**
 * This class represents an implementation of the strategy design pattern
 * It extends ChatRoom class. 
 * It aims to run the communication between the players in a SEPERATE java process
 * It sets MultiThreadCommunication object to the CommunicationType attribute.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MultiThreadChat extends ChatRoom{
	
	/**
     * Constructor: initialize the chatRoom, 
     * and sets MultiThreadChat object to the CommunicationType attribute.
     * @param maxMsgPerPlayer the message max number that each player allowed to send or receive.
     */
	public MultiThreadChat(int maxMsgPerPlayer) {
		super(maxMsgPerPlayer);
		System.out.println("\n---------------- Initializing Multi-Threading Chat --------------\n");
		this.setCommunication(
				new MultiThreadCommunication(super.getPlayerService())
			);
	}

}
