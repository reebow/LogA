package de.reebow.loga;

class LogStatementGeneratorDefaultImpl implements LogStatementGenerator {

  @Override
  public String generateLogStatement(String methodName, Class[] parameterTypes, String[] parameterNames,
    Object[] parameterValues) {
    if (noParameters(parameterTypes)) {
      return String.format("Invoke method: \"%s\"", methodName);
    } else {
      return generateLogStatementFromParameters(methodName, parameterTypes, parameterNames, parameterValues);
    }
  }

  private String generateLogStatementFromParameters(String methodName, Class[] parameterTypes, String[] parameterNames,
    Object[] parameterValues) {
    StringBuilder parameterTypesAndValuesStrings = new StringBuilder();

    for (int index = 0; index < parameterTypes.length; index++) {
      Object value = parameterValues[index];
      String stringValue = null;
      if (value != null) {
        stringValue = value.toString();
      }
      parameterTypesAndValuesStrings.append("Parameter type: ").append(parameterTypes[index]).append("Parameter name: ")
        .append(parameterNames[index]).append(" value: ").append(stringValue).append(". ");
    }
    removeLastChar(parameterTypesAndValuesStrings);

    return String.format("Input arguments for method \"%s\": %s", methodName, parameterTypesAndValuesStrings);
  }

  private boolean noParameters(Class[] parameterTypes) {
    return parameterTypes == null || parameterTypes.length < 1;
  }

  private void removeLastChar(StringBuilder parameterTypesAndValuesStrings) {
    if (parameterTypesAndValuesStrings.length() > 0) {
      parameterTypesAndValuesStrings.setLength(parameterTypesAndValuesStrings.length() - 1);
    }
  }
}
