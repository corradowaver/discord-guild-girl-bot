package com.corradowaver.flexus.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

/*
  Class holds pre-built MessageEmbed response for ping command.
 */
public class PingMessages {

  private PingMessages() {

  }

  public static MessageEmbed getMessage(long latency) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Pong");
    info.setDescription("Server latency: " + latency + "ms");
    info.setColor(Color.GREEN);
    info.setFooter("powered by corradowaver");
    return info.build();
  }
}
