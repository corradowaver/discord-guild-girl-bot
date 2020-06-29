package com.corradowaver.bot.helpers;

import java.util.concurrent.TimeUnit;

public class TimeConverter {

  private TimeConverter(){

  }

  public static String longToHms(long millis) {
    return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
  }
}
