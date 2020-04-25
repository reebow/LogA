package de.reebow.loga;

public interface LogStatementGenerator {

  String generateLogStatement(String methodName, Class[] parameterTypes, String[] parameterNames, Object[] args);
}
