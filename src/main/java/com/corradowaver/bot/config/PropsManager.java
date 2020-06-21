package com.corradowaver.bot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropsManager {
  public static Properties getProps() {
    Properties props = new Properties();
    try (InputStream input = new FileInputStream(
        Objects.requireNonNull(
            PropsManager.class.getClassLoader().getResource("application.properties"))
            .getFile())) {
      props.load(input);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
    return props;
  }
}
