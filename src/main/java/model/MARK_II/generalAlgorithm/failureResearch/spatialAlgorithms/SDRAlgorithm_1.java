package model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms;

import model.MARK_II.generalAlgorithm.ColumnPosition;
import model.MARK_II.generalAlgorithm.Pooler;
import model.MARK_II.region.Column;
import model.MARK_II.region.DistalSegment;
import model.MARK_II.region.Neuron;
import model.MARK_II.region.Region;

import java.util.Arrays;
import java.util.HashSet;

/**
 * This is an experimental SDR algorithm.
 *
 * The core idea of this algorithm is that it finds a FIXED percentage of the
 * most active columns and this is the Region's SDR.
 *
 * This class solidifies these ideas into a deterministic SDR generation
 * algorithm that works okay. Understanding how this algorithm compares
 * to SDRAlgorithm_2(with inhibition) will allow us to understand when it is
 * appropriate or not appropriate to use inhibition in SDRAlgorithms.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @author Aarathi Raghuraman (raarathi@vt.edu)
 * @version 11/01/2015
 */
public class SDRAlgorithm_1 extends Pooler {
    private final double ACTIVITY_PERCENTAGE;

    // activityPercentage: what percentage of columns are allowed to become active
    public SDRAlgorithm_1(int numberOfTimesToRunAlgorithm, Region region, double activityPercentage) {
        super(numberOfTimesToRunAlgorithm);
        super.region = region;
        this.ACTIVITY_PERCENTAGE = activityPercentage;
    }

    /**
     * Call this method to run SDRAlgorithm_1 once on a Region.
     */
    public HashSet<ColumnPosition> run() {

        // % to actual active columns
        Column[][] columns = this.region.getColumns();
        int numActiveColumns = (int) Math.round(((double)columns[0].length)*((double)columns.length)*ACTIVITY_PERCENTAGE/100);

        int[] overlapScores = new int[columns.length*columns[0].length];

        int counter = 0;
        int indexOfMinumOverlapScore = overlapScores.length - numActiveColumns;
        //TODO live QuickSort implemented here would remove sort algorithm below. 
        for (int row = 0; row < columns.length; row++) {
            for (int column = 0; column < columns[0].length; column++) {
                overlapScores[counter] = this.computeColumnOverlapScore(columns[row][column]);
                counter++;
            }
        }

        // sorting overlapScores using quicksort
        Arrays.sort(overlapScores);
 
        // setting columns that are above the minimumOverlapScore to active
        int minimumOverlapScore = overlapScores[indexOfMinumOverlapScore];
        //System.out.println("minimumOverlapScore = " + minimumOverlapScore);
        if (minimumOverlapScore == 0) {
            System.out.println("WARNING: Consider making your region " +
            this.region.getBiologicalName() + "'s connection to lower regions " +
                    "have more overlap.");
        }

//        String sortedScores = new String();
//        for (int i = 0; i < overlapScores.length; i++) {
//            sortedScores += overlapScores[i] + ", ";
//        }
//        System.out.println("sortedScores = " + sortedScores);

        HashSet<ColumnPosition> sparseRepresentation = new HashSet<ColumnPosition>();
        for (int row = 0; row < columns.length; row++) {
            for (int column = 0; column < columns[0].length; column++) {
                if(columns[row][column].getProximalSegment().getNumberOfActiveSynapses()
                        >= minimumOverlapScore)
                {
                    columns[row][column].setActiveState(true);
                    for (Neuron neuron : columns[row][column].getNeurons()) {
                        neuron.setActiveState(true);
                    }
                    super.activeColumns.add(columns[row][column]);
                    sparseRepresentation.add(new ColumnPosition(row, column));
                }
            }
        }

        super.activeColumnPositions = sparseRepresentation;
        return sparseRepresentation;
    }

    /**
     * The overlapScore for each Column is the number of Synapses connected to
     * Cells with active inputs.
     */
    int computeColumnOverlapScore(Column column) {
        if (column == null) {
            throw new IllegalArgumentException(
                    "the Column in SDAlgorithm_1 method computeColumnOverlapScore cannot be null");
        }

        /// overlap(c) = 0
        int newOverlapScore = column.getProximalSegment()
                /// for s in connectedSynapses(c)
                ///     overlap(c) = overlap(c) + input(t, s.sourceInput)
                .getNumberOfActiveSynapses();

        return newOverlapScore;
    }

    @Override
    public void nextTimeStep() {
        Column[][] columns = super.region.getColumns();
        for (int ri = 0; ri < columns.length; ri++) {
            for (int ci = 0; ci < columns[0].length; ci++) {
                for (Neuron neuron : columns[ri][ci].getNeurons()) {
                    neuron.nextTimeStep();
                }
            }
        }
    }
}