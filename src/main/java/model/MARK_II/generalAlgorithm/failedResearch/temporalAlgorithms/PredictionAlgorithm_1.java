package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import model.MARK_II.generalAlgorithm.Pooler;
import model.MARK_II.generalAlgorithm.SpatialPooler;
import model.MARK_II.region.Column;
import model.MARK_II.region.Neuron;

import java.util.HashSet;
import java.util.Set;

/**
 * An experimental prediction algorithm. Initial ideas behind how this algorithm
 * was designed are here: https://github.com/WalnutiQ/walnut/issues/199
 *
 * This class solidifies those initial ideas into a deterministic prediction
 * algorithm that probably doesn't work well. Fully understanding why this
 * algorithm doesn't predict well will be important to generating new ideas on
 * how to improve it.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @version 9/3/2015
 */
public class PredictionAlgorithm_1 extends Pooler {
    private SpatialPooler spatialPooler;

    Set<Neuron> previouslyActiveNeurons;

    public PredictionAlgorithm_1(SpatialPooler spatialPooler) {
        this.spatialPooler = spatialPooler;
        super.region = spatialPooler.getRegion();

        this.previouslyActiveNeurons = new HashSet<Neuron>();
    }

    /**
     * Call this method to run PredictionAlgorithm_1 once on Region.
     */
    public void run() {
        Set<Column> activeColumns = this.spatialPooler.getActiveColumns();
        // Step 1) iterate through all active neurons in region
        for (Column activeColumn : activeColumns) {
            Neuron learningNeuron = this.getNeuronWithLeastNumberOfConnectedSynapses(activeColumn);
            // TODO: how many of the previouslyActiveNeurons should we connect to?
        }
    }

    /**
     * PROS:
     * 1) Prevents same neuron in column to repeatedly be learning neuron.
     * 2) Events out connections within Region like real neocortex.
     *
     * CONS:
     * 1) Is this enough of an indicator to say next time neuron2 becomes
     *    active -> neuron3 becomes predicted?
     */
    Neuron getNeuronWithLeastNumberOfConnectedSynapses(Column activeColumn) {
        return null;
    }
}
