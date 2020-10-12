package com.example.players.services;

import java.util.ArrayList;
import java.util.List;

import com.example.players.exceptions.PlayerException;
import com.example.players.exceptions.PlayerNotFoundException;
import com.example.players.model.Player;
import com.example.players.repositories.IPlayerRepository;
import com.example.players.repositories.PlayerRepository;

/**
 * This class implements IPlayerService interface. 
 * It allows to manage players
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class PlayerService implements IPlayerService {

	/* the Player repository, which store and manage the players */
	private final IPlayerRepository playerRepository = new PlayerRepository();
	
	/**
     * Constructor: initialize the PlayerService.
     */
	public PlayerService() {
    }
	
	/**
     * This method set the initiator player of the chat by his name
     * @param playerName String
	 * @throws PlayerException 
     */
	@Override
	public void setInitiatorPlayer(String playerName) {		
		try {
			this.playerRepository.setInitiatorPlayer(new Player(playerName));
			System.out.println("new Player '" + playerName +"' has been joined as the initiator of the chat");
		} catch (PlayerException e) {
			e.printStackTrace();
		}
		
    }
	
	/**
     * This method set the initiator player of the chat
     * @param player Player
     */
	public void addInitiatorPlayer(Player player) {
		try {
			this.playerRepository.setInitiatorPlayer(player);
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
     * This method add a player to the chat by his name
     * @param player Player
     */
	public void addPlayer(String playerName) {
    	try {
			this.playerRepository.addPlayer(new Player(playerName));
			System.out.println("new Player '" + playerName +"' has been joined to chat");
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * This method add a player to the chat
     * @param player Player
     */
    public void addPlayer(Player player) {
    	try {
			this.playerRepository.addPlayer(player);
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * add the player list of the chat
     * @param playerListName playerName List
     */
    @Override
    public void addPlayerList(List<String> playerListName) {
    	if(playerListName==null) {
    		throw new IllegalArgumentException("player list name may not be null!");
    	}
    	playerListName.forEach( playerName -> addPlayer(playerName)  );
    }
    
    /**
     * Find a player by his name and return it back
     * @param playerName String
     */
    @Override
    public Player find(String playerName) {
    	
    	if(playerName == null || "".equals(playerName)) {
			throw new IllegalArgumentException("player name may not be null!");
		}
    	
    	Player player = this.playerRepository.findPlayerByName(playerName);
    	
    	if(player == null) {
    		throw new PlayerNotFoundException("player '" + playerName + "' not found");
    	}
    	
    	return player;
    }
    
    /**
     * Get the initiator player of the chat
     */
    @Override
	public Player getInitiatorPlayer() {
		return this.playerRepository.getInitiatorPlayer();
	}
    
    /**
     * Get the player list of the chat
     */
    @Override
	public List<Player> getObserverPlayers() {
		return this.playerRepository.getPlayers();
	} 
    
    /**
     * Get all the players of the chat
     */
    @Override
	public List<Player> getAllPlayers() {
    	List<Player> playerList = new ArrayList<Player>();
		playerList.add(this.getInitiatorPlayer());
		playerList.addAll(this.getObserverPlayers() );
		return playerList;
	}
    
    
    
    /**
     * Check if a player is exist in the chat
     * @param player Player
     */
    public boolean exist(Player player) {
    	/* check if the player is valid */
		if(player == null || player.empty()) {
			throw new PlayerNotFoundException("player not found, enter a valid player");
		}
    	/* check if he is the initiator of the chat */
    	if(this.getInitiatorPlayer().equals(player)) {
    		return true;
    	}
    	/* check the other players */
    	if(this.getObserverPlayers().contains(player)) {
    		return true;
    	}
    	return false;
    }
    
//    public boolean existPlayer(String playerName) {
//    	if(this.getInitiatorPlayer().getName().equals(playerName)) {
//    		return true;
//    	}
//    	
//    	return this.getObserverPlayers().stream()
//		    							.filter(o -> o.getName().equals(playerName))
//		    							.findFirst()
//		    							.isPresent();
//    }
    
//    public void displayMessage(String playerName) {
//    	Player player = this.find(playerName);
//    	if(player != null) {
//    		System.out.println("\n---------------- " + player.getName() + " Logger -------------\n");
//    		player.getMessageLogger().displayMessages();
//    	}
//    }    
    
//    public void displayPlayerStatistics(String playerName) {
//		Player player = this.find(playerName);
//    	if(player != null) {
//    		player.getMessageLogger().displayStatisticLoggedMessageByPlayer(player);
//    	}
//	}  
    
//    public void changePlayerToInitiator(Player player) {
//    	if(!exist(player)) {
//    		throw new PlayerNotFoundException("player not found");
//    	}
//    	if(!this.getInitiatorPlayer().equals(player)) {
//    		Player currentInitiatorPlayer = getInitiatorPlayer();
//    		deletePlayer(player);
//    		setInitiatorPlayer(player);
//    		addPlayer(currentInitiatorPlayer);
//    	}
//    }
//    
//    public void deletePlayer(Player deletePlayer) {
//    	this.playerRepository.deletePlayer(deletePlayer);
//    }
//
//    
//
//	public void setInitiatorPlayer(Player initiatorPlayer) {
//		try {
//			this.playerRepository.setInitiatorPlayer(initiatorPlayer);
//		} catch (PlayerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public List<Player> getAllPlayers() {
//		List<Player> players = new ArrayList<Player>();
//		players.add(this.getInitiatorPlayer());
//		players.addAll(this.getObserverPlayers());
//		return players;
//	}

	

	
}
