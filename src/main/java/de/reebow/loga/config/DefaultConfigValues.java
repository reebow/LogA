package de.reebow.loga.config;

import org.apache.logging.log4j.spi.StandardLevel;

public final class DefaultConfigValues {

  private DefaultConfigValues() {
    // no-op
  }

  public static StandardLevel defaultLogLevel() {
    return StandardLevel.DEBUG;
  }

}
