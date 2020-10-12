package com.example.players.communication.chatProcessing;

/**
 * This class implements Runnable interface. 
 * It takes in the parameter the Observer player.
 * Used to run the communication between them in a SEPARATE java process.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class ChatThread implements Runnable{
	
	/* Will hold reference to the MultiThreadPlayerObserver object */
	private Observer observer;

	/**
     * Constructor: initialize the chat Thread.
     * @param observer Observer Will hold reference to the MultiThreadPlayerObserver object
     */
	public ChatThread(Observer observer) {
		
		/*  Store the reference to the MultiThreadPlayerObserver object 
		 * so we can make calls to its methods */
		this.observer = observer;
	}
	
	/**
     * Constructor: initialize the chat Thread 
     * and start the communication between the observer.
     * @param observer Observer Will hold reference to the MultiThreadPlayerObserver object
     * @param msg String the first message to be communicated in the chat
     */
	public ChatThread(Observer observer, String msg) {
		
		/*  Store the reference to the MultiThreadPlayerObserver object 
		 * so we can make calls to its methods */
		this.observer = observer;
		
		/* start the communication and send the first message msg to all other observer */
		this.observer.startChat(msg);
	}

	/**
     * Run the thread.
     * While this observer can send a message
     * Try to receive a new message and send it back to all other observer
     */
	@Override
	public void run() {
		
		/* While this observer can send a message
		 * Try to receive a new message and send it back to all other observer
		 */
		while( ((MultiThreadPlayerObserver) this.observer).canWrite()) {
			
			((MultiThreadPlayerObserver) this.observer).sendNewMessage();
		}
	}

}
