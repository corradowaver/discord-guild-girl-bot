package com.corradowaver.bot.commands.handlers.bitcoin;

import com.corradowaver.bot.commands.messages.BitcoinMessages;
import com.corradowaver.bot.commands.services.bitcoin.BitcoinService;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BitcoinHandler {
  public BitcoinHandler(GuildMessageReceivedEvent event) {
    event.getChannel().sendTyping().queue();
    MessageEmbed response;
    try {
      List<BitcoinBody> rates = BitcoinService.getRates();
      response = BitcoinMessages.getMessage(event, rates);
    } catch (UnirestException e) {
      e.printStackTrace();
      response = BitcoinMessages.getErrorMessage(e.getLocalizedMessage());
    }
    event.getChannel().sendMessage(response).queue();
  }
}
