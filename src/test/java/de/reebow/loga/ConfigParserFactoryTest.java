package de.reebow.loga;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConfigParserFactoryTest {

  @Test
  @DisplayName("No ConfigParser found - configParser - throw NotYetImplementedException")
  void noConfigParserFound() {
    ConfigParserFactory factory = new ConfigParserFactory();

    assertThrows(UnsupportedOperationException.class, () -> factory.configParser("ConfigParserWhichDoesNotExists"),
      "Unsupported config parser: \"ConfigParserWhichDoesNotExists\"");
  }

  @Test
  @DisplayName("Filename loga.properties - configParser - return PropertiesConfigParser")
  void propertiesConfigParser() {
    ConfigParserFactory factory = new ConfigParserFactory();

    ConfigParser configParser = factory.configParser("loga.properties");
    assertThatObject(configParser).isInstanceOf(PropertiesConfigParser.class);
  }
}