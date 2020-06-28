package com.corradowaver.bot.commands.handlers.music;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayHandler {
  public PlayHandler(GuildMessageReceivedEvent event, List<String> args) {

    TextChannel channel = event.getChannel();

    if (args.isEmpty()) {
      channel.sendMessage("Provide url").queue();
      return;
    }

    String trackUrl = args.get(0);

    if (!isUrl(trackUrl)) {
      channel.sendMessage("Provide valid url").queue();
      return;
    }

    PlayManager manager = PlayManager.getInstance();
    manager.loadAndPlay(event.getChannel(), trackUrl);
    manager.getGuildMusicManager(event.getGuild()).player.setVolume(50);
  }

  private boolean isUrl(String string) {
    try {
      new URL(string);
      return true;
    } catch (MalformedURLException e) {
      return false;
    }
  }
}
