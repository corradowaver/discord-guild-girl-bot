package com.corradowaver.bot.commands.handlers.images;

import com.corradowaver.bot.commands.messages.ImageMessages;
import com.corradowaver.bot.commands.services.images.ImageService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ImageSearchHandler {

  public ImageSearchHandler(GuildMessageReceivedEvent event, String[] args) {
    MessageEmbed response;
    if (args.length >= 2) {
      try {
        String searchQuery = Arrays.stream(args).skip(1).collect(Collectors.joining(" "));
        response = ImageMessages.getImageMessage(event, ImageService.searchImages(searchQuery));
      } catch (Exception e) {
        e.printStackTrace();
        response = ImageMessages.getErrorMessage(e.getLocalizedMessage());
      }
    } else {
      response = ImageMessages.getInvalidArgumentsMessage();
    }
    event.getChannel().sendTyping().queue();
    event.getChannel().sendMessage(response).queue();
  }
}
