package com.corradowaver.bot.commands.handlers.state;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveHandler {
  public LeaveHandler(GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    AudioManager audioManager = event.getGuild().getAudioManager();

    if (!audioManager.isConnected()) {
      channel.sendMessage("i'm not here").queue();
      return;
    }

    VoiceChannel voiceChannel = audioManager.getConnectedChannel();
    assert voiceChannel != null;

    if (!voiceChannel.getMembers().contains(event.getMember())) {
      channel.sendMessage("join my voice channel first").queue();
      return;
    }

    audioManager.closeAudioConnection();
    channel.sendMessage("left this chat").queue();
  }
}
