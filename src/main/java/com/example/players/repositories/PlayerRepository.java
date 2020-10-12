package com.example.players.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.players.exceptions.PlayerException;
import com.example.players.exceptions.PlayerNotFoundException;
import com.example.players.model.Player;

/**
 * This class implements IPlayerRepository interface. 
 * It allows to manage players
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class PlayerRepository implements IPlayerRepository {

	/* this is the initiator player of the chat */
	private Player initiatorPlayer = new Player("");
	
	/* other players of the chat */
    private final List<Player> players = new ArrayList<>();    
    
    /**
     * Constructor: initialize the PlayerRepository.
     */
    public PlayerRepository() {
		super();
	}

    /**
     * This method set the initiator player of the chat
     * @param player Player
     */
	@Override
    public void setInitiatorPlayer(Player player) throws PlayerException {
		/* check if the player is valid */
		if(player == null || player.empty()) {
			throw new PlayerNotFoundException("Initiator Player not found, enter a valid player");
		}
		/* if exist already an initiator, we can't set it again
		 * In the service layer, we can delete and change the initiator of the chat
		 */
    	if(!this.initiatorPlayer.empty()) {
    		throw new PlayerException("Already exists an initiator player");
    	}
    	this.initiatorPlayer = player;
    }

	/**
     * This method add a player to the chat
     * @param player Player
     */
    @Override
    public void addPlayer(Player player) throws PlayerException {
    	/* check if the player is valid */
		if(player == null || player.empty()) {
			throw new PlayerNotFoundException("player not found, enter a valid player");
		}
    	/* if there is no initiator of the chat, we can't add a player to the chat
    	 * we shoud initialize the initiator of the chat first.
    	 */
    	if(this.initiatorPlayer.empty()) {
    		throw new PlayerException("Initiator player not found");
    	}
    	/* check if this player is already joined the chat */
        if(initiatorPlayer.equals(player)  || players.contains(player)) {
            throw new PlayerException("the player " + player.getName()  + " already joined the chat");
        }
        /* add the player to the chat player list */
        players.add(player);
    }
    
    /**
     * Get the initiator player of the chat
     */
    @Override
	public Player getInitiatorPlayer() {
		return initiatorPlayer;
	}

    /**
     * Get the observer players of the chat
     */
    @Override
	public List<Player> getPlayers() {
		return players;
	}    
    
    /**
     * Find a player by his name and return it back
     * @param playerName String
     */
    @Override
    public Player findPlayerByName(String playerName) {
    	
    	/* First check if he is the initiator player */
    	if(initiatorPlayer.getName().equalsIgnoreCase(playerName)) {
    		return initiatorPlayer;
    	}
    	
    	/* Check the other player */
    	Optional<Player> matchingObject = players.stream().
    		    filter(p -> p.getName().equalsIgnoreCase(playerName)).
    		    findFirst();
    	
    	/* if the player is found the return it back
    	 * otherwise return null
    	 */
    	Player player = matchingObject.orElse(null);
    	
    	return player;
    }
    
    /**
     * Delete a player from the chat
     * @param deletePlayer Player
     */
    public void deletePlayer(Player deletePlayer) {
    	/* check if the entered player is valid */
		if(deletePlayer == null || deletePlayer.empty()) {
			throw new PlayerNotFoundException("player not found, enter a valid player");
		}
    	int playerIndex = players.indexOf(deletePlayer);
    	if(playerIndex==-1) {
    		throw new PlayerNotFoundException("player " + deletePlayer.getName() + " not found in the chat");
    	}
    	players.remove(playerIndex);
    } 
}