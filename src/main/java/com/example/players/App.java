package com.example.players;

/**
 * Players-Chat App.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class App {

	public static void main(String[] args){	
		
		ChatMonitoring monitoringChat = new ChatMonitoring(args);
			
		try {
			monitoringChat.startChat();
			monitoringChat.getChatInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	

	
}
