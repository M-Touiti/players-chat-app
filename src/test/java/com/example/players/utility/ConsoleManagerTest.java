package com.example.players.utility;

import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.example.players.services.IPlayerService;
import com.example.players.services.PlayerService;

/**
 * Unit tests for ConsoleManager class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class ConsoleManagerTest {
	
	/**
     * Should throw IllegalArgumentException. Null playerService arguments.
     */
    @Test
    public void enterPlayerNameForNullPlayerService() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.enterPlayerName(null, "msg");
                }
        );
    }

	/**
     * Should throw IllegalArgumentException. Null message arguments.
     */
    @Test
    public void enterPlayerNameForNullMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			IPlayerService playerService = new PlayerService();
        			ConsoleManager.enterPlayerName(playerService, null);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message arguments.
     */
    @Test
    public void enterPlayerNameForEmptyMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			IPlayerService playerService = new PlayerService();
        			ConsoleManager.enterPlayerName(playerService, "");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message arguments.
     */
    @Test
    public void readIntForNullMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readInt(null, 0, 2);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message arguments.
     */
    @Test
    public void readIntForEmptyMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readInt("", 0, 2);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message arguments.
     */
    @Test
    public void readStringForNullMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readString(null);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message arguments.
     */
    @Test
    public void readStringForEmptyMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readString("");
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null message arguments.
     */
    @Test
    public void readStringWithOptionForNullMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readStringWithOption(null, Arrays.asList("y", "n"));
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty message arguments.
     */
    @Test
    public void readStringWithOptionForEmptyMessge() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readStringWithOption("", Arrays.asList("y", "n"));
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Null option arguments.
     */
    @Test
    public void readStringWithOptionForNullOption() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readStringWithOption("msg", null);
                }
        );
    }
    
    /**
     * Should throw IllegalArgumentException. Empty option arguments.
     */
    @Test
    public void readStringWithOptionForEmptyOption() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			ConsoleManager.readStringWithOption("msg", Collections.EMPTY_LIST);
                }
        );
    }
}
