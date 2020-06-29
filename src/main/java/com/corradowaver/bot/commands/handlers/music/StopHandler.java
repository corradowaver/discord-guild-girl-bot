package com.corradowaver.bot.commands.handlers.music;

import com.corradowaver.bot.commands.services.music.GuildMusicManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class StopHandler {
  public StopHandler(GuildMessageReceivedEvent event) {
    PlayManager playManager = PlayManager.getInstance();
    GuildMusicManager musicManager = playManager.getGuildMusicManager(event.getGuild());

    musicManager.scheduler.getQueue().clear();
    musicManager.player.stopTrack();
    musicManager.player.setPaused(false);

    event.getChannel().sendMessage("Stopping and clearing").queue();
  }
}
