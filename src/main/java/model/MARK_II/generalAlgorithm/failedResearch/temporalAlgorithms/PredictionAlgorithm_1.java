package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import model.MARK_II.generalAlgorithm.AlgorithmStatistics;
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
 * @version 9/7/2015
 */
public class PredictionAlgorithm_1 extends Pooler {
    private SpatialPooler spatialPooler;

    Set<Neuron> previouslyActiveNeurons;
    Set<Neuron> currentActiveNeurons;

    /**
     * TODO: size = ? Set a max & min with a new class? For now set same
     * as activeColumns.size()
     */
    Set<Neuron> isPredictingNeurons;

    public PredictionAlgorithm_1(SpatialPooler spatialPooler) {
        this.spatialPooler = spatialPooler;
        super.region = spatialPooler.getRegion();

        this.previouslyActiveNeurons = new HashSet<>();
        this.currentActiveNeurons = new HashSet<>();
        this.isPredictingNeurons = new HashSet<>();
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

                // Step 3) Decide which neurons are active for the current time
                //         step
                // Possible answer: the current list of learning neurons? This
                // is because they aren't connected to anything since they have
                // the least connected synapses
                // TODO: possible answer = only previously active neurons
                //       closeby to current learning neuron
                learningNeuron.setActiveState(true);
                this.currentActiveNeurons.add(learningNeuron);
            }
        }

        // Step 4) change current states for next time step
        for (Neuron neuron : this.currentActiveNeurons) {
            this.previouslyActiveNeurons.add(neuron);
            neuron.setActiveState(false);
        }

        // Step 5) what neurons can be used for prediction?
        // Possible answer: which neurons currently have the most # of active
        // synapses across all distal dendrites connected to the current set of
        // active neurons. This is where we reward all the competition between
        // all synapses to represent an connection to a past time step.
        int numberOfActiveSynapsesConnectedToCurrentActiveNeurons = 0;

        Column[][] columns = super.region.getColumns();
        for (int ri = 0; ri < columns.length; ri++) {
            for (int ci = 0; ci < columns[0].length; ci++) {
                for (Neuron maybePredictingNeuron : columns[ri][ci].getNeurons()) {
                    // TODO: we want to figure out which neurons(let's call them
                    // futureNeurons) in any previous time step created a
                    // synapse to attach to me(maybePredictingNeuron)! This
                    // means that in the past after "maybePredictingNeuron" was
                    // active, then in the next time step "futureNeurons" was
                    // active. Thus, if maybePredictingNeuron is currently
                    // active, then this is an INDICATOR that "futureNeurons"
                    // will be active in the next time step. Thus we will mark
                    // those neurons as "possiblyActiveInNextTimeStep" or
                    // "isPredicting".
                    for (DistalSegment distalSegment : maybePredictingNeuron.getDistalSegments()) {
                        for (Synapse synapse : distalSegment.getConnectedSynapses());
                        // TODO: fuck this is going to be a lot more work
                        // need to figure out efficient way to figure this out
                    }

                }
            }
        }

        // TODO: strengthen the connection between neuronAtTimeT and
        // neuronAtTimeT+1 where

        // prepare for next time step be clearing current info that is out of date
        this.currentActiveNeurons.clear();
        // TODO: iterate through all neurons and call nextTimeStep()
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
