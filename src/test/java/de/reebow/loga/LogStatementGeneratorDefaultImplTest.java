package de.reebow.loga;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LogStatementGeneratorDefaultImplTest {

  private LogStatementGeneratorDefaultImpl cut;

  @ParameterizedTest
  @MethodSource("parameterTestValues")
  void generateLog(String methodName, Class[] parameterTypes, String[] parameterNames, Object[] parameterValues,
    String expected) {
    String result = cut.generateLogStatement(methodName, parameterTypes, parameterNames, parameterValues);

    assertThat(result).isEqualTo(expected);
  }

  @BeforeEach
  void setUp() {
    cut = new LogStatementGeneratorDefaultImpl();
  }

  private static Stream<Arguments> parameterTestValues() {
    return Stream.of(methodWithOneParameter(), methodWithTwoParameters(), Arguments
        .of(null, new Class[] { String.class }, new String[] { "stringParam" }, new Object[] { "Hello World" },
          "Input arguments for method \"null\": Parameter type: class java.lang.StringParameter name: stringParam value: Hello World."),
      methodWithOneParameterAndValueIsNull(), Arguments
        .of("logThisMethod", new Class[] { String.class }, new String[] { "stringParam" }, new Object[] { "null" },
          "Input arguments for method \"logThisMethod\": Parameter type: class java.lang.StringParameter name: stringParam value: null."),
      methodWithNoParameters());
  }

  private static Arguments methodWithNoParameters() {
    return Arguments
      .of("logThisMethod", new Class[] {}, new String[] {}, new Object[] {}, "Invoke method: \"logThisMethod\"");
  }

  private static Arguments methodWithOneParameterAndValueIsNull() {
    return Arguments
      .of("logThisMethod", new Class[] { String.class }, new String[] { "stringParam" }, new Object[] { null },
        "Input arguments for method \"logThisMethod\": Parameter type: class java.lang.StringParameter name: stringParam value: null.");
  }

  private static Arguments methodWithTwoParameters() {
    return Arguments
      .of("logThisMethod", new Class[] { String.class, int.class }, new String[] { "stringParam", "intParam" },
        new Object[] { "Hello World", 42 },
        "Input arguments for method \"logThisMethod\": Parameter type: class java.lang.StringParameter name: stringParam value: Hello World. Parameter type: intParameter name: intParam value: 42.");
  }

  private static Arguments methodWithOneParameter() {
    return Arguments
      .of("logThisMethod", new Class[] { String.class }, new String[] { "stringParam" }, new Object[] { "Hello World" },
        "Input arguments for method \"logThisMethod\": Parameter type: class java.lang.StringParameter name: stringParam value: Hello World.");
  }

}