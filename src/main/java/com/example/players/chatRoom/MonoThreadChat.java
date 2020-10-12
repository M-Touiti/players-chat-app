package com.example.players.chatRoom;

import com.example.players.communication.MonoThreadCommunication;

/**
 * This class represents an implementation of the strategy design pattern
 * It extends ChatRoom class. 
 * It aims to run the communication between the players in the SAME java process
 * It sets MonoThreadCommunication object to the CommunicationType attribute.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MonoThreadChat extends ChatRoom{

	/**
     * Constructor: initialize the chatRoom, 
     * and sets MonoThreadCommunication object to the CommunicationType attribute.
     * @param maxMsgPerPlayer the message max number that each player allowed to send or receive.
     */
	public MonoThreadChat(int maxMsgPerPlayer) {
		super(maxMsgPerPlayer);
		System.out.println("\n---------------- Initializing Mono-Threading Chat --------------\n");
		this.setCommunication(
				new MonoThreadCommunication(super.getPlayerService())
			);
	}

}
