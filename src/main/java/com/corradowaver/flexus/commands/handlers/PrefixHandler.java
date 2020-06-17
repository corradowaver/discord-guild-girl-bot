package com.corradowaver.flexus.commands.handlers;

import com.corradowaver.flexus.GuildGirlBot;
import com.corradowaver.flexus.commands.messages.PrefixMessages;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/*
  Class gets args, event in a constructor and gives response to the event
  Changes GuildGirlBot prefix to new one
 */
public class PrefixHandler {

  public PrefixHandler(GuildMessageReceivedEvent event, String[] args) {
    if (args.length == 2) {
      String newPrefix = args[1];
      GuildGirlBot.setPrefix(newPrefix);
      event.getChannel().sendMessage(PrefixMessages.getSuccessMessage()).queue();
    } else {
      event.getChannel().sendMessage(PrefixMessages.getInvalidArgumentsMessage()).queue();
    }
  }

}
