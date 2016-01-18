package model.MARK_II.experiments.experiment_2;

import junit.framework.TestCase;
import java.io.IOException;

import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.region.Region;
import model.MARK_II.sensory.ImageViewer;
import model.MARK_II.sensory.Retina;
import model.MARK_II.util.Point3D;
import model.MARK_II.util.Rectangle;
import java.awt.Point;

import java.util.Arrays;

/**
 * Created by qliu on 1/17/16.
 * Created by raarathi on 1/17/16.
 */
public class Experiment_2 extends TestCase {
    private ImageViewer imageViewer;
    private Region regionA;
    private Region regionB;
    private Region regionC;

   public void setUp() throws IOException{
       Retina retina = new Retina(64, 64);

       regionA = new Region("A", 32, 32, 1, 20, 3);
       regionB = new Region("B", 32, 32, 1, 20, 3);
       regionC = new Region("C", 16, 16, 1, 20, 3);

       // Connecting retina to region B
       AbstractSensorCellsToRegionConnect retinaToRegion = new SensorCellsToRegionRectangleConnect();
       retinaToRegion.connect(retina.getVisionCells(), this.regionB.getColumns(), 0, 0);

       AbstractRegionToRegionConnect regionToRegion = new RegionToRegionRectangleConnect();
       // Connecting region A to B
       regionToRegion.connect(this.regionB.getColumns(), this.regionA.getColumns(), 0, 0);
       // Connecting region A to C:left
       regionToRegion.connect(this.regionA.getColumns(), this.regionC.getColumns(new Rectangle(new Point(0,0), new Point(7,15))), 0, 0);
       // Connecting region B to C:right
       regionToRegion.connect(this.regionB.getColumns(), this.regionC.getColumns(new Rectangle(new Point(7,0), new Point(15,15))), 0, 0);

       // 2-1
       this.imageViewer = new ImageViewer("2_minus_1.bmp", retina);
    }

    public void test_experiment_2() {
        assertEquals(2-1, 1);
    }


//    int[][] seenAreaFittedToRetinaSize = this.imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(new Point3D(33, 33, 33));
//    assertTrue(Arrays.deepEquals(this.twoDotBMP, seenAreaFittedToRetinaSize));
//    assertEquals(66, seenAreaFittedToRetinaSize.length);
//    assertEquals(66, seenAreaFittedToRetinaSize[0].length);
//
//    int[][] shiftToTheLeftAndZoomIn = this.imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(new Point3D(16, 33, 25));
//    assertEquals(66, shiftToTheLeftAndZoomIn.length);
//    assertEquals(66, shiftToTheLeftAndZoomIn[0].length);
//    assertTrue(Arrays.deepEquals(this.twoShiftedToTheLeftZoomedIn, shiftToTheLeftAndZoomIn));
}
