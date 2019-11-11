package io.github.Chipbonk.Discord_Bot;

import java.awt.Event;

import io.github.Chipbonk.Discord_Bot.smartresponse.SmartResponse;
import io.github.Chipbonk.Discord_Bot.games.mineSweeper.*;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.DiscordException;

public class Main {
	
	public static final IDiscordClient bot = MainRunner.createClient("*removed bot token*", true);
	
	public static void main(String[] event) {
		bot.getDispatcher().registerListener(new CommandHandler());
		bot.getDispatcher().registerListener(new Music());
		//bot.getDispatcher().registerListener(new Music2());
		//bot.getDispatcher().registerListener(new Music3());
		bot.getDispatcher().registerListener(new TextResponse());
		bot.getDispatcher().registerListener(new SmartResponse());
		bot.getDispatcher().registerListener(new Board());
		//bot.checkLoggedIn(bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, "for Alt+254, the prefix"));
		//bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, "for Alt+254, the prefix");
		
		bot.checkLoggedIn(null);
		//MainRunner.initPresence(null);
		/*if(bot.isLoggedIn() == true) {
			bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, "for Alt+254, the prefix");
		}*/
		
	}
	//public static final IDiscordClient bot = MainRunner.createClient("*removed bot token*", true);
		
	
	public static final String BOT_PREFIX = "■";
	
	public static String BOT_OWNER = "139541886888181760";
	
	public static String BOT_INVITE = "https://discordapp.com/oauth2/authorize?client_id=504425361371824164&scope=bot&permissions=8";
	
	/*public void onLogin(IDiscordClient bot) {
		bot.checkLoggedIn("504425361371824164");
		if(((IDiscordClient) bot.getOurUser()).isLoggedIn()) {
			bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, "for Alt+254, the prefix");
			
			/*try {
				bot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, "for Alt+254, the prefix");
			} catch (DiscordException e) s{
				e.printStackTrace();
			}
		}
		else {
			bot.checkReady("*removed bot token*");
		}

	}*/

	

}
