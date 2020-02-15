package de.reebow.loga;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class PropertiesConfigParser implements ConfigParser {

  private static final Logger log = LogManager.getLogger();

  private final String fileName;

  public PropertiesConfigParser(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public Config parseConfig() {
    ClassLoader classLoader = PropertiesConfigParser.class.getClassLoader();

    URL url = classLoader.getResource(fileName);
    if (url == null) {
      return defaultConfig();
    }
    try (InputStream inputStream = new FileInputStream(url.getFile())) {
      Properties properties = new Properties();

      properties.load(inputStream);

      LogLevel logLevel = getLogLevel(properties);
      return new Config(logLevel);
    } catch (IOException e) {
      log.error("Could not read properties file, returning default config.", e);
      return defaultConfig();
    }
  }

  private Config defaultConfig() {
    return new Config(DefaultConfigValues.defaultLogLevel());
  }

  private LogLevel getLogLevel(Properties properties) {
    String defaultLogLevelString = properties.getProperty("defaultLogLevel");
    if (defaultLogLevelString == null) {
      return DefaultConfigValues.defaultLogLevel();
    }
    return LogLevel.valueOf(defaultLogLevelString);
  }
}
