package com.example.players.exceptions;

public class PlayerNotFoundException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 634081669179625830L;

	public PlayerNotFoundException(String message) {
        super(message);
    }
}
