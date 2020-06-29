package com.corradowaver.bot.commands.handlers.music;

import com.corradowaver.bot.config.PropsManager;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayHandler {
  private YouTube youTube = null;
  private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

  public PlayHandler(GuildMessageReceivedEvent event, List<String> args) {
    try {
      youTube = new YouTube.Builder(
          GoogleNetHttpTransport.newTrustedTransport(),
          JacksonFactory.getDefaultInstance(),
          null
      )
          .setApplicationName("guild-girl-bot")
          .build();
    } catch (Exception e) {
      e.printStackTrace();
    }

    TextChannel channel = event.getChannel();

    if (args.isEmpty()) {
      channel.sendMessage("Provide url").queue();
      return;
    }

    String inputTrack = args.get(0);

    if (!isUrl(inputTrack)) {
      String ytSearched = searchYoutube(inputTrack);
      if (ytSearched == null) {
        channel.sendMessage("Youtube returned no results").queue();
        return;
      }
      inputTrack = ytSearched;
    }

    PlayManager manager = PlayManager.getInstance();
    manager.loadAndPlay(event.getChannel(), inputTrack);
    manager.getGuildMusicManager(event.getGuild()).player.setVolume(50);
  }

  @Nullable
  private String searchYoutube(String input) {
    try {
      List<SearchResult> results = youTube.search()
          .list("id,snippet")
          .setQ(input)
          .setMaxResults(1L)
          .setType("video")
          .setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)")
          .setKey(PropsManager.getProps().getProperty("google.youtube.api.key"))
          .execute()
          .getItems();

      if (!results.isEmpty()) {
        String videoId = results.get(0).getId().getVideoId();

        return YOUTUBE_URL + videoId;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
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
