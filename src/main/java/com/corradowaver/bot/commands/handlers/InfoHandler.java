package com.corradowaver.bot.commands.handlers;

import com.corradowaver.bot.commands.messages.InfoMessages;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/*
  Class gets event in a constructor and gives response to the event
 */
public class InfoHandler {

  public InfoHandler(GuildMessageReceivedEvent event) {
    event.getChannel().sendTyping().queue();
    event.getChannel().sendMessage(InfoMessages.getMessage(event)).queue();
  }

}
