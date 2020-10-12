package com.example.players.communication.chatProcessing;

/**
 * This interface handles adding, deleting and send message to all Observers.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public interface Subject {

	/**
     * Add a new observer to the List.
     * @param newObserver Observer to be added
     */
	public void register(Observer o);

	/**
     * Delete an observer from the List.
     * @param deleteObserver Observer to be deleted
     */
	public void unregister(Observer o);

	/**
     * chat and send message to all other observer
     * @param currentMsgWriter Observer the sender of the message
     * @param msg String the message content
     */
	public void chat(Observer currentMsgWriter, String msg);
}
