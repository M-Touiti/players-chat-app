package com.example.players.services;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.example.players.model.Player;

/**
 * Unit tests for MessageLogger class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class MessageLoggerTest {

	/**
     * Should throw IllegalArgumentException. Null message sender.
     */
    @Test
    public void logMessageForNullMessageSender() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(null, new Player("receiver"),0,"content");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message sender.
     */
    @Test
    public void logMessageForEmptyMessageSender() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player(""), new Player("receiver"),0,"content");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message receiver.
     */
    @Test
    public void logMessageForNullMessageReceiver() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player("sender"), null, 0,"content");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message receiver.
     */
    @Test
    public void logMessageForEmptyMessageReceiver() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player("sender"), new Player(""), 0,"content");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Wrong message Direction (shoud 1 for sent and 0 for receive).
     */
    @Test
    public void logMessageForWrongMessageDirection() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player("sender"), new Player("receiver"), 2,"content");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message receiver.
     */
    @Test
    public void logMessageForNullMessageContent() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player("sender"), new Player("receiver"), 0, null);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message receiver.
     */
    @Test
    public void addMessageForEmptyMessageContent() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			MessageLogger messageLogger = new MessageLogger();
        			messageLogger.logMessage(new Player("sender"), new Player("receiver"), 0, "");
                }
        );
    }     
}
