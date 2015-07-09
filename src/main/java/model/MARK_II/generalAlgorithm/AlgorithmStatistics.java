package model.MARK_II.generalAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 7/7/2015
 */
public class AlgorithmStatistics {
    // NOTE: each element in each of the following lists represent TOTALS
    //       for 1 time step of running the general learning algorithm.

    // NOTE: Most important statistics at top
    // spatial pooler (SP) statistics:
    private List<Integer> SP_activeSynapsesHistory,
            SP_activeColumnsHistory;
    private List<Double>  SP_inhibitionRadiusHistory;
    // Consider adding SDRStatistic probabilityOfFalsePositive later since
    // y-axis would be a different scale

    // temporal pooler (TP) statistics:
    private List<Integer> TP_synapsesHistory,
            TP_distalSegmentsHistory,
            TP_activeDistalSegmentsHistory, // TODO: check for this at the end of TP
    // since it is dependent on all Synpase permanence values
    TP_sequenceSegmentsHistory,
            TP_learningNeuronsHistory;
    // distribution of new synapses somehow?
    private List<Double> TP_rawAnomalyScoreHistory; // TODO: automatically calculate somehow?

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
        this.SP_activeSynapsesHistory = new ArrayList<Integer>(arraySize);
        this.SP_activeColumnsHistory = new ArrayList<Integer>(arraySize);
        this.SP_inhibitionRadiusHistory = new ArrayList<Double>(arraySize);

        this.TP_synapsesHistory = new ArrayList<Integer>(arraySize);
        this.TP_distalSegmentsHistory = new ArrayList<Integer>(arraySize);
        this.TP_activeDistalSegmentsHistory = new ArrayList<Integer>(arraySize);
        this.TP_sequenceSegmentsHistory = new ArrayList<Integer>(arraySize);
        this.TP_learningNeuronsHistory = new ArrayList<Integer>(arraySize);
        this.TP_rawAnomalyScoreHistory = new ArrayList<Double>(arraySize);
    }

    public List<Integer> getSP_activeSynapsesHistory() {
        return SP_activeSynapsesHistory;
    }

    public List<Integer> getSP_activeColumnsHistory() {
        return SP_activeColumnsHistory;
    }

    public List<Double> getSP_inhibitionRadiusHistory() {
        return SP_inhibitionRadiusHistory;
    }

    public List<Integer> getTP_synapsesHistory() {
        return TP_synapsesHistory;
    }

    public List<Integer> getTP_distalSegmentsHistory() {
        return TP_distalSegmentsHistory;
    }

    public List<Integer> getTP_activeDistalSegmentsHistory() {
        return TP_activeDistalSegmentsHistory;
    }

    public List<Integer> getTP_sequenceSegmentsHistory() {
        return TP_sequenceSegmentsHistory;
    }

    public List<Integer> getTP_learningNeuronsHistory() {
        return TP_learningNeuronsHistory;
    }

    public List<Double> getTP_rawAnomalyScoreHistory() {
        return TP_rawAnomalyScoreHistory;
    }
}