package com.example.players.exceptions;

public class PlayerNotFoundException extends IllegalArgumentException {

	public PlayerNotFoundException(String message) {
        super(message);
    }
}
