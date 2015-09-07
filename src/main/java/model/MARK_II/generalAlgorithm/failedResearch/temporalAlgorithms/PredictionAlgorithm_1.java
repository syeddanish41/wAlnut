package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import model.MARK_II.generalAlgorithm.ColumnPosition;
import model.MARK_II.generalAlgorithm.Pooler;
import model.MARK_II.generalAlgorithm.SpatialPooler;
import model.MARK_II.region.*;

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
    Set<Neuron> currentActiveNeurons;

    public PredictionAlgorithm_1(SpatialPooler spatialPooler) {
        this.spatialPooler = spatialPooler;
        super.region = spatialPooler.getRegion();

        this.previouslyActiveNeurons = new HashSet<>();
        this.currentActiveNeurons = new HashSet<>();
    }

    /**
     * Call this method to run PredictionAlgorithm_1 once on Region.
     *
     * MAIN LOGIC: For each learning neuron in an active column, connect to all
     * previously active neurons.
     */
    public void run() {
        Set<ColumnPosition> activeColumnPositions = this.spatialPooler.getActiveColumnPositions();
        // Step 1) iterate through all active neurons in region
        for (ColumnPosition ACP : activeColumnPositions) {
            Column activeColumn = super.getRegion().getColumn(ACP.getRow(), ACP.getRow());
            Neuron learningNeuron = this.getNeuronWithLeastNumberOfConnectedSynapses(activeColumn);

            // Step 2) for each learning neuron connect to all previously active
            //         neurons
            for (Neuron previouslyActiveNeuron : this.previouslyActiveNeurons) {
                DistalSegment distalSegment = new DistalSegment();
                distalSegment.addSynapse(new Synapse<>(previouslyActiveNeuron, Synapse.MINIMAL_CONNECTED_PERMANENCE, -1, -1));
                learningNeuron.addDistalSegment(distalSegment);

                // Step 3) Decide which neurons are active for the current time step
                // TODO: answer: the current list of learning neurons? This is because
                //       they aren't connected to anything since they have the least
                //       connected synapses
                learningNeuron.setActiveState(true);
                this.currentActiveNeurons.add(learningNeuron);
            }
        }

        // Step 4) change current states for next time step
        this.previouslyActiveNeurons = this.currentActiveNeurons;
        this.currentActiveNeurons.clear();
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
