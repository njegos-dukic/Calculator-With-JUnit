package impl.calculators;

import impl.exceptions.DivisionByZeroException;
import impl.exceptions.NotSupportedOperationException;

/**
 * Class that holds single Double value and provides basic arithmetic operations that include: addition, subtraction, multiplication and division.
 * @author njego
 * @version 1.0
 * 
 **/
public class Calculator {

	private Double currentValue = 0.0;

	
    /**
     * @return Current value of calculator after performed operations.
     */
    public Double getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue Value to be stored and used as first operand.
     */
    public void setCurrentValue(Double currentValue) {
        currentValue = handleNegativeZero(currentValue);
        this.currentValue = currentValue;
    }

    /**
     * @param value Second operand.
     * @param operator Operation that will be executed, supports ('+', '-', '*', '/')
     * @throws DivisionByZeroException Exception will be thrown if provided operand is 0 and provided operation is division.
     * @throws NotSupportedOperationException Exception will be thrown if provided operator is not valid.
     */
    public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/')
            throw new NotSupportedOperationException();

        if (value == null)
            return;

        value = handleNegativeZero(value);
        switch (operator) {
            case '+':
                this.currentValue += value;
                break;
            case '-':
                this.currentValue -= value;
                break;
            case '*':
                this.currentValue *= value;
                break;
            case '/':
                if (value == 0.0) {
                    throw new DivisionByZeroException();
                }
                this.currentValue /= value;
                break;
        }
        this.currentValue = handleNegativeZero(this.currentValue);
    }

    /**
     * @param value Value that will be checked.
     * @return Provided value if it's different than -0.0, else 0.0 without sign.
     */
    protected Double handleNegativeZero(Double value) {
        if (value != null && value == -0.0) {
            return 0.0;
        }

        return value;
    }
}
