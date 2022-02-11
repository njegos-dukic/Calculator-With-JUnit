package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import impl.calculators.CalculatorAdvanced;
import impl.exceptions.NotSupportedOperationException;
import impl.exceptions.NumberNotInAreaException;

public class CalculatorAdvancedTest {

	private final CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

    @BeforeEach
    void setUpCalculator() {
        calculatorAdvanced.setCurrentValue(0.0);
    }

    @Test
    void testCalculatorAdvanced() {
        assertThat(calculatorAdvanced, is(notNullValue()));
    }

    static Stream<Arguments> testCalculateAdvanced() {
        return Stream.of(
                Arguments.of(-0.01, '!', null, NumberNotInAreaException.class),
                Arguments.of(-0.0, '!', 1.0, null),
                Arguments.of(0.0, '!', 1.0, null),
                Arguments.of(0.01, '!', 1.0, null),
                Arguments.of(3.33, '!', 6.0, null),
                Arguments.of(4.13, '!', 24.0, null),
                Arguments.of(4.99, '!', 24.0, null),
                Arguments.of(5.00, '!', 120.0, null),
                Arguments.of(5.01, '!', 120.0, null),
                Arguments.of(9.99, '!', 362880.00, null),
                Arguments.of(10.00, '!', 3628800.00, null),
                Arguments.of(10.01, '!', null, NumberNotInAreaException.class),
                Arguments.of(-3.33, '1', -3.0, null),
                Arguments.of(-3.0, '2', 9.0, null),
                Arguments.of(-0.0, '0', 1.0, null),
                Arguments.of(0.0, '0', 1.0, null),
                Arguments.of(0.01, '0', 1.0, null),
                Arguments.of(-0.01, '3', 0.0, null),
                Arguments.of(-0.0, '3', 0.0, null),
                Arguments.of(0.0, '3', 0.0, null),
                Arguments.of(0.5, '3', 0.0, null),
                Arguments.of(3.33, '4', 81.0, null),
                Arguments.of(-1.11, '9', -1.0, null),
                Arguments.of(-1.11, '^', null, NotSupportedOperationException.class),
                Arguments.of(null, '!', null, null),
                Arguments.of(null, '^', null, NotSupportedOperationException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testCalculateAdvanced(Double initialValue, char operation, Double expectedValue, Class<? extends Exception> expectedException) throws NotSupportedOperationException, NumberNotInAreaException {
        calculatorAdvanced.setCurrentValue(initialValue);

        if (expectedException != null) {
            Exception e = assertThrows(expectedException, () -> calculatorAdvanced.calculateAdvanced(operation));
            assertThat(e, is(instanceOf(expectedException)));
        }

        else {
            calculatorAdvanced.calculateAdvanced(operation);
            assertThat(calculatorAdvanced.getCurrentValue(), is(equalTo(expectedValue)));
        }
    }

    static Stream<Arguments> testHasCharacteristic() {
        return Stream.of(
                Arguments.of(-3.0, 'A', null, NumberNotInAreaException.class),
                Arguments.of(-0.0, 'A', null, NumberNotInAreaException.class),
                Arguments.of(0.0, 'P', null, NumberNotInAreaException.class),
                Arguments.of(0.5, 'P', null, NumberNotInAreaException.class),

                Arguments.of(0.10, 'P', null, NumberNotInAreaException.class),
                Arguments.of(1.10, 'P', false, null),
                Arguments.of(3.33, 'P', false, null),
                Arguments.of(6.28, 'P', true, null),
                Arguments.of(27.99, 'P', false, null),
                Arguments.of(28.99, 'P', true, null),
                Arguments.of(500.0, 'P', false, null),
                Arguments.of(496.0, 'P', true, null),

                Arguments.of(0.10, 'A', null, NumberNotInAreaException.class),
                Arguments.of(1.90, 'A', true, null),
                Arguments.of(11.90, 'A', false, null),
                Arguments.of(153.12, 'A', true, null),
                Arguments.of(123.53, 'A', false, null),
                Arguments.of(370.01, 'A', true, null),
                Arguments.of(12.512, 'A', false, null),
                Arguments.of(371.99, 'A', true, null),

                Arguments.of(-3.0, '1', null, NotSupportedOperationException.class),
                Arguments.of(-0.0, '.', null, NotSupportedOperationException.class),
                Arguments.of(0.0, '+', null, NotSupportedOperationException.class),
                Arguments.of(0.5, '*', null, NotSupportedOperationException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testHasCharacteristic(Double initialValue, char characteristic, Boolean expectedValue, Class<? extends Exception> expectedException) throws NotSupportedOperationException, NumberNotInAreaException {
        calculatorAdvanced.setCurrentValue(initialValue);

        if (expectedException != null) {
            Exception e = assertThrows(expectedException, () -> calculatorAdvanced.hasCharacteristic(characteristic));
            assertThat(e, is(instanceOf(expectedException)));
        }

        else {
            assertThat(calculatorAdvanced.hasCharacteristic(characteristic), is(equalTo(expectedValue)));
        }
    }
}
