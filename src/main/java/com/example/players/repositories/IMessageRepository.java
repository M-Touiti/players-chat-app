package com.example.players.repositories;

import com.example.players.model.Message;

/**
 * This is the Message Repository interface allows to create, find and delete Message.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface IMessageRepository {

	/** 
    * add a new message
    * @param message Message
    */
	public void addMessage(Message message);
}
