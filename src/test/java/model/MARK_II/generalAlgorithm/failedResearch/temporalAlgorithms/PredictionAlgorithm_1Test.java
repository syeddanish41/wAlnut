package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.generalAlgorithm.SpatialPooler;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.generalAlgorithm.failureResearch.temporalAlgorithms.PredictionAlgorithm_1;
import model.MARK_II.region.Cell;
import model.MARK_II.region.Neuron;
import model.MARK_II.region.Region;
import model.MARK_II.region.Synapse;

import java.util.Set;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 1/16/2016
 */
public class PredictionAlgorithm_1Test extends TestCase {
    private SDRAlgorithm_1 SDRAlgorithm_1;
    private PredictionAlgorithm_1 predictionAlgorithm_1;
    private Region region;
    private Neuron A;
    private Neuron B;

    public void setUp() {
        this.region = new Region("root", 1, 2, 1, 20.0, 1);
        this.SDRAlgorithm_1 = new SDRAlgorithm_1(1, region, 0);
        this.predictionAlgorithm_1 = new PredictionAlgorithm_1(SDRAlgorithm_1);
    }

    // https://cloud.githubusercontent.com/assets/2585159/12080374/88609706-b226-11e5-832f-a33fac9d7447.png
    /*
     * Should use image instead of setting the individual neurons to active as the columns stay unaffected by the changes in the neuron
     */
    public void test_run() {
        //this.SDRAlgorithm_1.run();
        this.A = this.region.getColumn(0,0).getNeuron(0);
        this.B = this.region.getColumn(0,1).getNeuron(0);

        // @t = 0
        A.setActiveState(true);
        this.predictionAlgorithm_1.run();
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 1
        assertFalse(A.getActiveState());
        assertTrue(A.getPreviousActiveState());
        B.setActiveState(true);
        this.predictionAlgorithm_1.run();
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 2
        // Check for synapse between B and A
        Set<Synapse<Cell>> synapses = B.getDistalSegments().get(0).getSynapses();
        assertEquals(synapses.size(), 1);
        for(Synapse<Cell> synapse: synapses)
        {
            assertEquals(A, synapse.getCell());
        }
        assertFalse(B.getActiveState());
        assertFalse(A.getActiveState());
        this.predictionAlgorithm_1.run();
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 3
        A.setActiveState(true);
        this.predictionAlgorithm_1.run();
        assertTrue(B.getPredictingState());
        this.predictionAlgorithm_1.nextTimeStep();
    }

    // B becomes active from t=3 to t=4
    public void test_runAtTEquals4_Case1()
    {
        this.test_run();
        B.setActiveState(true);
        Synapse<Cell>[] synapses = (Synapse<Cell>[])(B.getDistalSegments().get(0).getSynapses()).toArray();
        double permanence = synapses[0].getPermanenceValue();
        this.predictionAlgorithm_1.run();
        double permanenceAfter = synapses[0].getPermanenceValue();
        boolean LTPHappened = permanenceAfter>permanence?true:false;
        assertTrue(LTPHappened);
        this.predictionAlgorithm_1.nextTimeStep();
    }

    // B does not become active from t=3 to t=4
    public void test_runAtTEquals4_Case2()
    {
        this.test_run();
        Synapse<Cell>[] synapses = (Synapse<Cell>[])(B.getDistalSegments().get(0).getSynapses()).toArray();
        double permanence = synapses[0].getPermanenceValue();
        this.predictionAlgorithm_1.run();
        double permanenceAfter = synapses[0].getPermanenceValue();
        boolean LTPHappened = permanenceAfter>permanence?true:false;
        assertFalse(LTPHappened);
        this.predictionAlgorithm_1.nextTimeStep();
    }
}
