package de.reebow.loga;

final class Config {

  private final LogLevel defaultLogLevel;

  public Config(LogLevel defaultLogLevel) {
    this.defaultLogLevel = defaultLogLevel;
  }

  public LogLevel defaultLogLevel() {
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

