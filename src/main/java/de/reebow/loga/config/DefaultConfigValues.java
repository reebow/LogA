package de.reebow.loga.config;

import de.reebow.loga.LogLevel;

public final class DefaultConfigValues {

  private DefaultConfigValues() {
    // no-op
  }

  public static LogLevel defaultLogLevel() {
    return LogLevel.DEBUG;
  }

}
