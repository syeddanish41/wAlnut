package model.util;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 1/9/2015
 */
public class SDRStatistics {
    // notes on below variables @ https://github.com/WalnutiQ/WalnutiQ/issues/152
    private int numberOfNeurons; // n
    private int numberOfActiveNeurons; // w
    private int numberOfOverlapNeurons; // b
    private int minimumNumberOfOverlapNeuronsForMatch; // theta

    public SDRStatistics(int numberOfNeurons, int numberOfActiveNeurons,
                         int numberOfOverlapNeurons, int minimumNumberOfOverlapNeuronsForMatch) {
        this.numberOfNeurons = numberOfNeurons;
        this.numberOfActiveNeurons = numberOfActiveNeurons;
        this.numberOfOverlapNeurons = numberOfOverlapNeurons;
        this.minimumNumberOfOverlapNeuronsForMatch = minimumNumberOfOverlapNeuronsForMatch;
    }

    /**
     * n choose k = n!/(k!(n-k)!)
     *
     * @param n Total number of elements.
     * @param k Number of elements to combine at one time.
     * @return The number of times k elements can be arranged out of n elements
     *         where order of arranging things do NOT make arrangement unique.
     */
    private double combination(int n, int k) {

    }
}
