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
    String parameterTypesAndValuesStrings = createParameterAndValueString(parameterTypes, parameterNames, args);

    String logString = String.format("Input arguments for method \"%s\": %s", signature.getName(), parameterTypesAndValuesStrings);

    logger.log(level, logString);
  }

  private String createParameterAndValueString(Class[] parameterTypes, String[] parameterNames, Object[] args) {
    StringBuilder parameterTypesAndValuesStrings = new StringBuilder();

    for (int i = 0; i < parameterTypes.length; i++) {
      Object arg = args[i];
      String argument;
      if (arg == null) {
        argument = "null";
      } else {
        argument = arg.toString();
      }
      parameterTypesAndValuesStrings.append("Parameter type: ").append(parameterTypes[i]).append("Parameter name: ").append(parameterNames[i]).append(" value: ").append(argument).append(". ");
    }
    return parameterTypesAndValuesStrings.toString();
  }
}
