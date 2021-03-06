package com.corradowaver.bot.commands.handlers.art;

import com.corradowaver.bot.commands.messages.ArtMessages;
import com.corradowaver.bot.commands.services.art.ArtService;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
  Converts input string to ASCII text art.
  FONT and STRING_MAX_LENGTH are related quantities.
 */
public class ArtHandler {
  private static final String FONT = "doom";
  private static final int STRING_MAX_LENGTH = 62;

  public ArtHandler(GuildMessageReceivedEvent event, List<String> args) {
    event.getChannel().sendTyping().queue();
    MessageEmbed response;
    if (!args.isEmpty()) {
      String text = String.join("+", args);
      try {
        String art = ArtService.getAsciiArt(text, FONT);
        if (isValidForOutput(art)) {
          response = ArtMessages.getMessage(event, art);
        } else {
          response = ArtMessages.getInvalidArgumentMessage(event);
        }
      } catch (UnirestException e) {
        e.printStackTrace();
        response = ArtMessages.getErrorMessage(e.getLocalizedMessage());
      }
      event.getChannel().sendMessage(response).queue();
    }
  }

  private boolean isValidForOutput(String text) {
    int length = text.indexOf("\n");
    return (length > -1 && length < STRING_MAX_LENGTH);
  }
}
