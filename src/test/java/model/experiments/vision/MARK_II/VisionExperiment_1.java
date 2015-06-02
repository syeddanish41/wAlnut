package model.experiments.vision.MARK_II;

import junit.framework.TestCase;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.Retina;

import java.awt.*;
import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/27/2015.
 */
public class VisionExperiment_1 extends TestCase {

    public void setUp() {

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Running VisionExperiment_1.main() ...");

        // ===================== Build Nervous System ==========================
        int maxSizeOfARegionInMB = 1024;
        // pass it an array of all Region names
        // Example List:
        // index_0 = root, 60, 60, 4, 20, 3
        // index_1 = A   , 60, 60, 4, 20, 3

        // NOTE: new region every 6 elements
        String[] regionParameterListInOrder = {"root", "60", "60", "4", "20", "3",
                                               "A", "60", "60", "4", "20", "3"};

        // NOTE: new connection pattern every 7 elements
        String[] regionConnectionParameterListInOrder = {"0", "0", "30", "60", "A", "4", "4",
                                                         "change to region A"};

        BigNeocortex bigNeocortex = new BigNeocortex(maxSizeOfARegionInMB,
                regionParameterListInOrder, new RegionToRegionRectangleConnect(),
                regionConnectionParameterListInOrder);

        Dimension retinaDimension = new Dimension(1000, 1000);
        AbstractSensorCellsToRegionConnect opticNerve = new SensorCellsToRegionRectangleConnect();
        String[] retinaConnectionParameterListInOrder = {"0", "0", "500", "500", "I", "8", "8",
                                                         "500", "0", "1000", "500", "J", "8", "8",
                                                         "0", "500", "500", "1000", "K", "8", "8",
                                                         "500", "500", "1000", "1000", "L", "8", "8"};

        BigNervousSystem bigNervousSystem = new BigNervousSystem(1024, bigNeocortex,
                retinaDimension, opticNerve, retinaConnectionParameterListInOrder);


        System.out.println("Finished VisionExperiment_1.main()");
    }

    public void test_HowToRunAlgorithmOnceOnNervousSystem() {
//        Neocortex neocortex = this.partialNervousSystem.getCNS().getBrain()
//                .getCerebrum().getCerebralCortex().getNeocortex();
//
//        //ImageViewer imageViewer = new ImageViewer("imageOfHumanFace1000x1000pixels.bmp",
//        //        this.partialNervousSystem.getPNS().getSNS().getRetina());
//
//        //runForreal(neocortex, imageViewer);
//
//        // save partialNervousSystemObject object in JSON format
//        String partialNervousSystemObject = this.gson
//                .toJson(this.partialNervousSystem);
//        FileInputOutput
//                .saveObjectToTextFile(partialNervousSystemObject,
//                        "./experiments/model/MARK_II/vision/PartialNervousSystem_MARK_II.json");
    }
}
