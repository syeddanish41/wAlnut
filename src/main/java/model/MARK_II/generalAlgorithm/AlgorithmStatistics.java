package model.MARK_II.generalAlgorithm;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 7/9/2015
 */
public class AlgorithmStatistics {
    // NOTE: each element in each of the following lists represent TOTALS
    //       for 1 time step of running the general learning algorithm.

    // NOTE: Most important statistics at top
    // spatial pooler (SP) statistics:
    private int[] SP_activeSynapsesHistory,
            SP_activeColumnsHistory;
    private double[]  SP_inhibitionRadiusHistory;
    // Consider adding SDRStatistic probabilityOfFalsePositive later since
    // y-axis would be a different scale

    // temporal pooler (TP) statistics:
    private int[] TP_synapsesHistory,
            TP_distalSegmentsHistory,
            TP_activeDistalSegmentsHistory, // TODO: check for this at the end of TP
    // since it is dependent on all Synpase permanence values
    TP_sequenceSegmentsHistory,
            TP_learningNeuronsHistory;
    // distribution of new synapses somehow?
    private double[] TP_rawAnomalyScoreHistory; // TODO: automatically calculate somehow?

    private int arraySize;

    public AlgorithmStatistics(int arraySize) {
        this.arraySize = arraySize;
        this.clearAllData();
    }

    public AlgorithmStatistics() {
        this(10);
        this.arraySize = 10;
        this.clearAllData();
    }

    public void clearAllData() {
        this.SP_activeSynapsesHistory = new int[arraySize];
        this.SP_activeColumnsHistory = new int[arraySize];
        this.SP_inhibitionRadiusHistory = new double[arraySize];

        this.TP_synapsesHistory = new int[arraySize];
        this.TP_distalSegmentsHistory = new int[arraySize];
        this.TP_activeDistalSegmentsHistory = new int[arraySize];
        this.TP_sequenceSegmentsHistory = new int[arraySize];
        this.TP_learningNeuronsHistory = new int[arraySize];
        this.TP_rawAnomalyScoreHistory = new double[arraySize];
    }

    public int[] getSP_activeSynapsesHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.SP_activeSynapsesHistory[index] +
                        valueToAdd;
        this.SP_activeSynapsesHistory[index] = newValue;
        return this.SP_activeSynapsesHistory;
    }

    public int[] getSP_activeColumnsHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.SP_activeColumnsHistory[index] + valueToAdd;
        this.SP_activeColumnsHistory[index] = newValue;
        return this.SP_activeColumnsHistory;
    }

    public double[] getSP_inhibitionRadiusHistoryAndAdd(int index, double valueToAdd) {
        double newValue = this.SP_inhibitionRadiusHistory[index] + valueToAdd;
        this.SP_inhibitionRadiusHistory[index] = newValue;
        return this.SP_inhibitionRadiusHistory;
    }

    public int[] getTP_synapsesHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.TP_synapsesHistory[index] + valueToAdd;
        this.TP_synapsesHistory[index] = newValue;
        return this.TP_synapsesHistory;
    }

    public int[] getTP_distalSegmentsHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.TP_distalSegmentsHistory[index] + valueToAdd;
        this.TP_distalSegmentsHistory[index] = newValue;
        return this.TP_distalSegmentsHistory;
    }

    public int[] getTP_activeDistalSegmentsHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.TP_activeDistalSegmentsHistory[index] + valueToAdd;
        this.TP_activeDistalSegmentsHistory[index] = newValue;
        return this.TP_activeDistalSegmentsHistory;
    }

    public int[] getTP_sequenceSegmentsHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.TP_sequenceSegmentsHistory[index] + valueToAdd;
        this.TP_sequenceSegmentsHistory[index] = newValue;
        return this.TP_sequenceSegmentsHistory;
    }

    public int[] getTP_learningNeuronsHistoryAndAdd(int index, int valueToAdd) {
        int newValue = this.TP_learningNeuronsHistory[index] + valueToAdd;
        this.TP_learningNeuronsHistory[index] = newValue;
        return this.TP_learningNeuronsHistory;
    }

    public double[] getTP_rawAnomalyScoreHistoryAndAdd(int index, double valueToAdd) {
        double newValue = this.TP_rawAnomalyScoreHistory[index] + valueToAdd;
        this.TP_rawAnomalyScoreHistory[index] = newValue;
        return this.TP_rawAnomalyScoreHistory;
    }
}