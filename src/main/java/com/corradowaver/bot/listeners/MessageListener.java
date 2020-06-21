package com.corradowaver.bot.listeners;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.commands.handlers.PingHandler;
import com.corradowaver.bot.commands.handlers.InfoHandler;
import com.corradowaver.bot.commands.handlers.PrefixHandler;
import com.corradowaver.bot.commands.handlers.image.ImageSearchHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.corradowaver.bot.commands.Commands.*;

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
        case SEND -> new ImageSearchHandler(event, args);
        case PING -> new PingHandler(event);
        default -> {
        }
      }
    }
  }

}
