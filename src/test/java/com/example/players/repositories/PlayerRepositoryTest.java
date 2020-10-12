package com.example.players.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.players.exceptions.PlayerException;
import com.example.players.exceptions.PlayerNotFoundException;
import com.example.players.model.Player;

/**
 * Unit tests for PlayerRepository class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class PlayerRepositoryTest {

	/**
     * Should throw PlayerNotFoundException. Null player parameter.
     */
    @Test
    public void setInitiatorPlayerForNullPlayer() {
        assertThrows(
        		PlayerNotFoundException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			playerRepo.setInitiatorPlayer(null);
                }
        );
    } 
    
    /**
     * Should throw PlayerNotFoundException. empty player parameter.
     */
    @Test
    public void setInitiatorPlayerForEmptyPlayer() {
        assertThrows(
        		PlayerNotFoundException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			playerRepo.setInitiatorPlayer(new Player(""));
                }
        );
    }
    
    /**
     * Should throw PlayerException. we can't set an initiator if it is already exist.
     */
    @Test
    public void setInitiatorPlayerManyTime() {
        assertThrows(
        		PlayerException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			Player player1 = new Player("player1");
        			Player player2 = new Player("player2");
        			playerRepo.setInitiatorPlayer(player1);
        			playerRepo.setInitiatorPlayer(player2);
                }
        );
    }
    
    /**
     * Should return true, we can set a valid initiator of the chat.
     */
    @Test
    public void setInitiatorPlayerForValidPlayer() {
        PlayerRepository playerRepo = new PlayerRepository();
		try {
			playerRepo.setInitiatorPlayer(new Player("player1"));
		} catch (PlayerException e) {
			e.printStackTrace();
		}
		/* the initiator is not empty */
        assertTrue(!playerRepo.getInitiatorPlayer().empty());
    }  
    
	/**
     * Should throw PlayerNotFoundException. Null player parameter.
     */
    @Test
    public void addPlayerForNullPlayer() {
        assertThrows(
        		PlayerNotFoundException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			playerRepo.addPlayer(null);
                }
        );
    } 
    
    /**
     * Should throw PlayerNotFoundException. Empty player parameter.
     */
    @Test
    public void addPlayerForEmptyPlayer() {
        assertThrows(
        		PlayerNotFoundException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			playerRepo.addPlayer(new Player(""));
                }
        );
    }
    
    /**
     * Should throw PlayerException. We should initialize the initiator of the chat first.
     */
    @Test
    public void addPlayerErrorInitiatorIsEmpty() {
        assertThrows(
        		PlayerException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			Player player1 = new Player("player1");
        			playerRepo.addPlayer(player1);
                }
        );
    }
    
    /**
     * Should throw PlayerException. we can't set a player if it is already the initiator.
     */
    @Test
    public void addPlayerForAPlayerAlreadyJoinedAsInitiator() {
        assertThrows(
        		PlayerException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			Player player1 = new Player("player1");        			
        			playerRepo.setInitiatorPlayer(player1);
        			
        			Player player2 = new Player("player1");
        			playerRepo.addPlayer(player2);
                }
        );
    }
    
    /**
     * Should throw PlayerException. we can't set a player if it is already exist.
     */
    @Test
    public void addPlayerForAPlayerJoinedTheChat() {
        assertThrows(
        		PlayerException.class,() -> {
        			PlayerRepository playerRepo = new PlayerRepository();
        			Player player1 = new Player("player1");        			
        			playerRepo.setInitiatorPlayer(player1);
        			
        			Player player2 = new Player("player2");
        			playerRepo.addPlayer(player2);
        			
        			Player player3 = new Player("player2");
        			playerRepo.addPlayer(player3);
                }
        );
    }
    
    /**
     * Should return 1, we can set a valid initiator of the chat.
     */
    @Test
    public void addPlayerForValidPlayer() {
        PlayerRepository playerRepo = new PlayerRepository();
		try {
			playerRepo.setInitiatorPlayer(new Player("player1"));
			playerRepo.addPlayer(new Player("player2"));
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* the initiator is not empty */
        assertEquals(1, playerRepo.getPlayers().size());
    }
}
