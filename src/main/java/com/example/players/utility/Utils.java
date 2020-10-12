package com.example.players.utility;

import java.util.concurrent.TimeUnit;

/**
 * This class contains some common and shared methods among all the class of the app.
 * 
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class Utils {
	
	/**
     * This method check if the arguments sent by the user commandLine are valid.
     * @param args String[] the user input arguments
     */
	public final static boolean checkParameters(String[] args) {
		
		/* Check the nullability of the parameter */
		if(args == null) {
	    	throw new IllegalArgumentException("Arguments may not be null!");
	    }
		
		/* Check the minimum number of parameter to be accepted */
		if(args.length < 5) {
	    	throw new IllegalArgumentException("Min number of arguments is 5!");
	    }
		
		/* Check if maxMsgPerPlayer argument is a number or not. */
		if(!Utils.isInteger(args[0])) {
			throw new IllegalArgumentException("Error: maxMsgPerPlayer is not a number.");
		}
		
		/* Check if processing type argument is valid or not. */
		if(!"mono".equalsIgnoreCase(args[1]) && !"multi".equalsIgnoreCase(args[1])) {
			throw new IllegalArgumentException("Error: you must pick processing type ('mono'/'multi'). "
					+ "'mono' for monothread and 'multi' for multithread ");
		}
		
		return true;
	}
	
	/**
     * Sleep the thread for an entered number of seconds.
     * @param seconds int the sleep unit time
     */
	public final static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Check if the input string is an integer or not.
     * @param str String the input string
     */
	public final static boolean isInteger(String str) {
		/* Check the nullability of the parameter */
		if(str == null) {
	    	throw new IllegalArgumentException("parameter may not be null!");
	    }
	    try { 
	        Integer.parseInt(str); 
	    } catch(NumberFormatException e) { 
	    	System.out.println("NumberFormatException : " +e.getMessage());
	        return false; 
	    } 
	    // only got here if we didn't return false
	    return true;
	}
}
