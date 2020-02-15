package de.reebow.loga.config;

import static org.assertj.core.api.Assertions.assertThat;

import de.reebow.loga.LogLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesConfigParserTest {

  private static final String FILE_NAME = "loga.properties";

  private static final String FILE_NAME_EMPTY = "loga_empty.properties";

  @Test
  @DisplayName("Property file does not exists - parseConfig - return default Config")
  void parsePropertiesFileNotExists() {
    var cut = new PropertiesConfigParser(FILE_NAME_EMPTY);
    var expected = new Config(DefaultConfigValues.defaultLogLevel());
    Config config = cut.parseConfig();

    assertThat(config).isEqualTo(expected);
  }

  @Test
  @DisplayName("Property defaultLogLevel is ERROR - parseConfig - return Config with defaultLogLevel ERROR")
  void parseDefaultLogLevel() {
    var cut = new PropertiesConfigParser(FILE_NAME);
    Config config = cut.parseConfig();

    assertThat(config.defaultLogLevel()).isEqualTo(LogLevel.ERROR);
  }

  @Test
  @DisplayName("Property defaultLogLevel is null - parseConfig - return Config with defaultLogLevel DEBUG")
  void parseDefaultLogLevelNotExisting() {
    var cut = new PropertiesConfigParser(FILE_NAME_EMPTY);
    Config config = cut.parseConfig();

    assertThat(config.defaultLogLevel()).isEqualTo(LogLevel.DEBUG);
  }
}