package model.util;

import java.math.BigInteger;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 2/19/2015
 */
public class SDRStatistics {
    // notes on below variables @ https://github.com/WalnutiQ/WalnutiQ/issues/152
    private int n; // number of neurons
    private int w; // number of activeNeurons and also upper bound of b
    private int theta; // theta = lower bound of b

    public SDRStatistics(int numberOfNeurons, int numberOfActiveNeurons,
                         int minimumNumberOfOverlapNeuronsForMatch) {
        this.n = numberOfNeurons;
        this.w = numberOfActiveNeurons;
        this.theta = minimumNumberOfOverlapNeuronsForMatch;
    }

    BigInteger probabilityOfFalsePositive() {
        if (this.n > this.w || this.n < this.theta) {
            throw new IllegalArgumentException("n must be <= than w and n must be >= theta");
        }

        BigInteger numerator = new BigInteger("0");
        for (int b = this.theta; b <= this.w; b++) {
            numerator = numerator.add(overlapSet(b));
        }

        BigInteger deminator = combination(this.n, this.w);

        // TODO: make sure return is not between 0 and 1. Read paper
        return numerator.divide(deminator);
    }

    BigInteger overlapSet(int b) {
        if (b > this.w) {
            throw new IllegalArgumentException("b must be <= than w");
        }
        return combination(this.w, b).multiply(combination(this.n - this.w, this.w - b));
    }

    /**
     * n choose k = n!/(k!(n-k)!)
     *
     * @param n Total number of elements.
     * @param k Number of elements to combine at one time.
     * @return The number of times k elements can be arranged out of n elements
     *         where order of arranging things do NOT make arrangement unique.
     */
    static BigInteger combination(int n, int k) {
        return factorial(n).divide(factorial(k).multiply(factorial(n - k)));
    }

    static BigInteger factorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("You cannot take the factorial" +
                    "of the negative integer " + n);
        }
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
