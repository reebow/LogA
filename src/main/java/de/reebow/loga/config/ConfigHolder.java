package de.reebow.loga.config;

public enum ConfigHolder {

  INSTANCE(new PropertiesConfigParser().parseConfig(""));

  private final Config config;

  ConfigHolder(Config config) {
    this.config = config;
  }

  public Config getConfig() {
    return config;
  }
}
