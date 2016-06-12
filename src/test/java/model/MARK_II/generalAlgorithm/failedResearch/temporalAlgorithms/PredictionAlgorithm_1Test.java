package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.generalAlgorithm.failureResearch.temporalAlgorithms.PredictionAlgorithm_1;
import model.MARK_II.region.*;
import model.MARK_II.sensory.Retina;

import java.io.IOException;
import java.util.Set;

/**
 * @author Aarathi Raghuraman (raarathi@vt.edu)
 * @author Q Liu (quinnliu@vt.edu)
 * @version 5/22/2016
 */
public class PredictionAlgorithm_1Test extends TestCase {
    private Retina retina;
    private Region region;
    private SDRAlgorithm_1 SDRAlgorithm_1;
    private PredictionAlgorithm_1 predictionAlgorithm_1;
    private Neuron A;
    private Neuron B;

    public void setUp() {
        // Needed to
        Synapse.INITIAL_PERMANENCE = 0.2;
        Synapse.MINIMAL_CONNECTED_PERMANENCE = 0.2;
        Synapse.PERMANENCE_INCREASE = 0.02;
        Synapse.PERMANENCE_DECREASE = 0.005;

        this.retina = new Retina(1, 2);
        this.region = new Region("root", 1, 2, 1, 20.0, 1);
        AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
        retinaToRegion.connect(this.retina.getVisionCells(), this.region.getColumns(), 0, 0);
        this.SDRAlgorithm_1 = new SDRAlgorithm_1(1, this.region, 50);
        this.predictionAlgorithm_1 = new PredictionAlgorithm_1(this.SDRAlgorithm_1);
    }

    public void test_run() throws IOException {
        // Please refer to this diagram while understanding the code below:
        // https://cloud.githubusercontent.com/assets/2585159/12080374/88609706-b226-11e5-832f-a33fac9d7447.png
        this.A = this.region.getColumn(0,0).getNeuron(0);
        this.B = this.region.getColumn(0,1).getNeuron(0);

        // @t = -0.5
        this.retina.seeBMPImage("visionCell00_active.bmp");
        this.SDRAlgorithm_1.run();
        this.predictionAlgorithm_1.run();

        // @t = 0
        assertTrue(A.getActiveState());
        assertFalse(B.getActiveState());
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 0.5
        this.retina.seeBMPImage("visionCell01_active.bmp");
        this.SDRAlgorithm_1.run();
        this.predictionAlgorithm_1.run();

        // @t = 1
        assertFalse(A.getActiveState());
        assertTrue(A.getPreviousActiveState());
        assertTrue(B.getActiveState());
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 1.5
        // nothing happens...

        // @t = 2
        assertFalse(A.getActiveState());
        assertFalse(B.getActiveState());
        assertTrue(B.getPreviousActiveState());

        // Check for synapse between B and A
        assertEquals(1, B.getDistalSegments().size());
        Set<Synapse<Cell>> B_synapses = B.getDistalSegments().get(0).getSynapses();
        assertEquals(1, B_synapses.size());
        for(Synapse<Cell> B_synapse: B_synapses)
        {
            assertEquals(A, B_synapse.getCell());
        }
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 2.5
        this.retina.seeBMPImage("visionCell00_active.bmp");
        this.SDRAlgorithm_1.run();
        this.predictionAlgorithm_1.run();

        // @t = 3
        assertTrue(A.getActiveState());
        assertTrue(B.getPredictingState());
        this.predictionAlgorithm_1.nextTimeStep();
    }

    public void test_runAtTEquals4_LTP() throws IOException
    {
        // @t = 3.5
        this.test_run();
        this.retina.seeBMPImage("visionCell01_active.bmp");
        this.SDRAlgorithm_1.run(); // B becomes active
        this.predictionAlgorithm_1.run();

        // @t = 4
        assertTrue(B.getActiveState());
        assertTrue(B.getPreviousPredictingState());
        assertTrue(A.getPreviousActiveState());

        Synapse<Cell> B_synapses[] = new Synapse[1];
        B.getDistalSegments().get(0).getSynapses().toArray(B_synapses);
        double permanenceAfter = B_synapses[0].getPermanenceValue();
        double expectedPermanence = Synapse.INITIAL_PERMANENCE + Synapse.PERMANENCE_INCREASE;
        assertEquals(expectedPermanence, permanenceAfter);

        this.predictionAlgorithm_1.nextTimeStep();
    }

    public void test_runAtTEquals4_LTD() throws IOException
    {
        // @t = 3.5
        this.test_run();
        //this.retina.seeBMPImage("visionCell01_active.bmp");
        this.SDRAlgorithm_1.run(); // B does not becomes active
        this.predictionAlgorithm_1.run();

        // @t = 4
        assertFalse(B.getActiveState());
        assertTrue(B.getPreviousPredictingState());
        assertTrue(A.getPreviousActiveState());

        Synapse<Cell> B_synapses[] = new Synapse[1];
        B.getDistalSegments().get(0).getSynapses().toArray(B_synapses);
        double permanenceAfter = B_synapses[0].getPermanenceValue();
        double expectedPermanence = Synapse.INITIAL_PERMANENCE - Synapse.PERMANENCE_DECREASE;
        assertEquals(expectedPermanence, permanenceAfter);

        this.predictionAlgorithm_1.nextTimeStep();
    }
}