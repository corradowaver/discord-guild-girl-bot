package com.corradowaver.bot.commands.handlers;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.commands.messages.PrefixMessages;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/*
  Class gets args, event in a constructor and gives response to the event
  Changes GuildGirlBot prefix to new one
 */
public class PrefixHandler {
  private final static String regex = "^[a-zA-Z0-9!#?*%$^]+$";

  public PrefixHandler(GuildMessageReceivedEvent event, String[] args) {
    if (args.length == 2 && args[1].matches(regex)) {
      String newPrefix = args[1];
      GuildGirlBot.setPrefix(newPrefix);
      event.getChannel().sendMessage(PrefixMessages.getSuccessMessage()).queue();
    } else {
      event.getChannel().sendMessage(PrefixMessages.getInvalidArgumentsMessage()).queue();
    }
  }

}
