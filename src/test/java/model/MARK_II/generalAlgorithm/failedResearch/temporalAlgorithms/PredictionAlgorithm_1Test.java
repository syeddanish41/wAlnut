package model.MARK_II.generalAlgorithm.failedResearch.temporalAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.generalAlgorithm.ColumnPosition;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.generalAlgorithm.failureResearch.temporalAlgorithms.PredictionAlgorithm_1;
import model.MARK_II.region.*;
import model.MARK_II.sensory.Retina;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Aarathi Raghuraman (raarathi@vt.edu)
 * @author Q Liu (quinnliu@vt.edu)
 * @version 1/31/2016
 */
public class PredictionAlgorithm_1Test extends TestCase {
    private Retina retina;
    private Region region;
    private SDRAlgorithm_1 SDRAlgorithm_1;
    private PredictionAlgorithm_1 predictionAlgorithm_1;
    private Neuron A;
    private Neuron B;

    public void setUp() {
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
        //ColumnPosition A_position = new ColumnPosition(0, 0);
        this.B = this.region.getColumn(0,1).getNeuron(0);
        //ColumnPosition B_position = new ColumnPosition(0, 1);

        // @t = 0
        this.retina.seeBMPImage("visionCell00_active.bmp");
        this.SDRAlgorithm_1.run();

        this.predictionAlgorithm_1.run();
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 1
        assertFalse(A.getActiveState());
        assertTrue(A.getPreviousActiveState());

        this.retina.seeBMPImage("visionCell01_active.bmp");
        this.SDRAlgorithm_1.run();

        this.predictionAlgorithm_1.run();
        this.predictionAlgorithm_1.nextTimeStep();

        // @t = 2
        assertFalse(B.getActiveState());
        assertTrue(B.getPreviousActiveState());

        // Check for synapse between B and A
        assertEquals(1, B.getDistalSegments().size());
        Set<Synapse<Cell>> synapses = B.getDistalSegments().get(0).getSynapses();
//        assertEquals(1, synapses.size());
//        for(Synapse<Cell> synapse: synapses)
//        {
//            assertEquals(A, synapse.getCell());
//        }
//        assertFalse(B.getActiveState());
//        assertFalse(A.getActiveState());
//        this.predictionAlgorithm_1.run();
//        this.predictionAlgorithm_1.nextTimeStep();
//
//        // @t = 3
//        A.setActiveState(true);
//        this.predictionAlgorithm_1.run();
//        assertTrue(B.getPredictingState());
//        this.predictionAlgorithm_1.nextTimeStep();
    }

    // B becomes active from t=3 to t=4
//    public void test_runAtTEquals4_Case1() throws IOException
//    {
//        this.test_run();
//        B.setActiveState(true);
//        Synapse<Cell>[] synapses = (Synapse<Cell>[])(B.getDistalSegments().get(0).getSynapses()).toArray();
//        double permanence = synapses[0].getPermanenceValue();
//        this.predictionAlgorithm_1.run();
//        double permanenceAfter = synapses[0].getPermanenceValue();
//        boolean LTPHappened = permanenceAfter>permanence?true:false;
//        assertTrue(LTPHappened);
//        this.predictionAlgorithm_1.nextTimeStep();
//    }

    // B does not become active from t=3 to t=4
//    public void test_runAtTEquals4_Case2() throws IOException
//    {
//        this.test_run();
//        Synapse<Cell>[] synapses = (Synapse<Cell>[])(B.getDistalSegments().get(0).getSynapses()).toArray();
//        double permanence = synapses[0].getPermanenceValue();
//        this.predictionAlgorithm_1.run();
//        double permanenceAfter = synapses[0].getPermanenceValue();
//        boolean LTPHappened = permanenceAfter>permanence?true:false;
//        assertFalse(LTPHappened);
//        this.predictionAlgorithm_1.nextTimeStep();
//    }
}
