package de.reebow.loga;

import de.reebow.loga.annotations.LogInput;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@SuppressWarnings("rawtypes")
@Aspect
public final class LogInputAspect {

  @Before("@annotation(de.reebow.loga.annotations.LogInput) && execution(* *(..))")
  public void logInput(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Logger logger = LogManager.getLogger(signature.getDeclaringType());

    LogInput annotation = signature.getMethod().getAnnotation(LogInput.class);
    LogLevel logLevel = annotation.logLevel();
    if (LogLevel.CONFIG.equals(logLevel)) {
      logLevel = ConfigHolder.INSTANCE.getConfig().defaultLogLevel();
    }
    Level level = Level.valueOf(logLevel.name());

    if (!logger.isEnabled(level)) {
      return;
    }

    Class[] parameterTypes = signature.getParameterTypes();
    String[] parameterNames = signature.getParameterNames();
    Object[] args = joinPoint.getArgs();

    String logString = ConfigHolder.INSTANCE.getLogStatementGenerator()
      .generateLogStatement(signature.getName(), parameterTypes, parameterNames, args);

    logger.log(level, logString);
  }

}
