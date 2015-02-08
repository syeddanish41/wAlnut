package model.util;

import java.math.BigInteger;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 1/9/2015
 */
public class SDRStatistics {
    // notes on below variables @ https://github.com/WalnutiQ/WalnutiQ/issues/152
    private int numberOfNeurons; // n
    private int numberOfActiveNeurons; // w
    private int minimumNumberOfOverlapNeuronsForMatch; // theta

    public SDRStatistics(int numberOfNeurons, int numberOfActiveNeurons,
                         int minimumNumberOfOverlapNeuronsForMatch) {
        this.numberOfNeurons = numberOfNeurons;
        this.numberOfActiveNeurons = numberOfActiveNeurons;
        this.minimumNumberOfOverlapNeuronsForMatch = minimumNumberOfOverlapNeuronsForMatch;
    }

//    public double percentageOfFalsePositiveMatch() {
//        double favorableOutcomes = 0;
//        int theta = minimumNumberOfOverlapNeuronsForMatch;
//        int w = numberOfActiveNeurons;
//        int n = numberOfNeurons;
//        for (int b = theta; b <= w; b++) {
//            favorableOutcomes += overlap(n, w, b);
//        }
//
//        double totalOutcomes = combination(n, w);
//        return favorableOutcomes / totalOutcomes;
//    }
//
    BigInteger overlap(int n, int w, int b) {
        if (b > w) {
            throw new IllegalArgumentException("b cannot be greater" +
                    "than w when computing overlap");
        }
        return combination(w, b).multiply(combination(n - w, w - b));
    }

    /**
     * n choose k = n!/(k!(n-k)!)
     *
     * @param n Total number of elements.
     * @param k Number of elements to combine at one time.
     * @return The number of times k elements can be arranged out of n elements
     *         where order of arranging things do NOT make arrangement unique.
     */
    BigInteger combination(int n, int k) {
        return factorial(n).divide(factorial(k).multiply(factorial(n - k)));
    }

    BigInteger factorial(long n) {
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
