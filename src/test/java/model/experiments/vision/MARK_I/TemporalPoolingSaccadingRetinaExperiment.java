package model.experiments.vision.MARK_I;

import junit.framework.TestCase;
import model.unimplementedBiology.NervousSystem;

import java.io.IOException;

/**
 * Created by qliu on 6/30/14.
 */
public class TemporalPoolingSaccadingRetinaExperiment extends TestCase {
    private NervousSystem partialNervousSystem;

    public void setUp() throws IOException {
        this.partialNervousSystem = this.constructConnectedPartialNervousSystem();
    }

    private NervousSystem constructConnectedPartialNervousSystem() {
//        Neocortex unconnectedNeocortex = new Neocortex(new Region("V1", 4, 4,
//                4, 50, 3), new RegionToRegionRectangleConnect());
//
//        LGN unconnectedLGN = new LGN(new Region("LGN", 8, 8, 1, 50, 3));
//
//        Retina unconnectedRetina = new Retina(66, 66);
//
//        NervousSystem nervousSystem = new NervousSystem(unconnectedNeocortex,
//                unconnectedLGN, unconnectedRetina);
//
//        // connect OldRetina to LGN
//        Retina retina = nervousSystem.getPNS().getSNS().getRetine();
//        LGN LGN = nervousSystem.getCNS().getBrain().getThalamus().getLGN();
//        AbstractSensorCellsToRegionConnect opticNerve = new SensorCellsToRegionRectangleConnect();
//        opticNerve.connect(retina.getVisionCells(), LGN.getRegion(), 0, 0);
//
//        // connect LGN to very small part of V1 Region of Neocortex
//        Neocortex neocortex = nervousSystem.getCNS().getBrain().getCerebrum()
//                .getCerebralCortex().getNeocortex();
//        AbstractRegionToRegionConnect regionToRegionConnect = new RegionToRegionRectangleConnect();
//        regionToRegionConnect.connect(LGN.getRegion(),
//                neocortex.getCurrentRegion(), 0, 0);
//
//        return nervousSystem;
        return null;
    }

    public void test_trainPartialNervousSystemThroughOneIterationOfLearning() throws IOException {
//        Retina retina = partialNervousSystem.getPNS().getSNS().getRetine();
//        Region LGNRegion = partialNervousSystem.getCNS().getBrain()
//                .getThalamus().getLGN().getRegion();
//
//        retina.seeBMPImage("2.bmp");
//
//        SpatialPooler spatialPooler = new SpatialPooler(LGNRegion);
//        spatialPooler.setLearningState(true);
//        spatialPooler.performPooling();
//        Set<ColumnPosition> LGNNeuronActivity = spatialPooler
//                .getActiveColumnPositions();
//
//        assertEquals(11, LGNNeuronActivity.size());
        TestCase.assertEquals(1, 2 - 1);
    }
}
