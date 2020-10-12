package com.example.players.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.players.exceptions.PlayerNotFoundException;
import com.example.players.model.Player;
import com.example.players.services.IPlayerService;

/**
 * This is the ChatMonitoring class. 
 * It maintains the communication between the application and the user.
 * It reads some information and chosen option from the user console.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class ConsoleManager {
	
	private final static Scanner scanner = new Scanner(System.in);
	
	/**
     * this method allows the user to enter a valid player name.
     * @param playerService IPlayerService it uses the playerService 
     * 				to check if the entered player name is valid or not.
     * @param msg String it is the message to display to the user
     */
	public final static Player enterPlayerName(IPlayerService playerService, String msg) {
		
		if(playerService == null || msg == null || "".equals(msg)) {
			throw new IllegalArgumentException("Parameters may not be null or empty!");
		}
		
		boolean playerFound;
		Player player;
		
		/* repeat until the player is found */
		do {
			playerFound = true;
			
			/* read the entered player name from the console */
			String playerName = ConsoleManager.readString(msg);
			
			/* check if the entered player name is valid or not */
			player=null;
			try {
			player = playerService.find(playerName);
			}catch(PlayerNotFoundException e){
				player = null;
			}
			
			/* If the entered player name is not Valid, 
			 * ask the user if he want to continue choosing another name
			 */
			if(player==null) {
				playerFound = false;
				System.out.println("Player '" + playerName + "' Not Found");
				String respone = ConsoleManager.readStringWithOption("Do you want to choose another name (y/n)", 
						Arrays.asList("y", "n"));
				
				/* if the response = "n", then quit */
				if("n".equals(respone)) {
					playerFound = true;
				}
			}			
		} while (playerFound == false);
		
		/* return the player if it is exist otherwise null */
		return player;
	}
	
	/**
     * this method allows the user to enter a valid number in the interval (from , to).
     * @param msg String it is the message to display to the user
     * @param from int the entered number must be greater or equal to this
     * @param to int the entered number must be less or equal to this
     */
	public final static int readInt(String msg, int from, int to) {
		
		if(msg == null || "".equals(msg)) {
			throw new IllegalArgumentException("Message may not be null or empty!");
		}
		
		int chosenOption = 0;
		do {
			System.out.println(msg);
			if(scanner.hasNextInt()) {
				chosenOption = scanner.nextInt();	
				scanner.nextLine();
			}else {
				scanner.next();
			}
		} while (chosenOption < from || chosenOption>to);

		return chosenOption;
	}
	
	/**
     * this method allows the user to enter a string.
     * @param msg String it is the message to display to the user
     */
	public final static String readString(String msg) {
		
		if(msg == null || "".equals(msg)) {
			throw new IllegalArgumentException("Message may not be null or empty!");
		}
		
		String input = "test";
		do {
			System.out.println(msg);
			if(scanner.hasNextLine()) {
				input = scanner.nextLine();
			}
		}while("".equals(input));
				
		return input;
	}
	
	/**
     * this method allows the user to enter a valid string and must be in the validOption.
     * @param msg String it is the message to display to the user
     * @param validOption the entered string must be in the validOption
     */
	public final static String readStringWithOption(String msg, List<String> validOption) {
		
		if(msg == null || "".equals(msg)) {
			throw new IllegalArgumentException("Message may not be null or empty!");
		}
		
		if(validOption == null || validOption.isEmpty()) {
			throw new IllegalArgumentException("validOption may not be null or empty! enter a valid char List");
		}
		String str = "";
		
		do {
			System.out.println(msg);
			if(scanner.hasNext()) {
				str = scanner.next();
				scanner.nextLine();
			}
			
		} while (!validOption.contains(str));		
		
		return str;
	}

	/**
     * Clear the console.
     */
	public final static void clearConsole()
	{
//		System.out.print("\033[H\033[2J");
		System.out.flush();
		try {
		      final String os = System.getProperty("os.name");
		      System.out.println("OS :: " + os);
		      if (os.contains("Windows")) {
		          Runtime.getRuntime().exec("cls");		          
		      } else {
		          Runtime.getRuntime().exec("clear");
		      }
		    } catch (final Exception e) {
		        System.out.println("something went wrong :(");
		    }
	}
}
