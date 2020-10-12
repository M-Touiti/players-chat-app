package com.example.players.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.players.exceptions.PlayerException;
import com.example.players.exceptions.PlayerNotFoundException;
import com.example.players.model.Player;
import com.example.players.repositories.PlayerRepository;

/**
 * Unit tests for PlayerServiceTest class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class PlayerServiceTest {

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
			// TODO Auto-generated catch block
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
     * Should return 1, we can't add a player if it is already the initiator.
     */
    @Test
    public void addPlayerForAPlayerAlreadyJoinedAsInitiator() {
    	PlayerService playerService = new PlayerService();
		
		playerService.setInitiatorPlayer("player1");
		
		playerService.addPlayer("player1");
		
		playerService.addPlayer("player2");
		
		/* Number of observer player */
		assertEquals(1, playerService.getObserverPlayers().size());
    }
    
    /**
     * Should return 1, we can't add a player if it is already exist.
     */
    @Test
    public void addPlayerForAPlayerJoinedTheChat() {
    	PlayerService playerService = new PlayerService();
		
		playerService.setInitiatorPlayer("player1");
		
		playerService.addPlayer("player2");
		
		playerService.addPlayer("player2");
		
		/* Number of observer player */
		assertEquals(1, playerService.getObserverPlayers().size());
    }
    
    /**
     * Should return 1, we can set a valid initiator of the chat.
     */
    @Test
    public void addPlayerForValidPlayer() {
    	PlayerService playerService = new PlayerService();
		
		playerService.setInitiatorPlayer("player1");
		playerService.addPlayer("player2");
		
		/* Number of observer player */
        assertEquals(1, playerService.getObserverPlayers().size());
    }
    
    /**
     * Should throw IllegalArgumentException. Null player list name parameter.
     */
    @Test
    public void addPlayerListForNullPlayer() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			PlayerService playerService = new PlayerService();
        			playerService.addPlayerList(null);
                }
        );
    } 
    
    /**
     * Should throw IllegalArgumentException. Null player name parameter.
     */
    @Test
    public void findPlayerForNullPlayerName() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			PlayerService playerService = new PlayerService();
        			playerService.find(null);
                }
        );
    } 
    
    /**
     * Should throw IllegalArgumentException. empty player name parameter.
     */
    @Test
    public void findPlayerForEmptyPlayerName() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			PlayerService playerService = new PlayerService();
        			playerService.find("");
                }
        );
    }
    
    /**
     * Should return true, find player by name.
     */
    @Test
    public void findPlayerForValidPlayerName() {
    	PlayerService playerService = new PlayerService();
		
		playerService.setInitiatorPlayer("player1");
		
		Player player2 = new Player("player2");
		Player player3 = new Player("player3");
		
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		
        assertTrue(player2.equals( playerService.find("player2") ) );
    }
}
