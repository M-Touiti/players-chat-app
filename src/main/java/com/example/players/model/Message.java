package com.example.players.model;

import java.util.Objects;

/**
 * This is a representation of message. 
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class Message {

	/* the sender of the message */
    private final Player sender;
    /* the receiver of the message */
    private final Player receiver;
    /* direction of the message
     * 1 : send message
     * 0 : receive message
     */
    private final int direction;
    /* the message content */
    private final String content;

    /**
     * Constructor: initialize the Message Class.
     * @param sender Player
     * @param receiver Player
     * @param direction int 1 for send and 0 for receive
     * @param content String message content
     */
    public Message(Player sender, Player receiver, int direction, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.direction = direction;
        this.content = content;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }    

    public int getDirection() {
		return direction;
	}
    
    public boolean valid() {
    	return ( sender!=null && !sender.empty() )
    			&&  ( receiver!=null && !receiver.empty() )
    			&&  ( direction==0 || direction==1 )
    			&&  ( content!=null && !"".equals(content) );
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return  Objects.equals(sender, message.sender) &&
                Objects.equals(receiver, message.receiver) &&
                Objects.equals(direction, message.direction) &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, content);
    }

    @Override
    public String toString() {
    	if(direction==1) {
    		return sender.getName() + " send to " + receiver.getName() + " a message : " + content;
    	}else {
    		return receiver.getName() + " received from " + sender.getName() + " a message : " + content;
    	}
    }
}