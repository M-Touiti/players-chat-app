package com.example.players.repositories;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.players.model.Message;
import com.example.players.model.Player;

/**
 * This is the Message Repository interface allows to create, find and delete Message.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface IMessageRepository {

	/** 
    * add a new message
    * @param message Message
    */
	public void addMessage(Message message);
	 
	/** 
	* Get all the messages
	*/
	public List<Message> getAllMessages();
	
	/** 
     * Get all the messages sent or received by a specific player
     * @param player Player
     */
	public List<Message> getAllMessagesByPlayer(Player player);

	/** 
	* Get all the messages sent by a specific player
	* @param sender Player
	*/
	public List<Message> getAllMessagesBySender(Player sender);

	/** 
	* Get all the messages received by a specific player
	* @param receiver Player
	*/
	public List<Message> getAllMessagesByReceiver(Player receiver);
	 
	/** 
	* Get all the messages sent by a specific player grouped by the message content
	* @param sender Player
	*/
	public Map<String, Set<Message>> getLoggedMessagesBySenderGroupedByMessage(Player sender);
	 
	/** 
     * Get all the messages received by a specific player grouped by the message content
     * @param receiver Player
     */
	public Map<String, Set<Message>> getLoggedMessagesByReceiverGroupedByMessage(final Player receiver);
}
