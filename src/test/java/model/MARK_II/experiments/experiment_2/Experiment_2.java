package model.MARK_II.experiments.experiment_2;

import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRandomConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.generalAlgorithm.SpatialPooler;
import model.MARK_II.generalAlgorithm.TemporalPooler;
import model.MARK_II.region.Region;
import model.MARK_II.region.Synapse;
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
        // ==============================================================
        // numberOfCols =    50                         6*8 = 48
        int cellsPerColumn =   2; //                    rest all the same
        double initialPerm =   0.5;
        double connectedPerm = 0.5;
        int minThreshold =    10;
        int newSynapseCount = 10;
        double permanenceInc =   0.1;
        double permanenceDec =   0.0;
        // activationThreshold = 8?
        // globalDecay = 0?
        // burnIn = 1?
        // checkSynapseConsistency = False?
        // pamLength = 10?

        Synapse.INITIAL_PERMANENCE = initialPerm;
        Synapse.MINIMAL_CONNECTED_PERMANENCE = connectedPerm;
        Synapse.PERMANENCE_INCREASE = permanenceInc;
        Synapse.PERMANENCE_DECREASE = permanenceDec;

        // Step 1: create Temporal Pooler instance with appropriate parameters
        Retina retina = new Retina(10, 10);
        Region region = new Region("root", 6, 8, cellsPerColumn, minThreshold, 3);
        region.setInhibitionRadius(3);

        AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
        retinaToRegion.connect(retina.getVisionCells(), region.getColumns(),
                0, 0);

        SpatialPooler spatialPooler = new SpatialPooler(region);
        spatialPooler.setLearningState(true);

        TemporalPooler temporalPooler = new TemporalPooler(spatialPooler, newSynapseCount);

//        this.retina.seeBMPImage("2.bmp");
//        this.spatialPooler.performPooling();
//        this.temporalPooler.performPooling();
//        this.temporalPooler.nextTimeStep();


        System.out.println("Finished Experiment_2.main()");
    }
}
