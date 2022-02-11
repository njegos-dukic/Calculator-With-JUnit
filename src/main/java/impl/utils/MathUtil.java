package impl.utils;

public class MathUtil {
	
	/**
	 * @param value Value to be calculated as factorial value * (value - 1) * (value - 2) * ... * 3 * 2 * 1
	 * @return Factorial of current value.
	 */
	public static int factorial(int value) {
        int factorial = 1;
        for (int i = value; i > 0; i--) {
            factorial *= i;
        }

        return factorial;
    }

    /**
     * @param value Base to be calculated on the power of second param.
     * @param power Exponent to be used.
     * @return Power of value to the power param.
     */
    public static int power(int value, int power) {
        if (power == 0) {
            return 1;
        }

        int tempValue = value;
        for (int i = 1; i < power; i++) {
            value *= tempValue;
        }

        return value;
    }

    /**
     * @param value Value to be checked.
     * @return True if provided value is Armstrong's number, else false.
     */
    public static Boolean isArmstrong(int value) {
        int numberOfDigits = String.valueOf(value).length();
        int sumOfNPow = 0;
        int valueTemp = value;

        while (valueTemp != 0) {
            int currentDigit = valueTemp % 10;
            sumOfNPow += Math.pow(currentDigit, numberOfDigits);
            valueTemp /= 10;
        }

        return value == sumOfNPow;
    }

    /**
     * @param value Value to be checked.
     * @return True if provided value is perfect number, else false.
     */
    public static Boolean isPerfect(int value) {
        int sumOfDivisors = 0;

        for (int i = 1; i <= value / 2; i++) {
            if (value % i == 0) {
                sumOfDivisors += i;
            }
        }

        return value == sumOfDivisors;
    }
}
