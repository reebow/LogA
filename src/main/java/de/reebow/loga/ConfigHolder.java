package de.reebow.loga;

enum ConfigHolder {

  INSTANCE(new ConfigParserFactory().configParser("loga.properties").parseConfig(),
    new LogStatementGeneratorDefaultImpl());

  private final Config config;

  private final LogStatementGenerator logStatementGenerator;

  ConfigHolder(Config config, LogStatementGenerator logStatementGenerator) {
    this.config = config;
    this.logStatementGenerator = logStatementGenerator;
  }

  public Config getConfig() {
    return config;
  }

  public LogStatementGenerator getLogStatementGenerator() {
    return logStatementGenerator;
  }
}
