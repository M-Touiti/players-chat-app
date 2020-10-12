package com.example.players.communication.chatProcessing;

import java.util.LinkedList;
import java.util.Queue;

import com.example.players.chatRoom.ChatRoom;
import com.example.players.model.Player;

/**
 * This class implements Observer interface from the Observer design pattern. 
 * Represents each Observer that is receiving and monitoring changes in the subject.
 * It takes in the parameter the chat and the players.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MultiThreadPlayerObserver implements Observer {
	
	/* The player that is represented by this Observer */
	private Player player;
	
	/* Will hold reference to the ChatProvider object */
	private Subject chat;
	
	/* Queue of the received Messages */
	private Queue<String>  receivedMessages = new LinkedList<String>();
	
	/* Number of the received Message */
	private int receivedMsg = 0;
	
	/* Number of the sent Message */
	public int sentMsg = 0;	
	
	/**
     * Constructor: initialize the subject and the player object.
     * @param chat Subject Will hold reference to the ChatThread object
     * @param player Player Will hold reference to the Player object
     */
	public MultiThreadPlayerObserver(Subject chat, Player player) {
		
		/* initialize the player */
		this.player = player;
		
		/*  Store the reference to the ChatProvider object 
		 * so we can make calls to its methods */
		this.chat = chat;
		
		/* Add the observer to the Subjects ArrayList */
		chat.register(this);
	}

	/**
     * get the player
     */
	@Override
	public Player getPlayer() {
		return this.player;
	}
	
	/**
     * Initiate the chat and send the first message to all the other observers
     * @param msg String the first message be sent in the chat
     */
	@Override
	public void startChat(String msg) {
		System.out.println("\n"+ this.player.getName() + " start the chat ...\n");
		++sentMsg;
		chat.chat(this, msg);
	}

	/**
     * Receives a message and send back a new message to all the observers using the subject object
     * log the notification of the reception of the message
     * @param sender Player the sender of the message
     * @param msg String the message content
     */
	@Override
	public void receive(Player sender, String msg) {
		
		/* check if this observer is allowed to receive a new message
		 * then save the received message to the queue 
		 * and log the notification of the reception of the message
		 */
		if(this.canRead()) {
			
			/* add the received message to the message queue */
			this.receivedMessages.add(msg);
			
			receivedMsg++;
			
			/* notify the subject to log the confirmation of the reception of the message */
			synchronized(this){
				((ChatProvider) this.chat).logReceivedMessage(sender, this.getPlayer(), msg);
			}
		}
		
		
	}
	
	/**
     * Retrieve a message from the queue of the received message 
     * and send it to all the observers using the subject object
     */
	public void sendNewMessage() {
		
		/* check if this observer is allowed to send a new message
		 * then retrieve the message to be sent from the queue 
		 * and send it to all observers
		 */
		if(canWrite() && !this.receivedMessages.isEmpty()) {
			
			/* retrieve the first received message in the queue 
			 * an concatenate it with the number of messages this player already sent.
			 */
			String messageToSend = this.receivedMessages.peek() + (++sentMsg);
			
			/* Remove the message to be sent from the message queue */
			this.receivedMessages.remove();
			
			/* communicate the message with all the other observer using the subject */
			this.chat.chat(this, messageToSend);
		}

	}

	/**
     * check if this player can send more message or not
     */
	public boolean canWrite() {
		return sentMsg < ChatRoom.getMaxMsgPerPlayer();
	}
	
	/**
     * check if this player can receive more message or not
     */
	public boolean canRead() {
		return receivedMsg < ChatRoom.getMaxMsgPerPlayer();
	}
}
