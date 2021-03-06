package de.reebow.loga;

final class ConfigParserFactory {

  ConfigParser configParser(String fileName) {
    if ("loga.properties".equals(fileName)) {
      return new PropertiesConfigParser(fileName);
    }
    throw new UnsupportedOperationException(String.format("Unsupported config parser: \"%s\"", fileName));
  }

}
