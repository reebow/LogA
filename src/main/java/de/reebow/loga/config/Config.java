package de.reebow.loga.config;

import org.apache.logging.log4j.spi.StandardLevel;

public final class Config {

  private final StandardLevel defaultLogLevel;

  public Config(StandardLevel defaultLogLevel) {
    this.defaultLogLevel = defaultLogLevel;
  }

  public StandardLevel defaultLogLevel() {
    return defaultLogLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Config config = (Config) o;

    return defaultLogLevel == config.defaultLogLevel;
  }

  @Override
  public int hashCode() {
    return defaultLogLevel.hashCode();
  }
}

