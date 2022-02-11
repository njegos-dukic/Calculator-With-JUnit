package impl.calculators;

import impl.exceptions.NotSupportedOperationException;
import impl.exceptions.NumberNotInAreaException;
import impl.utils.MathUtil;

/**
 * @author njego
 * @version 1.0
 * 
 * Class that extends Calculator and provides additional operations that include: power, factorial, checking for Armstrong and perfect number.
 *
 */
public class CalculatorAdvanced extends Calculator {

	/**
	 * @param action Action that will be performed (! - Factorial or values 1 - 9 for power of current calculator value.
	 * @throws NumberNotInAreaException Will be thrown if current value is less than 0 or greater than 10 in case of calculating factorial.
	 * @throws NotSupportedOperationException Will be thrown if provided action is not '!' or single digit.
	 * 
	 * Stores result in current value.
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {
        if (!Character.isDigit(action) && action != '!')
            throw new NotSupportedOperationException();

        if (this.getCurrentValue() == null)
            return;

        switch (action) {
            case '!':
                if (this.getCurrentValue() < 0 || this.getCurrentValue() > 10) {
                    throw new NumberNotInAreaException();
                }
                this.setCurrentValue((double) MathUtil.factorial(this.getCurrentValue().intValue()));
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                this.setCurrentValue((double) MathUtil.power(this.getCurrentValue().intValue(), Character.getNumericValue(action)));
                break;
        }
    }

    /**
     * @param value Character that specifies characterisitc to be checked ('A' for Armstrong, 'P' for perfect number)
     * @return True if current value is of the provided type, else false.
     * @throws NumberNotInAreaException Will be thrown if current value is less than 1.
     * @throws NotSupportedOperationException Will be thrown if specified char is not 'A' or 'P'.
     */
    public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException {
        if (value != 'A' && value != 'P')
            throw new NotSupportedOperationException();

        if (this.getCurrentValue().intValue() < 1) {
            throw new NumberNotInAreaException();
        }

        if (value == 'A') {
            return MathUtil.isArmstrong(this.getCurrentValue().intValue());
        }
        else {
            return MathUtil.isPerfect(this.getCurrentValue().intValue());
        }
    }
}
