package de.reebow.loga;

enum ConfigHolder {

  INSTANCE(new ConfigParserFactory().configParser("loga.properties").parseConfig());

  private final Config config;

  ConfigHolder(Config config) {
    this.config = config;
  }

  public Config getConfig() {
    return config;
  }
}