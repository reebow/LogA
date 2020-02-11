package de.reebow.loga;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import de.reebow.loga.annotations.LogInput;
import de.reebow.loga.appenders.TestAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.spi.StandardLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogInputAspectTest {

  private TestAppender appender;

  private LogInputAspect cut;

  @Mock
  private JoinPoint joinPoint;

  @Mock
  private LogInput logInput;

  @Mock
  private MethodSignature signature;

  @Mock
  private Method method;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    Configuration config = ctx.getConfiguration();
    appender = (TestAppender) config.getAppenders().get("TestAppender");
    cut = new LogInputAspect();
  }

  @Test
  void levelNotEnabled_logInput_skipLogging() {
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getDeclaringType()).thenReturn(this.getClass());
    when(signature.getMethod()).thenReturn(method);
    when(method.getAnnotation(any())).thenReturn(logInput);
    when(logInput.logLevel()).thenReturn(StandardLevel.DEBUG);

    Configurator.setLevel(this.getClass().getCanonicalName(), Level.ERROR);

    cut.logInput(joinPoint);

    verify(signature, never()).getParameterTypes();
  }
}
