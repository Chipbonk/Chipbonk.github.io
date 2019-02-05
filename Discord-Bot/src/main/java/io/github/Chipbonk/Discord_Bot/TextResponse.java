package io.github.Chipbonk.Discord_Bot;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TextResponse {
	
	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
	}

	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {
		//String[] properGrammar = {"i'm","i'll"};
		//List of words to react to replace
		//boolean containsIm = StringUtils.containsIgnoreCase(event.getMessage().getContent(), "i'm"); //<--- Change this
		
		/*if(containsIm == true) {
			MainRunner.sendMessage(event.getChannel(), "I am*");
		}*/
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "i'm") == true) {
			MainRunner.sendMessage(event.getChannel(), "I am*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), " im ") == true) {
			MainRunner.sendMessage(event.getChannel(), "I am*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "im ") == true) {
			MainRunner.sendMessage(event.getChannel(), "I am*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "can't") == true) {
			MainRunner.sendMessage(event.getChannel(), "Can not*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), " cant") == true) {
			MainRunner.sendMessage(event.getChannel(), "Can not*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "i'll") == true) {
			MainRunner.sendMessage(event.getChannel(), "I will*");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "kevin") == true) {
			MainRunner.sendMessage(event.getChannel(), "What you sayin' about me ol' chum?");
		}
		if(StringUtils.containsIgnoreCase(event.getMessage().getContent(), "doesn't work") == true) {
			MainRunner.sendMessage(event.getChannel(), "You don't know that!");
		}
	}
}