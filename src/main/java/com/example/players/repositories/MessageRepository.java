package com.example.players.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.players.model.Message;

public class MessageRepository implements IMessageRepository {

	/* message list */
    private final List<Message> messages = new ArrayList<>();

    /**
     * Constructor: initialize the MessageRepository.
     */
    public MessageRepository() {
    }

    /** 
     * add a new message
     * @param message Message
     */
    @Override
    public void addMessage(Message message) {
    }

}
