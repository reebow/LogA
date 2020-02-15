package de.reebow.loga;

final class DefaultConfigValues {

  private DefaultConfigValues() {
    // no-op
  }

  public static LogLevel defaultLogLevel() {
    return LogLevel.DEBUG;
  }

}
