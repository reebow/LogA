package de.reebow.loga;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DefaultConfigValuesTest {

  @Test
  void returnDefaultConfig() {
    assertThat(DefaultConfigValues.defaultConfig()).isEqualTo(new Config(LogLevel.DEBUG));
  }

  @Test
  void defaultLogLevelDebug() {
    assertThat(DefaultConfigValues.defaultLogLevel()).isEqualTo(LogLevel.DEBUG);
  }
}