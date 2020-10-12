package com.example.players.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.players.model.Message;
import com.example.players.model.Player;
import com.example.players.repositories.MessageRepository;

/**
 * This class implements IMessageLogger interface. 
 * It allows to manage messages
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MessageLogger implements IMessageLogger{
	
	/* the Message repository, which store and manage the messages */
	private final MessageRepository messageRepository = new MessageRepository();
	
	/**
     * Constructor: initialize the MessageLogger.
     */
	public MessageLogger() {
    }

	/**
     * Add new message to the MessageLogger
     * @param sender Player
     * @param receiver Player
     * @param direction int 1 for send and 0 for receive
     * @param content String message content
     */
	@Override
	public void logMessage(Player sender, Player receiver, int direction, String content) {
		Message message = new Message(sender, receiver, direction, content);
        messageRepository.addMessage(message);
	}
	
	/** 
     * Display all the messages sent and received by a specific player
     * @param player Player
     */
	@Override
	public void displayPlayerLogger(Player player) {
		this.messageRepository.getAllMessagesByPlayer(player)
			.forEach(System.out::println);
	}
	
	/** 
     * Display all logged messages
     */
	@Override
	public void displayChatLogger() {
		this.messageRepository.getAllMessages().forEach(System.out::println);
	}
	
	/** 
     * Display some statistic for all the messages sent and received by a list of player
     * Display the number and the message received of each player
     * Display the number and the message sent of each player
     * @param players Player List
     */
	@Override
	public void displayPlayersStatistics(List<Player> playerList) {
		playerList.forEach( player -> displayPlayerChatStatistic(player));
    }
	
	/** 
     * Display some statistic for all the messages sent and received by a specific player     * 
     * Display the number and the message received for a specific player
     * Display the number and the message sent for a specific player
     * @param player Player
     */
	@Override
	public void displayPlayerChatStatistic(Player player) {
		System.out.println("\n-------- player '" + player.getName() + "' statistics");
		displayLoggedMessagesBySenderGroupedByMessage(player);
		displayLoggedMessagesByReceiverGroupedByMessage(player);
	}	
	
	/** 
     * Display some statistic for all the messages sent by a specific player
     * @param sender Player
     */
	public void displayLoggedMessagesBySenderGroupedByMessage(Player sender) {
		
		/* Get all the messages sent by a specific player grouped by the message content
		 * the key of the map represents the message content
		 * the value of the map Set<Message> represents the message set sent by the specific player
		 * and their content equals to the key map
		 */
		Map<String, Set<Message>> messageSenderGroupedByMessage =  
									this.messageRepository.getLoggedMessagesBySenderGroupedByMessage(sender);
		
		
		
		System.out.println("\n--- '" + sender.getName() 
							+ "' sent " 
							+ messageSenderGroupedByMessage.size() + " messages:");
		
		/* Display the content of all message sent 
		 * and for each message sent display the number of player 
		 * received this message from this player
		 */
		messageSenderGroupedByMessage.forEach((messaContent,messageList)->{
			System.out.println(sender.getName() + " sent the message " + messaContent 
					+ " to " + messageList.size() + " player(s)");
		});
	}
	
	/** 
     * Display some statistic for all the messages received by a specific player
     * @param receiver Player
     */
	public void displayLoggedMessagesByReceiverGroupedByMessage(Player receiver) {
		
		/* Get all the messages received by a specific player grouped by the message content
		 * the key of the map represents the message content
		 * the value of the map Set<Message> represents the message set received by the specific player
		 * and their content equals to the key map
		 */
		Map<String, Set<Message>> messageReceiverGroupedByMessage =  
									this.messageRepository.getLoggedMessagesByReceiverGroupedByMessage(receiver);
		
		/* calculate the number of received messages for this player */
		int numberOfReceivedMessages = messageReceiverGroupedByMessage.entrySet().stream()
                .map(entry -> entry.getValue())
                .flatMap(list -> list.stream())
                .collect(Collectors.toSet())
                .size();
		
		System.out.println("\n--- '" + receiver.getName() 
							+ "' received "
							+  numberOfReceivedMessages +" messages:");
		
		/* Display the content of all message received 
		 * and for each message received display the number of player 
		 * sent this message to this player
		 */
		messageReceiverGroupedByMessage.forEach((messaContent,messageList)->{
			System.out.println(receiver.getName() + " received the message " + messaContent 
					+ " from " + messageList.size() + " player(s)");
		});
	}
	
	
	

}
