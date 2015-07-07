package model.MARK_II.generalAlgorithm;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 7/7/2015
 */
public class algorithmStatistics {
    // NOTE: each element in each of the following lists represent TOTALS
    //       for 1 time step of running the general learning algorithm.

    // NOTE: Most important statistics at top
    // spatial pooler statistics:
    private int[] activeSynapsesHistory;
    private int[] activeColumnsHistory;
    private int[] inhibitionRadiusHistory;
    // Consider adding SDRStatistic probabilityOfFalsePositive later since
    // y-axis would be a different scale

    // temporal pooler statistics:
    private int[] synapsesHistory;
    private int[] distalSegmentsHistory;
    // distribution of new synapses somehow?
    private double[] rawAnomalyScore;
    private int[] activeDistalSegmentsHistory;
    private int[] sequenceSegmentsHistory;
    private int[] learningNeuronsHistory;

    public algorithmStatistics(int arraySize) {
        this.resetForNextTimeStep(arraySize);
    }

    public void resetForNextTimeStep(int arraySize) {
        this.activeSynapsesHistory = new int[arraySize];
        this.activeColumnsHistory = new int[arraySize];
        this.inhibitionRadiusHistory = new int[arraySize];

        this.synapsesHistory = new int[arraySize];
        this.distalSegmentsHistory = new int[arraySize];
        this.rawAnomalyScore = new double[arraySize];
        this.activeDistalSegmentsHistory = new int[arraySize];
        this.sequenceSegmentsHistory = new int[arraySize];
        this.learningNeuronsHistory = new int[arraySize];
    }
}
