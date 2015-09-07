package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.region.Neuron;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 9/7/2015
 */
public class PredictionAlgorithm_1Test extends TestCase {

    public void setUp() {

    }

    public void test_changingReference() {
        Set<Neuron> previouslyActiveNeurons = new HashSet<>();
        Set<Neuron> currentActiveNeurons = new HashSet<>();
        currentActiveNeurons.add(new Neuron());

        //previouslyActiveNeurons = currentActiveNeurons; // Didn't work
        for (Neuron neuron : currentActiveNeurons) {
            previouslyActiveNeurons.add(neuron);
        }
        assertEquals(1, previouslyActiveNeurons.size());
        assertEquals(1, currentActiveNeurons.size());

        currentActiveNeurons.clear();

        assertEquals(1, previouslyActiveNeurons.size());
        assertEquals(0, currentActiveNeurons.size());
    }
}
