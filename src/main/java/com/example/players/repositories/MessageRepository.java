package com.example.players.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.players.model.Message;
import com.example.players.model.Player;

/**
 * This class implements IMessageRepository interface. 
 * It allows to manage messages
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MessageRepository implements IMessageRepository {

	/* message list */
    private final List<Message> messages = new ArrayList<>();

    /**
     * Constructor: initialize the MessageRepository.
     */
    public MessageRepository() {
    }

    /** 
     * add a new message
     * @param message Message
     */
    @Override
    public void addMessage(Message message) {
    	/* check if the player is valid */
		if(message == null) {
			throw new IllegalArgumentException("Message may not be null!");
		}
		if(!message.valid()) {
			throw new IllegalArgumentException("Message may not have valid attirubte!");
		}
        messages.add(message);
    }
    
    /** 
     * Get all the messages
     */
    @Override
	public List<Message> getAllMessages() {
		return this.messages;
	}
    
    /** 
     * Get all the messages sent or received by a specific player
     * @param player Player
     */
    @Override
    public List<Message> getAllMessagesByPlayer(Player player) {
        return this.messages.stream()
        				.filter(msg -> 
        				(msg.getSender().equals(player)  && msg.getDirection()==1)
        				|| (msg.getReceiver().equals(player))  && msg.getDirection()==0)
        				.collect(Collectors.toList());
    }

    /** 
     * Get all the messages sent by a specific player
     * @param sender Player
     */
    @Override
    public List<Message> getAllMessagesBySender(Player sender) {
        return this.messages.stream()
        				.filter(msg -> msg.getDirection()==1)
        				.filter(msg -> msg.getSender().equals(sender))
        				.collect(Collectors.toList());
    }

    /** 
     * Get all the messages received by a specific player
     * @param receiver Player
     */
    @Override
    public List<Message> getAllMessagesByReceiver(final Player receiver) {
        return this.messages.stream()
        				.filter(msg -> msg.getDirection()==0)
        				.filter(msg -> msg.getReceiver().equals(receiver))
        				.collect(Collectors.toList());
    }
    
    /** 
     * Get all the messages sent by a specific player grouped by the message content
     * @param sender Player
     */
    @Override
    public Map<String, Set<Message>> getLoggedMessagesBySenderGroupedByMessage(Player sender) {
        return this.getAllMessagesBySender(sender)
        					.stream()
        					.collect(Collectors.groupingBy(Message::getContent, Collectors.toSet()));
    }
	
    /** 
     * Get all the messages received by a specific player grouped by the message content
     * @param receiver Player
     */
    @Override
    public Map<String, Set<Message>> getLoggedMessagesByReceiverGroupedByMessage(final Player receiver) {
        return this.getAllMessagesByReceiver(receiver)
        					.stream()
			        		.collect(Collectors.groupingBy(Message::getContent, Collectors.toSet()));
	    }
}