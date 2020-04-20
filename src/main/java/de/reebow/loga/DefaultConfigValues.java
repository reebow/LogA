package de.reebow.loga;

final class DefaultConfigValues {

  private DefaultConfigValues() {
    // no-op
  }

  public static Config defaultConfig() {
    return new Config(defaultLogLevel());
  }

  public static LogLevel defaultLogLevel() {
    return LogLevel.DEBUG;
  }

}
