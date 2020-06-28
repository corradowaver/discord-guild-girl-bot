package com.corradowaver.bot.listeners;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.commands.handlers.InfoHandler;
import com.corradowaver.bot.commands.handlers.PingHandler;
import com.corradowaver.bot.commands.handlers.PrefixHandler;
import com.corradowaver.bot.commands.handlers.art.ArtHandler;
import com.corradowaver.bot.commands.handlers.bitcoin.BitcoinHandler;
import com.corradowaver.bot.commands.handlers.images.ImageSearchHandler;
import com.corradowaver.bot.commands.handlers.music.PlayHandler;
import com.corradowaver.bot.commands.handlers.state.JoinHandler;
import com.corradowaver.bot.commands.handlers.state.LeaveHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.corradowaver.bot.commands.Commands.*;

public class MessageListener extends ListenerAdapter {

  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    String[] args = event.getMessage().getContentRaw().split("\\s+");
    String message = args[0].toLowerCase();
    String prefix = GuildGirlBot.getPrefix();
    List<String> preparedArgs = Arrays.stream(args).skip(1).collect(Collectors.toList());
    if (message.startsWith(prefix)) {
      String command = message.substring(prefix.length());
      switch (command) {
        case INFO -> new InfoHandler(event);
        case PREFIX -> new PrefixHandler(event, preparedArgs);
        case SEND -> new ImageSearchHandler(event, preparedArgs);
        case PING -> new PingHandler(event);
        case BTC -> new BitcoinHandler(event);
        case ART -> new ArtHandler(event, preparedArgs);
        case JOIN -> new JoinHandler(event);
        case LEAVE -> new LeaveHandler(event);
        case PLAY -> new PlayHandler(event, args);
        default -> {
        }
      }
    }
  }

}
