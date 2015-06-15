package model.experiments.vision.MARK_II;

import model.MARK_II.BigNeocortex;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.MARK_II.connectTypes.SensorCellsToRegionRectangleConnect;
import model.MARK_II.BigNervousSystem;

import java.awt.*;
import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 6/15/2015.
 */
public class Experiment_1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Running Experiment_1.main() ...");

        // ===================== Build Nervous System ==========================
        int maxSizeOfARegionInMB = 512;
        // pass it an array of all Region names
        // Example List:
        // index_0 = root, 60, 60, 4, 20, 3
        // index_1 = A   , 60, 60, 4, 20, 3
        // ...

        String fourNeurons = "4"; // = neocortex layer 3 with 4 neurons per column
        String oneNeuron = "1"; // = neocortex layer 4 with 1 neuron per column
        String PMO = "20"; // = percent minimum overlap
        String DLA = "3"; // = desired local activity

        // NOTE: new region every 6 elements with keyword "Layer 5"
        // to create a Layer5Region.java class object
        String[] regionParameterListInOrder = {
                "root", "60", "60", fourNeurons, PMO, DLA,
                "A"   , "60", "60", fourNeurons, PMO, DLA,
                "B"   , "60", "60", fourNeurons, PMO, DLA,
                "C"   , "125", "125", fourNeurons, PMO, DLA,
                "D"   , "125", "125", fourNeurons, PMO, DLA,
                "E"   , "125", "125", fourNeurons, PMO, DLA,
                "F"   , "125", "125", fourNeurons, PMO, DLA,
                "G"   , "125", "125", fourNeurons, PMO, DLA,
                "H"   , "125", "125", fourNeurons, PMO, DLA,
                "layer 5 M"   , "125", "125", fourNeurons, PMO, DLA,
                "I"   , "250", "250", fourNeurons, PMO, DLA,
                "J"   , "250", "250", fourNeurons, PMO, DLA,
                "K"   , "250", "250", fourNeurons, PMO, DLA,
                "L"   , "250", "250", fourNeurons, PMO, DLA
        };

        // NOTE: new connection pattern every 7 elements or
        // change with 1 element with keywords "change to region REGION_NAME"
        String[] regionConnectionParameterListInOrder = {
                "0", "0", "30", "60", "A", "4", "4",
                "30", "0", "60", "60", "B", "4", "4",
                "change to region A",
                "0", "0", "60", "60", "C", "4", "4",
                "change to region B",
                "0", "0", "60", "60", "D", "4", "4",
                "change to region C",
                "0", "0", "63", "125", "E", "4", "4",
                "63", "0", "125", "125", "F", "4", "4",
                "change to region D",
                "0", "0", "63", "125", "G", "4", "4",
                "63", "0", "125", "125", "H", "4", "4",
                "change to region E",
                "0", "0", "125", "125", "I", "4", "4",
                "change to region F",
                "0", "0", "125", "125", "J", "4", "4",
                "change to region G",
                "0", "0", "125", "125", "K", "4", "4",
                "change to region H",
                "0", "0", "125", "125", "L", "4", "4",
                // connecting layer 5 region M
                "change to region I",
                "0", "0", "125", "125", "M", "4", "4",
                "change to region J",
                "0", "0", "125", "125", "M", "4", "4",
                "change to region K",
                "0", "0", "125", "125", "M", "4", "4",
                "change to region L",
                "0", "0", "125", "125", "M", "4", "4",
                "change to region M",
                "0", "0", "125", "125", "F", "4", "4"
        };

        String whereToSaveBigNeocortex = "./src/test/java/model/experiments/vision/MARK_II" +
                "/BigNervousSystemTest_Neocortex__0";
        BigNeocortex bigNeocortex = new BigNeocortex(maxSizeOfARegionInMB,
                regionParameterListInOrder, new RegionToRegionRectangleConnect(),
                regionConnectionParameterListInOrder, whereToSaveBigNeocortex);

        Dimension retinaDimension = new Dimension(1000, 1000);
        AbstractSensorCellsToRegionConnect opticNerve = new SensorCellsToRegionRectangleConnect();
        String[] retinaConnectionParameterListInOrder = {"0", "0", "500", "500", "I", "8", "8",
                "500", "0", "1000", "500", "J", "8", "8",
                "0", "500", "500", "1000", "K", "8", "8",
                "500", "500", "1000", "1000", "L", "8", "8"};

        String pathAndRetinaFileName = "" +
                "./src/test/java/model/experiments/vision/MARK_II" +
                "/retina.json";
        BigNervousSystem bigNervousSystem = new BigNervousSystem(2048, bigNeocortex,
                retinaDimension, opticNerve, retinaConnectionParameterListInOrder,
                pathAndRetinaFileName);

        System.out.println("Finished Experiment_1.main()");
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
