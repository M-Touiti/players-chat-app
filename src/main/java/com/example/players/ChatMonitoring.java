package com.example.players;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.example.players.chatRoom.ChatRoom;
import com.example.players.chatRoom.MonoThreadChat;
import com.example.players.chatRoom.MultiThreadChat;
import com.example.players.exceptions.PlayerException;
import com.example.players.model.Player;
import com.example.players.utility.ConsoleManager;
import com.example.players.utility.Utils;

/**
 * This is the ChatMonitoring class. 
 * It takes in the parameter the arguments sent by the user command line 
 * And parses this parameter to build the Chat Room instance.
 * Then, it uses the method startChat() to start the communication between the players.
 * When the conversation is finished, it use the method getChatInfo displays a menu 
 * and through this menu we can choose and display some chat statistics.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class ChatMonitoring {

	/* All the treatment of chat is initialized here, in the chatRoom class  */
	private ChatRoom chatRoom;
	
	/* First message to communicate in the chat */
	private String firstMessageToSent;	
	
	private boolean initializedChat = false;
	
	/**
     * Constructor: initialize the ChatRoom class
     * @param args the arguments sent by the user command line.
	 * @throws PlayerException 
     */
	public ChatMonitoring(String[] args) {		
		buildChatRoom(args);		
	}	
	
	/**
     * this method build the ChatRoom Class.
     * First, it check if the arguments sent by the user commandLine are valid.
     * Then, it starts build the chatRoom by initializing the maxMsgPerPlayer
     * check the processing type, if it would be mono-threading or multi-threading
     * Finally, retrieves the list of players and initialize them in the chatRoom
     * @param args String[] the user input arguments
	 * @throws PlayerException 
     */
	public void buildChatRoom(String[] args) {
		
		/* check if the arguments sent by the user commandLine are valid. */
		Utils.checkParameters(args);
		
		/* parse and set the max Msg can be sent or received by Player*/
		int maxMsgPerPlayer = 0;
		if(Utils.isInteger(args[0])) {
			maxMsgPerPlayer = Integer.parseInt(args[0]);
		}		
		
		/* check and initialize the processing type, 
		 * if it would be mono-threading or multi-threading */
		if("mono".equalsIgnoreCase(args[1])) {
			chatRoom =  new MonoThreadChat(maxMsgPerPlayer);
		}else {
			chatRoom =  new MultiThreadChat(maxMsgPerPlayer);
		}
		
		List<String> argsList = Arrays.asList(args);		
		/* retrieve the list of observer Players from the entered arguments */
		List<String> observerPlayers = argsList.stream().skip(4)
	            .collect(Collectors.toList());
		
		/* initialize the InitiatorPlayer */
		chatRoom.addInitiatorPlayer(args[3]);
		
		
		/* initialize the observerPlayers */
		chatRoom.addPlayerList(observerPlayers);
		
		/* initialize the first message to be communicated in the chat */
		firstMessageToSent = args[2];
		
		this.initializedChat = true;
	}
	
	/**
     * this method starts the communication between the players and
     * display the exchanged messages notification list
	 * @throws Exception 
     */
	public void startChat() throws Exception {
		if(!initializedChat || !chatRoom.canStartChat()) {
			throw new Exception("Chat is not initialized !");
		}
		chatRoom.startCommunication(firstMessageToSent);
		chatRoom.displayChatMessages();
		System.out.println("end of chat.\n");		
	}
	
	/**
     * this method displays a menu in the aims to choose and display some chat statistics.
	 * @throws Exception 
     */
	public void getChatInfo() throws Exception {
		if(!initializedChat) {
			throw new Exception("Chat is not initialized !");
		}
		
		int chosenOption;
		do {
			
			/* displays a menu that helps display some chat statistics 
			 * and retrieve the selected option from the menu
			 */
			chosenOption = chooseMenuOption();
			
			/* Now, we treat the desired and selected option */
			Player player;
			switch (chosenOption) {			  
			  case 1:
				  /* if chosen option = 1, display the chat statistics of all the players*/
				chatRoom.displayPlayersStatistics();
			    break;
			  case 2:
				  /* if chosen option = 2, display the chat statistics for a specific player 
				   * First, we retrieve the player from the enteredPlayerName
				   * and we display his chat statistics.
				   */
				 player = ConsoleManager.enterPlayerName(chatRoom.getPlayerService(), "\nEnter a player name : ");
				 if(player != null) {
					 chatRoom.displayPlayerStatistics(player);
				 }				
			    break;
			  case 3:
				  /* if chosen option = 3, display the chat history of all the players*/
				  chatRoom.displayChatMessages();
			    break;
			  case 4:
				  /* if chosen option = 2, display the chat history for a specific player 
				   * First, we retrieve the player from the enteredPlayerName
				   * and we display his chat history.
				   */
				  player = ConsoleManager.enterPlayerName(chatRoom.getPlayerService(), "\nEnter a player name : ");
					 if(player != null) {
						 chatRoom.displayPlayerMessages(player);
					 }
			    break;
			  case 5:
				  System.out.println("\n\nGoodBye...");
				  break;
			}
			/* if chosen option = 2, then quit */
		} while (chosenOption != 5);
	}
	
	
	/**
     * this method displays a menu.
     * return the selected option.
     */
	public int chooseMenuOption() {
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/* display the menu items*/
		System.out.println("\n##################### MENU #####################");
		System.out.println("\nIf you want to see some statistics of the chat, choose the desired option in the menu below :");
		System.out.println("1. Chat statistics of all the players ");
		System.out.println("2. Chat statistics of a specific player ");
		System.out.println("3. Chat history of all the players ");
		System.out.println("4. Chat history of a specific player ");
		System.out.println("5. Quit.\n");		
		
		
		/* return the entered and picked option number */
		return ConsoleManager.readInt("Enter the chosen option (pick a number between 1 et 5) : "
						, 1, 5);
	}	
	
	
	
	
}
