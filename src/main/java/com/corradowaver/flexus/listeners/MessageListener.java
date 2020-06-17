package com.corradowaver.flexus.listeners;

import com.corradowaver.flexus.GuildGirlBot;
import com.corradowaver.flexus.commands.handlers.InfoHandler;
import com.corradowaver.flexus.commands.handlers.PrefixHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.corradowaver.flexus.commands.Commands.*;

public class MessageListener extends ListenerAdapter {

  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    String[] args = event.getMessage().getContentRaw().split("\\s+");
    String message = args[0].toLowerCase();
    String prefix = GuildGirlBot.getPrefix();
    if (message.startsWith(prefix)) {
      String command = message.substring(prefix.length());
      switch (command) {
        case INFO -> new InfoHandler(event);
        case PREFIX -> new PrefixHandler(event, args);

        default -> {
        }
      }
    }
  }

}
