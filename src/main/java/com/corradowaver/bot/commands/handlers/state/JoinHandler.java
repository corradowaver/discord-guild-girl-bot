package com.corradowaver.bot.commands.handlers.state;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinHandler {
  public JoinHandler(GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    AudioManager audioManager = event.getGuild().getAudioManager();

    if (audioManager.isConnected()) {
      channel.sendMessage("already here").queue();
      return;
    }

    GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
    assert memberVoiceState != null;

    if (!memberVoiceState.inVoiceChannel()) {
      channel.sendMessage("join a voice channel first").queue();
      return;
    }

    VoiceChannel voiceChannel = memberVoiceState.getChannel();
    Member selfMember = event.getGuild().getSelfMember();

    if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
      channel.sendMessage("missing permission to join").queue();
      return;
    }
    audioManager.openAudioConnection(voiceChannel);
    channel.sendMessage("joined").queue();
  }
}
