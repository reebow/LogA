/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.reebow.loga;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import de.reebow.loga.annotations.LogInput;
import de.reebow.loga.appenders.TestAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogInputAspectIntegrationTest {

  public static final String EXPECTED_LOG_MESSAGE = "Input arguments for method \"methodUnderTest\": Parameter type: "
    + "class java.lang.StringParameter name: name value: Daria. Parameter type: " + "intParameter name: age value: 1.";

  private TestAppender appender;

  @LogInput
  @SuppressWarnings("unused")
  public void methodUnderTest(String name, int age) {

  }

  @LogInput
  @SuppressWarnings("unused")
  public void methodNoParams() {

  }

  @BeforeEach
  void setUp() {
    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    Configuration config = ctx.getConfiguration();
    appender = (TestAppender) config.getAppenders().get("TestAppender");
  }

  @Test
  @DisplayName("Method with Annotation @LogInput - invoke method - log method name, parameter types, name and value")
  void logInput() {
    methodUnderTest("Daria", 1);

    List<LogEvent> logEvents = appender.getLogEvents();

    assertThat(logEvents).extracting(logEvent -> ((MutableLogEvent) logEvent).getFormattedMessage())
      .contains(EXPECTED_LOG_MESSAGE);
  }

  @Test
  @DisplayName("Method with Annotation @LogInput and no parameter - invoke method - log method name")
  void logInputWithNoParameter() {
    methodNoParams();

    List<LogEvent> logEvents = appender.getLogEvents();

    assertThat(logEvents).extracting(logEvent -> ((MutableLogEvent) logEvent).getFormattedMessage())
      .contains("Invoke method: \"methodNoParams\"");
  }

}
