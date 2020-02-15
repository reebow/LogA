package de.reebow.loga;

public enum LogLevel {

  /**
   * Uses configured log level.
   */
  CONFIG,

  /**
   * No events will be logged.
   */
  OFF,

  /**
   * A severe error that will prevent the application from continuing.
   */
  FATAL,

  /**
   * An error in the application, possibly recoverable.
   */
  ERROR,

  /**
   * An event that might possible lead to an error.
   */
  WARN,

  /**
   * An event for informational purposes.
   */
  INFO,

  /**
   * A general debugging event.
   */
  DEBUG,

  /**
   * A fine-grained debug message, typically capturing the flow through the application.
   */
  TRACE,

  /**
   * All events should be logged.
   */
  ALL
}
