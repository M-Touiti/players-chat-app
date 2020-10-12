package com.example.players.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.example.players.model.Message;
import com.example.players.model.Player;

/**
 * Unit tests for MessageRepository class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MessageRepositoryTest {

	/**
     * Should throw IllegalArgumentException. Null message parameter.
     */
    @Test
    public void addMessageForNullMessage() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			messageRepo.addMessage(null);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message sender.
     */
    @Test
    public void addMessageForNullMessageSender() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(null, new Player("receiver"),0,"content");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message sender.
     */
    @Test
    public void addMessageForEmptyMessageSender() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player(""), new Player("receiver"),0,"content");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message receiver.
     */
    @Test
    public void addMessageForNullMessageReceiver() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player("sender"), null, 0,"content");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message receiver.
     */
    @Test
    public void addMessageForEmptyMessageReceiver() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player("sender"), new Player(""), 0,"content");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Wrong message Direction (shoud 1 for sent and 0 for receive).
     */
    @Test
    public void addMessageForWrongMessageDirection() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player("sender"), new Player("receiver"), 2,"content");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message content.
     */
    @Test
    public void addMessageForNullMessageContent() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player("sender"), new Player("receiver"), 0, null);
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message content.
     */
    @Test
    public void addMessageForEmptyMessageContent() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageRepository messageRepo = new MessageRepository();
        			Message message = new Message(new Player("sender"), new Player("receiver"), 0, "");
        			messageRepo.addMessage(message);
                }
        );
    }
    
    /**
     * Should return 1, we can add a valid message.
     */
    @Test
    public void addMessageForValidMessage() {
    	MessageRepository messageRepo = new MessageRepository();
		Message message = new Message(new Player("sender"), new Player("receiver"),1,"content");
		messageRepo.addMessage(message);
		/* the initiator is not empty */
        assertEquals(1, messageRepo.getAllMessages().size());
    }
    
    /**
     * Should return 0, Null Sender.
     */
    @Test
    public void getAllMessagesBySenderForNullSender() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);

        assertEquals(0, messageRepo.getAllMessagesBySender(null).size());
    }
    
    /**
     * Should return 0, Player sent 0 message.
     */
    @Test
    public void getAllMessagesBySenderForNotFoundSender() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);

        assertEquals(0, messageRepo.getAllMessagesBySender(player2).size());
    }
    
    /**
     * Should return 2, we can get all message sent by specific player.
     */
    @Test
    public void getAllMessagesBySenderForValidSender() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);
		
		message = new Message(player2, player1 ,1 ,"content2");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,0 ,"content3");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,1 ,"content4");
		messageRepo.addMessage(message);

        assertEquals(2, messageRepo.getAllMessagesBySender(player1).size());
    }   
    
    /**
     * Should return 0, Null Receiver.
     */
    @Test
    public void getAllMessagesByReceiverForNullReceiver() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);

        assertEquals(0, messageRepo.getAllMessagesByReceiver(null).size());
    }
    
    /**
     * Should return 0, Player received 0 message.
     */
    @Test
    public void getAllMessagesByReceiverForNotFoundReceiver() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);

        assertEquals(0, messageRepo.getAllMessagesByReceiver(player1).size());
    }
    
    /**
     * Should return 1, we can get all message received by specific player.
     */
    @Test
    public void getAllMessagesByReceiverForValidReceiver() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"content1");
		messageRepo.addMessage(message);
		
		message = new Message(player2, player1 ,1 ,"content2");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,0 ,"content3");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,1 ,"content4");
		messageRepo.addMessage(message);

        assertEquals(1, messageRepo.getAllMessagesByReceiver(player2).size());
    }
    
    /**
     * Should return 2, we can get all message sent by specific player and grouped by the message content.
     */
    @Test
    public void getAllMessagesBySenderGroupedByMessageForValidSender() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,1 ,"Hello");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,1 ,"Hi");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,1 ,"Hello");
		messageRepo.addMessage(message);

        assertEquals(2, messageRepo.getLoggedMessagesBySenderGroupedByMessage(player1).size());
    }
    
    /**
     * Should return 2, we can get all message received by specific player and grouped by the message content.
     */
    @Test
    public void getAllMessagesByReceiverGroupedByMessageForValidSender() {
    	MessageRepository messageRepo = new MessageRepository();
    	 
    	Player player1 = new Player("player1");
    	Player player2 = new Player("player2");
    	
		Message message = new Message(player1, player2 ,0 ,"Hello");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,0 ,"Hi");
		messageRepo.addMessage(message);
		
		message = new Message(player1, player2 ,0 ,"Hello");
		messageRepo.addMessage(message);

        assertEquals(2, messageRepo.getLoggedMessagesByReceiverGroupedByMessage(player2).size());
    }
}
