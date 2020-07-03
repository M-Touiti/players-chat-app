package com.example.players.model;

/**
 * This is a representation of player. 
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class Player {
	
	/* The name of the player */
	private final String name;
	
	/**
     * Constructor: initialize the Player Class.
     * @param name String player name
     */
	public Player(String name) {
		this.name = name;
	}
	
	/** 
     * Check if this player is null or has an empty name.
     */
	public boolean empty() {
		return this == null || "".equals(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	
}