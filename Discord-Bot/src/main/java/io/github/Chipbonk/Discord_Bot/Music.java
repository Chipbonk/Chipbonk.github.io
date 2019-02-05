package io.github.Chipbonk.Discord_Bot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IIDLinkedObject;
import sx.blah.discord.util.audio.AudioPlayer;
import sx.blah.discord.api.internal.DiscordVoiceWS;
import sx.blah.discord.api.internal.*;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;
import sx.blah.discord.handle.impl.events.guild.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.*;
import javax.sound.sampled.*;

public class Music {

	// A static map of commands mapping from command string to the functional impl
    private static Map<String, Command> commandMap = new HashMap<>();

    // Statically populate the commandMap with the intended functionality
    // Might be better practise to do this from an instantiated objects constructor
    static {
    	
	//AudioPlayer audioP = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
      //  AudioPlayer audioP2 = AudioPlayer.getAudioPlayerForGuild(event.getGuild());

        // If the IUser that called this is in a voice channel, join them
        commandMap.put("joinvoice", (event, args) -> {

            IVoiceChannel userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();
            Timer timer = new Timer();

          
            if(userVoiceChannel == null)
                return;

            userVoiceChannel.join();
            
            AudioPlayer audioP = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
            AudioPlayer audioP2 = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
            
            File[] songDirSpawn = new File("music").listFiles(file -> file.getAbsolutePath().contains("CubeSpawn"));
            
            
            
            try {
				audioP.queue(songDirSpawn[0]);
			} catch (IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            File[] songDirVoice1 = new File("music").listFiles(file -> file.getAbsolutePath().contains("CubePrintVoice1"));
            File[] songDirVoice2 = new File("music").listFiles(file -> file.getAbsolutePath().contains("CubePrintVoice2"));
            
            timer.scheduleAtFixedRate(new TimerTask() {
            	@Override
            	public void run() {
            		try {
            			audioP2.queue(songDirVoice2[0]);
            		}catch (IOException | UnsupportedAudioFileException e) {
            			e.printStackTrace();
            		}
            	}
            }, 4*1000*8, 4*1000*8);
            
            timer.scheduleAtFixedRate(new TimerTask() {
            	@Override
            	public void run() {
            		try {
            			audioP.queue(songDirVoice1[0]);
            		}catch (IOException | UnsupportedAudioFileException e) {
            			e.printStackTrace();
            		}
            	}
            }, 4*1001*7, 4*1001*7);
            
            File[] songDirAmb = new File("music").listFiles(file -> file.getAbsolutePath().contains("CubeAmbient1"));
            
            timer.scheduleAtFixedRate(new TimerTask() {
            	@Override
            	public void run() {
            		try {
            			audioP2.queue(songDirAmb[0]);
            		} catch (IOException | UnsupportedAudioFileException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            	}
            }, 11064, 11064);

        });

        commandMap.put("leavevoice", (event, args) -> {
            AudioPlayer audioP = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
            AudioPlayer audioP2 = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
            IVoiceChannel botVoiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
            audioP.clear();
            audioP2.clear();
            IIDLinkedObject botVoiceChanel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();            
            if(botVoiceChannel != null) {
            	botVoiceChannel.leave();
            	botVoiceChannel = null;
            }
                

            

        });

        // Plays the first song found containing the first arg
        commandMap.put("playsong", (event, args) -> {

            IVoiceChannel botVoiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();

            
            
            if(botVoiceChannel == null) {
                MainRunner.sendMessage(event.getChannel(), "Not in a voice channel, join one and then use joinvoice");
                botVoiceChannel = null;
            }

            // Turn the args back into a string separated by space
            String searchStr = String.join(" ", args);

            // Get the AudioPlayer object for the guild
            AudioPlayer audioP3 = AudioPlayer.getAudioPlayerForGuild(event.getGuild());

            // Find a song given the search term
            File[] songDir = new File("music")
                    .listFiles(file -> file.getAbsolutePath().contains(searchStr));

            if(songDir == null || songDir.length == 0)
                return;

            // Stop the playing track
            audioP3.clear();

            // Play the found song
            try {
                audioP3.queue(songDir[0]);
            } catch (IOException | UnsupportedAudioFileException e) {
                MainRunner.sendMessage(event.getChannel(), "There was an issue playing that song.");
                e.printStackTrace();
            }

            MainRunner.sendMessage(event.getChannel(), "Now playing: " + songDir[0].getName());

        });

    }

    @EventSubscriber
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
        if(!argArray[0].startsWith(Main.BOT_PREFIX))
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

}
