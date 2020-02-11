package de.reebow.loga.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.spi.StandardLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesConfigParserTest {

  private static final String FILE_NAME = "loga.properties";

  private static final String FILE_NAME_EMPTY = "loga_empty.properties";

  private ConfigParser cut;

  @BeforeEach
  void setUp() {
    cut = new PropertiesConfigParser();
  }

  @Test
  @DisplayName("Property file does not exists - parseConfig - return default Config")
  void parsePropertiesFileNotExists() {
    var expected = new Config(DefaultConfigValues.defaultLogLevel());
    Config config = cut.parseConfig("fileNotExists");

    assertThat(config).isEqualTo(expected);
  }

  @Test
  @DisplayName("Property defaultLogLevel is ERROR - parseConfig - return Config with defaultLogLevel ERROR")
  void parseDefaultLogLevel() {
    Config config = cut.parseConfig(FILE_NAME);

    assertThat(config.defaultLogLevel()).isEqualTo(StandardLevel.ERROR);
  }

  @Test
  @DisplayName("Property defaultLogLevel is null - parseConfig - return Config with defaultLogLevel DEBUG")
  void parseDefaultLogLevelNotExisting() {
    Config config = cut.parseConfig(FILE_NAME_EMPTY);

    assertThat(config.defaultLogLevel()).isEqualTo(StandardLevel.DEBUG);
  }
}