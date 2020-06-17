package com.corradowaver.flexus;

import com.corradowaver.flexus.listeners.GuildJoinListener;
import com.corradowaver.flexus.listeners.MessageListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class GuildGirlBot {

  private static final String TOKEN = "NzE4MTI3NzA1MzU0NDAzOTAx.Xtkbfw.DICaQRnmBF1wBZEJJr_puve3Wck";

  private static JDA jda;

  private static String prefix = "!";

  public static void main(String[] args) throws LoginException {
    jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).build();
    jda.getPresence().setStatus(OnlineStatus.ONLINE);
    jda.getPresence().setActivity(Activity.listening("lofi radio"));
    jda.addEventListener(new MessageListener());
    jda.addEventListener(new GuildJoinListener());
  }

  public static String getPrefix() {
    return prefix;
  }

  public static void setPrefix(String prefix) {
    GuildGirlBot.prefix = prefix;
  }
}
