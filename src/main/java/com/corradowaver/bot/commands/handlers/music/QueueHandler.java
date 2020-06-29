package com.corradowaver.bot.commands.handlers.music;

import com.corradowaver.bot.commands.services.music.GuildMusicManager;
import com.corradowaver.bot.helpers.TimeConverter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class QueueHandler {
  public QueueHandler(GuildMessageReceivedEvent event) {
    PlayManager playManager = PlayManager.getInstance();
    GuildMusicManager musicManager = playManager.getGuildMusicManager(event.getGuild());
    BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();

    if (!queue.isEmpty()) {
      int trackCount = Math.min(queue.size(), 20);
      List<AudioTrack> tracks = new ArrayList<>(queue);
      EmbedBuilder queueMessage = new EmbedBuilder();
      queueMessage.setTitle("Current queue: " + trackCount + " tracks");
      tracks.forEach(track -> {
        var info = track.getInfo();
        queueMessage.appendDescription(
            String.format(
                "%15s - %15s %16s\n",
                info.author,
                info.title,
                TimeConverter.longToHms(info.length))
        );
      });

      event.getChannel().sendMessage(queueMessage.build()).queue();
    }
  }
}
