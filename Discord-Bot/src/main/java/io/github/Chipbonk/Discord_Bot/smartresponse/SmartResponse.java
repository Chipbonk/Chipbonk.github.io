package io.github.Chipbonk.Discord_Bot.smartresponse;

import java.awt.Event;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Message;
import io.github.Chipbonk.Discord_Bot.MainRunner;
import io.github.Chipbonk.Discord_Bot.smartresponse.responses.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.*;
import sx.blah.discord.handle.obj.IChannel;

public class SmartResponse {
	
	public static void Main(String[] args) {
		
	}
	
	//private static WriteToFile()
	
	/*public static String messageContent(MessageReceivedEvent event) {
		String message = event.getMessage().getContent().toString();
		return message;
	}*/	
	
	public static void checkMessageAgainstFile(String onMessageRecieved, String response, MessageReceivedEvent event) throws IOException {
		File testFile = new File("src//main//java//io//github//Chipbonk//Discord_Bot//smartresponse//responses//test.txt");
		Scanner scan = new Scanner(testFile);
		String message = event.getMessage().getContent().toString();
		//String message = new String(messageContent(null));
		
		while(scan.hasNextLine()) {
			String quote = scan.nextLine();
			MainRunner.sendMessage(event.getChannel(), "hello");
			if(StringUtils.containsIgnoreCase(message, quote)) {
				MainRunner.sendMessage(event.getChannel(), quote);
				break;
			}
			
		}
		
		
	}

}
