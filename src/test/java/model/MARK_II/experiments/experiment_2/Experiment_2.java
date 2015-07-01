package model.MARK_II.experiments.experiment_2;

import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRandomConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.generalAlgorithm.SpatialPooler;
import model.MARK_II.generalAlgorithm.TemporalPooler;
import model.MARK_II.region.Region;
import model.MARK_II.sensory.Retina;

import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 7/1/2015.
 */
public class Experiment_2 {
    private static String pathToExperiment_2_folder = "./src/test/java/model/experiments/vision/MARK_II" +
            "/experiment_2/";

    public static void main(String[] args) throws IOException {
        System.out.println("Running Experiment_2.main() ...");

        // NOTE: the following parameters are borrowed from numenta/nupic TP example
        // here: https://github.com/numenta/nupic/blob/master/examples/tp/hello_tp.py

        //    NUPIC                                     WalnutiQ
        // =========================================================
        // numberOfCols = 50                            6*8 = 48
        // cellsPerColumn = 2                               2
        // initialPerm = 0.5
        // connectedPerm = 0.5
        // minThreshold = 10
        // newSynapseCount = 10
        // permanenceInc = 0.1
        // permanenceDec = 0.0

        // Step 1: create Temporal Pooler instance with appropriate parameters
        Retina retina = new Retina(10, 10);
        Region region = new Region("root", 6, 8, 2, 10, 3);
        region.setInhibitionRadius(3);

        AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
        retinaToRegion.connect(retina.getVisionCells(), region.getColumns(),
                0, 0);

        SpatialPooler spatialPooler = new SpatialPooler(region);
        spatialPooler.setLearningState(true);

        TemporalPooler temporalPooler = new TemporalPooler(spatialPooler, 10);

//        this.retina.seeBMPImage("2.bmp");
//        this.spatialPooler.performPooling();
//        this.temporalPooler.performPooling();
//        this.temporalPooler.nextTimeStep();


        System.out.println("Finished Experiment_2.main()");
    }
}
