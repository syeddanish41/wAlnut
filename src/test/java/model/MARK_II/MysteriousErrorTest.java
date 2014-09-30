package model.MARK_II;

import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.Retina;
import java.io.IOException;

/**
 * Created by Huanqing on 9/30/2014.
 */
public class MysteriousErrorTest extends junit.framework.TestCase {

    private Retina retina;
    private Region region;
    private SpatialPooler spatialPooler;
    private TemporalPooler temporalPooler;

    public void setUp() throws IOException {
        // images this oldRetina will see are all 66x66 pixels
        this.retina = new Retina(66, 66);

        this.region = new Region("Region", 8, 8, 3, 77.8, 1);

        AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
        retinaToRegion.connect(this.retina.getVisionCells(), this.region, 0, 0);

        this.spatialPooler = new SpatialPooler(this.region);
        this.spatialPooler.setLearningState(true);

        this.retina.seeBMPImage("2.bmp");
        this.spatialPooler.performPooling();

        this.temporalPooler = new TemporalPooler(this.spatialPooler, 25);
        this.temporalPooler.setLearningState(true);
    }

    public int test_performTemporalPoolingOnRegion() {
        // segmentUpdateList.size = 0

        // in Phase 1
        //   segmentUpdateList.size += (segmentUpdate for each learning Neuron = # of active columns from SP)

        // in Phase 2
        //   segmentUpdateList.size += active segments(created by spatial pooling)
        //                          += any synapses that could have predicted this segments activation?

        // in Phase 3
        //   segmentUpdateList.size -= adapt segments on learning neurons
        //   segmentUpdateList.size -= adapt segments previously predictive & NOT currently predictive

        this.temporalPooler.performPooling();
        assertEquals(16, this.temporalPooler.getSegmentUpdateList().size());
        this.temporalPooler.nextTimeStep();

        this.spatialPooler.performPooling();
        this.temporalPooler.performPooling();

        int segmentUpdateListSize = this.temporalPooler.getSegmentUpdateList().size();
        //assertEquals(8, segmentUpdateListSize); // NOTE: why does this sometimes return 6?
        System.out.println("segmentUpdateListSize after TP = " + segmentUpdateListSize);
        this.temporalPooler.nextTimeStep();
        return segmentUpdateListSize;
    }

    public void test_Call20Times() throws IOException {
        boolean found6 = false;
        boolean found8 = false;
        int segmentUpdateListSize;
        for (int i = 0; i < 20; i++) {
            this.setUp();
            segmentUpdateListSize = this.test_performTemporalPoolingOnRegion();
            if (segmentUpdateListSize == 6) {
                found6 = true;
            } else if (segmentUpdateListSize == 8) {
                found8 = true;
            }

            if (found6 && found8) {
                break;
            }

            System.out.println("\n");
        }
    }
}
