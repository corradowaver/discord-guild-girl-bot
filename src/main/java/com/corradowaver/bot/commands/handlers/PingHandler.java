package com.corradowaver.bot.commands.handlers;

import com.corradowaver.bot.commands.messages.PingMessages;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.OffsetDateTime;

public class PingHandler {
  public PingHandler(GuildMessageReceivedEvent event) {
    event.getChannel().sendTyping().queue();
    long messageTime = event.getMessage().getTimeCreated().toInstant().toEpochMilli();
    long now = OffsetDateTime.now().toInstant().toEpochMilli();
    long latency = now - messageTime;
    event.getChannel().sendMessage(PingMessages.getMessage(latency)).queue();
  }
}
