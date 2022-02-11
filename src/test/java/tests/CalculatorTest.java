package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import impl.calculators.Calculator;
import impl.exceptions.DivisionByZeroException;
import impl.exceptions.NotSupportedOperationException;

public class CalculatorTest {

	private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUpCalculator() {
        calculator.setCurrentValue(0.0);
    }

    @Test
    void testCalculator() {
        assertThat(calculator, is(notNullValue()));
    }

    static Stream<Arguments> testGetAndSetCurrentValue() {
        return Stream.of(
                Arguments.of(0.0),
                Arguments.of(-0.0),
                Arguments.of(0.01),
                Arguments.of(-0.01),
                Arguments.of(1.25),
                Arguments.of(-1.25),
                Arguments.of(0.0000001),
                Arguments.of(-0.0000001),
                Arguments.of(Double.MAX_VALUE),
                Arguments.of(Double.MAX_VALUE - 0.00000001),
                Arguments.of(Double.MAX_VALUE + 0.00000001),
                Arguments.of(Double.MAX_VALUE - 1),
                Arguments.of(Double.MAX_VALUE + 1),
                Arguments.of(Double.MIN_VALUE),
                Arguments.of(Double.MIN_VALUE - 0.00000001),
                Arguments.of(Double.MIN_VALUE + 0.00000001),
                Arguments.of(Double.MIN_VALUE - 1),
                Arguments.of(Double.MIN_VALUE + 1),
                Arguments.of((Object) null),
                Arguments.of((double) 1),
                Arguments.of((double) -24)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetAndSetCurrentValue(Double value) {
        calculator.setCurrentValue(value);

        if (value != null && value == -0.0) {
            value = 0.0;
        }

        assertThat(value, is(equalTo(calculator.getCurrentValue())));
    }

    static Stream<Arguments> testCalculateSingleStep() {
        return Stream.of(
                Arguments.of(0.0, '+', 0.0, 0.0, null),
                Arguments.of(0.0, '+', -0.0, 0.0, null),
                Arguments.of(-0.0, '+', 0.0, 0.0, null),
                Arguments.of(-0.0, '+', -0.0, 0.0, null),
                Arguments.of(0.0, '+', 0.5, 0.5, null),
                Arguments.of(0.0, '+', -1.25, -1.25, null),
                Arguments.of(1.45, '+', 0.0, 1.45, null),
                Arguments.of(1.45, '+', 2.50, 3.95, null),
                Arguments.of(1.45, '+', -3.45, -2.0, null),
                Arguments.of(-2.5, '+', 0.0, -2.5, null),
                Arguments.of(-3.5, '+', 6.75, 3.25, null),
                Arguments.of(-2.5, '+', -3.5, -6.0, null),

                Arguments.of(0.0, '-', 0.0, 0.0, null),
                Arguments.of(0.0, '-', -0.0, 0.0, null),
                Arguments.of(-0.0, '-', 0.0, 0.0, null),
                Arguments.of(-0.0, '-', -0.0, 0.0, null),
                Arguments.of(0.0, '-', 0.5, -0.5, null),
                Arguments.of(0.0, '-', -1.25, 1.25, null),
                Arguments.of(1.45, '-', 0.0, 1.45, null),
                Arguments.of(1.45, '-', 2.50, -1.05, null),
                Arguments.of(1.45, '-', -3.45, 4.9, null),
                Arguments.of(-2.5, '-', 0.0, -2.5, null),
                Arguments.of(-2.5, '-', 3.5, -6.0, null),
                Arguments.of(-2.5, '-', -3.5, 1.0, null),

                Arguments.of(0.0, '*', 0.0, 0.0, null),
                Arguments.of(0.0, '*', -0.0, 0.0, null),
                Arguments.of(-0.0, '*', 0.0, 0.0, null),
                Arguments.of(-0.0, '*', -0.0, 0.0, null),
                Arguments.of(0.0, '*', 0.5, 0.0, null),
                Arguments.of(0.0, '*', -1.25, 0.0, null),
                Arguments.of(1.45, '*', 0.0, 0.0, null),
                Arguments.of(1.45, '*', 2.50, 3.625, null),
                Arguments.of(1.45, '*', -3.45, -5.0025, null),
                Arguments.of(-2.5, '*', 0.0, 0.0, null),
                Arguments.of(-2.5, '*', 3.5, -8.75, null),
                Arguments.of(-2.5, '*', -3.5, 8.75, null),

                Arguments.of(0.0, '/', 0.0, null, DivisionByZeroException.class),
                Arguments.of(0.0, '/', -0.0, null, DivisionByZeroException.class),
                Arguments.of(-0.0, '/', 0.0, null, DivisionByZeroException.class),
                Arguments.of(-0.0, '/', -0.0, null, DivisionByZeroException.class),
                Arguments.of(0.0, '/', 0.5, 0.0, null),
                Arguments.of(0.0, '/', -1.25, 0.0, null),
                Arguments.of(1.45, '/', 0.0, null, DivisionByZeroException.class),
                Arguments.of(1.45, '/', 2.50, 0.58, null),
                Arguments.of(3.5, '/', -2.5, -1.4, null),
                Arguments.of(-2.5, '/', 0.0, null, DivisionByZeroException.class),
                Arguments.of(-3.5, '/', 2.5, -1.4, null),
                Arguments.of(-3.5, '/', -2.5, 1.4, null),
                Arguments.of(1.0, '/', -0.0, null, DivisionByZeroException.class),

                Arguments.of(-3.0, '+', null, -3.0, null),
                Arguments.of(0.0, '-', null, 0.0, null),
                Arguments.of(-0.0, '*', null, 0.0, null),
                Arguments.of(3.0, '/', null, 3.0, null),
                Arguments.of(null, '+', -1.11, null, null),
                Arguments.of(null, '-', 0.0, null, null),
                Arguments.of(null, '*', -0.0, null, null),
                Arguments.of(null, '/', 1.11, null, null),

                Arguments.of(1.99, '.', 1.11, null, NotSupportedOperationException.class),
                Arguments.of(-5.12, ':', 1.11, null, NotSupportedOperationException.class),
                Arguments.of(null, '~', null, null, NotSupportedOperationException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testCalculateSingleStep(Double initialValue, char operation, Double operand, Double expectedResult, Class<? extends Exception> expectedException) throws DivisionByZeroException, NotSupportedOperationException {
        calculator.setCurrentValue(initialValue);

        if (expectedException != null) {
        	Exception e = assertThrows(expectedException, () -> { calculator.calculate(operand, operation); });
        	assertThat(e, is(instanceOf(expectedException)));
        }

        else if (expectedResult != null) {
            calculator.calculate(operand, operation);
            assertThat(calculator.getCurrentValue(), is(equalTo(expectedResult)));
        }
    }

    static Stream<Arguments> testCalculateMultistep() {
        return Stream.of(
                Arguments.of(0.0, Arguments.of('+', 3.0, '+', 4.0, '-', 2.0), 5.0, null),
                Arguments.of(0.0, Arguments.of('+', 1.0, '-', 4.0, '*', -2.0, '/', 2.0), 3.0, null),
                Arguments.of(4.0, Arguments.of('/', 0.0, '-', 4.0, '*', -2.0, '/', 2.0), 3.0, DivisionByZeroException.class),
                Arguments.of(1.5, Arguments.of('/', 1.0, '-', 3.0, '-', -2.0, '*', 2.0, 'u', 1.5, '+', 4), 3.0, NotSupportedOperationException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testCalculateMultistep(Double initialValue, Arguments arguments, Double expectedResult, Class<? extends Exception> expectedException) throws DivisionByZeroException, NotSupportedOperationException {
        calculator.setCurrentValue(initialValue);

        if (expectedException == null) {
        	for (int i = 0; i < arguments.get().length; i += 2) {
                calculator.calculate((Double) arguments.get()[i + 1], (char) arguments.get()[i]);
            }
        	
            assertThat(calculator.getCurrentValue(), is(equalTo(expectedResult)));
        }
        
        else {
        	Exception e = assertThrows(expectedException, () -> {
                for (int i = 0; i < arguments.get().length; i += 2) {
                    calculator.calculate((Double) arguments.get()[i + 1], (char) arguments.get()[i]);
                }
        	});
        	
        	assertThat(e, is(instanceOf(expectedException)));
        }
    }
}
