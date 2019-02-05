package io.github.Chipbonk.Discord_Bot;

import sx.blah.discord.api.events.EventSubscriber;

import org.apache.commons.lang3.*;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.User;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.Ban;
import sx.blah.discord.util.MessageTokenizer;
import sx.blah.discord.handle.impl.obj.VoiceChannel;
import sx.blah.discord.util.MessageTokenizer.MentionToken;
import sx.blah.discord.util.MessageTokenizer.Token;
import sx.blah.discord.util.MessageTokenizer.UserMentionToken;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.*;


import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;

import sx.blah.discord.api.internal.json.objects.UserObject;
import sx.blah.discord.util.PermissionUtils;

public class CommandHandler {
	
	public static String BOT_PREFIX = "■";
	
	/*public static long getAuthor(MessageReceivedEvent event, String string) {
		IUser sender = event.getMessage().getAuthor();
		String messageAuthor = sender.toString();
	}*/
	
	private static Map<String, Command> commandMap = new HashMap<>();
	
	
	
    // Statically populate the commandMap with the intended functionality
    // Might be better practise to do this from an instantiated objects constructor
    static {
    	
    	

        commandMap.put("testcommand", (event, args) -> {
            MainRunner.sendMessage(event.getChannel(), "You ran the test command with args: " + args);
        });

        commandMap.put("ping", (event, args) -> {
            MainRunner.sendMessage(event.getChannel(), "pong");
        });
        
        commandMap.put("work", (event, args) -> {
        	MainRunner.sendMessage(event.getChannel(), "Hey I work!");
        });
        
        commandMap.put("goaway", (event, args) -> {
        	//String owner = user.getLongId;
        	//Timer logouttimer = new Timer(3000, null);
        	Timer timer = new Timer();
    		IUser sender = event.getMessage().getAuthor();
			if(sender.getStringID().equals(Main.BOT_OWNER)){
				/*logouttimer.start();
				MainRunner.sendMessage(event.getChannel(), "Logging out..");
				if(logouttimer.isRunning() == true) {
					logouttimer.stop();
        			Main.bot.logout();
        			System.exit(0);
				}*/
				MainRunner.sendMessage(event.getChannel(), "Ceasing to exist");
				timer.schedule(new TimerTask() {
	            	@Override
	            	public void run() {
	            		Main.bot.logout();
	            		System.exit(0);
	            	}
	            }, 2*1000);
			}
			else {
				MainRunner.sendMessage(event.getChannel(), "Invalid Permissions");
			}
			
        });
        commandMap.put("invite", (event, args) -> {
        	MainRunner.sendMessage(event.getChannel(), "https://discordapp.com/oauth2/authorize?client_id=504425361371824164&scope=bot&permissions=8");
        });
        
        commandMap.put("playing", (event, string) -> {
    		IUser sender = event.getMessage().getAuthor();
			if(sender.getStringID().equals(Main.BOT_OWNER)){
				Main.bot.changePresence(StatusType.ONLINE, ActivityType.PLAYING, event.getMessage().getContent().toString().substring(8));
        		MainRunner.sendMessage(event.getChannel(), "Now playing: " + event.getMessage().getContent().toString().substring(8));
			}
			else {
				MainRunner.sendMessage(event.getChannel(), "Invalid Permissions");
			}
        });
        
        commandMap.put("listening", (event, string) -> {
    		IUser sender = event.getMessage().getAuthor();
			if(sender.getStringID().equals(Main.BOT_OWNER)){
				Main.bot.changePresence(StatusType.ONLINE, ActivityType.LISTENING, event.getMessage().getContent().toString().substring(10));
        		MainRunner.sendMessage(event.getChannel(), "Now listening to: " + event.getMessage().getContent().toString().substring(10));
			}
			else {
				MainRunner.sendMessage(event.getChannel(), "Invalid Permissions");
			}
        });
        
        commandMap.put("watching", (event, string) -> {
    		IUser sender = event.getMessage().getAuthor();
			if(sender.getStringID().equals(Main.BOT_OWNER)){
				Main.bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, event.getMessage().getContent().toString().substring(9));
        		MainRunner.sendMessage(event.getChannel(), "Now watching: " + event.getMessage().getContent().toString().substring(9));
			}
			else {
				MainRunner.sendMessage(event.getChannel(), "Invalid Permissions");
			}
        });
        
        commandMap.put("streaming", (event, string) -> {
    		IUser sender = event.getMessage().getAuthor();
			if(sender.getStringID().equals(Main.BOT_OWNER)){
				Main.bot.changePresence(StatusType.ONLINE, ActivityType.STREAMING, event.getMessage().getContent().toString().substring(10));
        		MainRunner.sendMessage(event.getChannel(), "Now streaming: " + event.getMessage().getContent().toString().substring(10));
			}
			else {
				MainRunner.sendMessage(event.getChannel(), "Invalid Permissions");
			}
        });
        
        commandMap.put("kick", (event, string) -> {
        	IUser sender = event.getMessage().getAuthor();
        	Permissions kick = Permissions.KICK;
        	IGuild server = event.getGuild();
        	IMessage message = event.getMessage();
        	//MentionToken target = event.getMessage().getContent();
        	IUser target = message.getMentions().get(0);
        	
        	if(sender.getPermissionsForGuild(server).contains(kick)) {
        		MainRunner.sendMessage(event.getChannel(), message.getMentions().toString() + "has been kicked from the server.");
        		//server.kickUser((IUser) target.getMentionObject().getClient());
        		server.kickUser(target);
        	}
        	else {
        		MainRunner.sendMessage(event.getChannel(), "Insufficient permisions");
        	}
        	//if(sender.hasRole(arg0))
        	//EnumSet<Permissions> kickClient = sender.hasPermissions(Permissions.KICK, true);
        	
        });
        commandMap.put("rune2", (event, string) -> {
        	MainRunner.sendMessage(event.getChannel(), "```​ ＿＿\r\n" + 
        			"  _＿＿＿＿ |\r\n" + 
        			"  |⟍       |\r\n" + 
        			"  |  ⟍     |\r\n" + 
        			"| |    ⟍   |\r\n" + 
        			"| |      ⟍ |\r\n" + 
        			"|＿＿＿    ⟍\r\n" + 
        			"```");
        });

    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event){
    	
		IUser sender = event.getMessage().getAuthor();
		String messageAuthor = sender.toString();
		
		
    	

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply incase they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if(argArray.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if(!argArray[0].startsWith(BOT_PREFIX))
            return;

        // Extract the "command" part of the first arg out by just ditching the first character
        String commandStr = argArray[0].substring(1);

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists

        if(commandMap.containsKey(commandStr))
            commandMap.get(commandStr).runCommand(event, argsList);

    }

    /*@EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event){

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply incase they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if(argArray.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if(!argArray[0].startsWith(BOT_PREFIX))
            return;

        // Extract the "command" part of the first arg out by just ditching the first character
        String commandStr = argArray[0].substring(1);

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        // Begin the switch to handle the string to command mappings. It's likely wise to pass the whole event or
        // some part (IChannel) to the command handling it
        switch (commandStr) {

            case "test":
                testCommand(event);
                break;
                
            case "work":
            	secondTestCommand(event);
                break;
            

        }
    }


    private void testCommand(MessageReceivedEvent event){

        MainRunner.sendMessage(event.getChannel(), "You ran the test command with args: ");

    }
    
    private void secondTestCommand(MessageReceivedEvent event){
    	
    	MainRunner.sendMessage(event.getChannel(), "I wont work for you xD");
    }*/

}
