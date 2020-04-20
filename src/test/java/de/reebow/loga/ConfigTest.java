package de.reebow.loga;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ConfigTest {

  @Test
  void defaultLogLevel() {
    var config = new Config(LogLevel.DEBUG);

    assertThat(config.defaultLogLevel()).isEqualTo(LogLevel.DEBUG);
  }

  @Test
  void equalsValuesAreEquals() {
    var config = new Config(LogLevel.DEBUG);
    var equalsConfig = new Config(LogLevel.DEBUG);

    assertThat(config.equals(equalsConfig)).isTrue();
  }

  @Test
  void equalsObjectIsEquals() {
    var config = new Config(LogLevel.DEBUG);
    var equalsObject = config;

    assertThat(config.equals(equalsObject)).isTrue();
  }
}