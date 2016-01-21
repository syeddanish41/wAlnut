package model.MARK_II.experiments.experiment_2;

import junit.framework.TestCase;
import java.io.IOException;

import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.generalAlgorithm.failureResearch.temporalAlgorithms.PredictionAlgorithm_1;
import model.MARK_II.region.Layer5Region;
import model.MARK_II.region.Region;
import model.MARK_II.sensory.ImageViewer;
import model.MARK_II.sensory.Retina;
import model.MARK_II.util.Point3D;
import model.MARK_II.util.Rectangle;
import java.awt.Point;

/**
 * Refer to Experiment 2 drawing here: https://github.com/WalnutiQ/wAlnut/issues/199
 *
 * @author raarathi
 * @author Q Liu (quinnliu@vt.edu)
 * @version 1/17/16
 */
public class Experiment_2 extends TestCase {
    private ImageViewer imageViewer;
    private Region regionA;
    private Layer5Region regionB;
    private Region regionC;
    private SDRAlgorithm_1 sdrAlgorithm_1;
    private PredictionAlgorithm_1 predictionAlgorithm_1;

   public void setUp() throws IOException{
       Retina retina = new Retina(64, 64);

       this.regionA = new Region("A", 32, 32, 1, 20, 3);
       this.regionB = new Layer5Region("B", 32, 32, 1, 20, 3);
       this.regionC = new Region("C", 16, 16, 1, 20, 3);

       AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
       retinaToRegion.connect(retina.getVisionCells(), this.regionA.getColumns(), 8, 8);

       AbstractRegionToRegionConnect regionToRegion = new RegionToRegionRectangleConnect();
       regionToRegion.connect(this.regionA.getColumns(), this.regionB.getColumns(), 5, 5);
       regionToRegion.connect(this.regionA.getColumns(), this.regionC.getColumns(new Rectangle(new Point(0,0), new Point(7,15))), 5, 5);
       regionToRegion.connect(this.regionB.getColumns(), this.regionC.getColumns(new Rectangle(new Point(7,0), new Point(15,15))), 5, 5);

       this.imageViewer = new ImageViewer("2_minus_1.bmp", retina); // 400 x 400 pixels
       this.imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(new Point3D(200, 200, 200));

       this.sdrAlgorithm_1 = new SDRAlgorithm_1(1000, this.regionA, 10);
       this.sdrAlgorithm_1.setLearningState(true);
       this.predictionAlgorithm_1 = new PredictionAlgorithm_1(this.sdrAlgorithm_1);
       this.predictionAlgorithm_1.setLearningState(true);
   }

    void runAlgorithmOneTimeStep() throws IOException {
        // TODO: error not seeing any input
        this.sdrAlgorithm_1.run(); // on Region A
        System.out.println("Region A SDR size: " + this.sdrAlgorithm_1.getActiveColumnPositions().size());
        this.sdrAlgorithm_1.changeRegion(this.regionB);
        System.out.println("Region B SDR size: " + this.sdrAlgorithm_1.getActiveColumnPositions().size());
        this.sdrAlgorithm_1.run(); // on Region B
        this.sdrAlgorithm_1.changeRegion(this.regionC);
        System.out.println("Region C SDR size: " + this.sdrAlgorithm_1.getActiveColumnPositions().size());
        this.sdrAlgorithm_1.run(); // on Region C

        this.predictionAlgorithm_1.run(); // on Region C
        this.sdrAlgorithm_1.changeRegion(this.regionA);

        Point3D nextRetinaPosition = this.regionB.getMotorOutput(
                this.imageViewer.getBoxRetinaIsStuckIn());
        int[][] seenArea = this.imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(nextRetinaPosition);

        System.out.println("nextRetinaPosition = (" + nextRetinaPosition.getX() + ", " + nextRetinaPosition.getY() + ", " + nextRetinaPosition.getZ() + ")");
    }

    public void test_experiment_2() throws IOException {
        assertEquals(2-1, 1);

        for (int i = 0; i < 1; i++) {
            this.runAlgorithmOneTimeStep();
        }
    }
}
