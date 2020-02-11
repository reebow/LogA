package de.reebow.loga.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;

public class PropertiesConfigParser implements ConfigParser {

  private static final Logger log = LogManager.getLogger();

  @Override
  public Config parseConfig(String fileName) {
    ClassLoader classLoader = PropertiesConfigParser.class.getClassLoader();

    URL url = classLoader.getResource(fileName);
    if (url == null) {
      return defaultConfig();
    }
    try (InputStream inputStream = new FileInputStream(url.getFile())) {
      Properties properties = new Properties();

      properties.load(inputStream);

      StandardLevel standardLevel = getStandardLevel(properties);
      return new Config(standardLevel);
    } catch (IOException e) {
      log.error("Could not read properties file, returning default config.", e);
      return defaultConfig();
    }
  }

  private Config defaultConfig() {
    return new Config(DefaultConfigValues.defaultLogLevel());
  }

  private StandardLevel getStandardLevel(Properties properties) {
    String defaultLogLevelString = properties.getProperty("defaultLogLevel");
    if (defaultLogLevelString == null) {
      return DefaultConfigValues.defaultLogLevel();
    }
    return StandardLevel.valueOf(defaultLogLevelString);
  }
}
