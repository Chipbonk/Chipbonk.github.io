package io.github.Chipbonk.Discord_Bot;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import org.apache.commons.lang3.*;

import java.util.List;

public interface Message {
	
	void grabMessage(MessageReceivedEvent event, List<String> string);

}
