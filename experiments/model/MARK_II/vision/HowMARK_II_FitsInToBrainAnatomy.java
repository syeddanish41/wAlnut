package model.MARK_II.vision;

import com.google.gson.Gson;
import junit.framework.TestCase;
import model.ImageViewer;
import model.Layer5Region;
import model.MARK_II.Neocortex;
import model.MARK_II.Region;
import model.MARK_II.SpatialPooler;
import model.MARK_II.TemporalPooler;
import model.MARK_II.connectTypes.*;
import model.Retina;
import model.unimplementedBiology.NervousSystem;
import model.util.JsonFileInputOutput;
import model.util.Point3D;
import model.util.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Nathan Waggoner(nwagg14@vt.edu)
 * @version 3/14/2015
 */
public class HowMARK_II_FitsInToBrainAnatomy {
    private NervousSystem partialNervousSystem;

    /**
     * For saving the Java NervousSystem object as a JSON file later on.
     */
    private Gson gson;
    private SpatialPooler spatialPooler;
    private TemporalPooler temporalPooler;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

//    public void setUp() throws IOException {
//        this.partialNervousSystem = this.buildNervousSystem();
//
//        this.gson = new Gson();
//    }

    /**
     * The following is a BIRDS EYE VIEW of a connected partial nervous system with a 3D drawing here:
     * https://github.com/WalnutiQ/WalnutiQ/issues/107
     *
     * LEGEND:
     * root, A, B, C, ... Z are Region names
     * M = parietal lobe region meaning it's neuron activity directly causes the Retina to move to it's new
     * position within the box Retina is stuck in.
     *
     *             root (runs just temporal pooling algorithm)
     *           /      \
     *          A        B (higher layer 3 runs spatial & temporal pooling)
     *          |        |
     *          C        D (higher Layer 4 runs spatial pooling)
     *         / \      / \
     * +<---- F   E    G   H (Layer 3 runs spatial pooling & temporal pooling)
     * |      |   |    |   |
     * M----->J   I    K   L (Layer 4 runs spatial pooling)
     * |      |   |    |   |
     * |       \  |    |  /
     * |  +-----\-|----|-/-----+
     * |  |      \|    |/      |
     * +--------> Retina       | <= box Retina is stuck in
     *    |                    |
     *    ImageRetinaIsLookingAt
     */
    private NervousSystem buildNervousSystem() {
        // HeapTracker.startTrace(); // start tracing
        // HeapTracker.takeLiveHeapSample();
        int fourNeurons = 4; // = neocortex layer 3 with 4 neurons per column
        int oneNeuron = 1; // = neocortex layer 4 with 1 neuron per column
        double PMO = 20; // = percent minimum overlap
        int DLA = 3; // = desired local activity

        // HeapTracker.takeLiveHeapSample();
        // regions
        Region root = new Region("root", 60, 60, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region A = new Region("A", 60, 60, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region B = new Region("B", 60, 60, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region C = new Region("C", 125, 125, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region D = new Region("D", 125, 125, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region E = new Region("E", 125, 125, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region F = new Region("F", 125, 125, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region G = new Region("G", 125, 125, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region H = new Region("H", 125, 125, fourNeurons, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region M = new Layer5Region("M", 125, 125, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region I = new Region("I", 250, 250, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region J = new Region("J", 250, 250, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region K = new Region("K", 250, 250, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();
        Region L = new Region("L", 250, 250, oneNeuron, PMO, DLA);
        // HeapTracker.takeLiveHeapSample();

        // connecting all regions together
//        Neocortex neocortex = new Neocortex(root, new RegionToRegionRectangleConnect());
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(30, 60)), A, 4, 4);
//        neocortex.addToCurrentRegion(new Rectangle(new Point(30, 0), new Point(60, 60)), B, 4, 4);
//
//        neocortex.changeCurrentRegionTo("A");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(60, 60)), C, 4, 4);
//
//        neocortex.changeCurrentRegionTo("B");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(60, 60)), D, 4, 4);
//
//        neocortex.changeCurrentRegionTo("C");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(63, 125)), E, 4, 4);
//        neocortex.addToCurrentRegion(new Rectangle(new Point(63, 0), new Point(125, 125)), F, 4, 4);
//
//        neocortex.changeCurrentRegionTo("D");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(63, 125)), G, 4, 4);
//        neocortex.addToCurrentRegion(new Rectangle(new Point(63, 0), new Point(125, 125)), H, 4, 4);
//
//        neocortex.changeCurrentRegionTo("E");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), I, 4, 4);
//
//        neocortex.changeCurrentRegionTo("F");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), J, 4, 4);
//
//        neocortex.changeCurrentRegionTo("G");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), K, 4, 4);
//
//        neocortex.changeCurrentRegionTo("H");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), L, 4, 4);
//
//        // connecting layer 5 region M
//        neocortex.changeCurrentRegionTo("I");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), M, 4, 4);
//        neocortex.changeCurrentRegionTo("J");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), M, 4, 4);
//        neocortex.changeCurrentRegionTo("K");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), M, 4, 4);
//        neocortex.changeCurrentRegionTo("L");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), M, 4, 4);
//
//        neocortex.changeCurrentRegionTo("M");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(125, 125)), F, 4, 4);
//
//        // NOTE: I, J, K, & L are connected to different parts of the same Retina
//        Retina retina = new Retina(1000, 1000);
//
//        AbstractSensorCellsToRegionConnect opticNerve = new SensorCellsToRegionRectangleConnect();
//        // now we can overlap
//        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(0, 0), new Point(500, 500))), I.getColumns(), 8, 8); // .getVisionCells(topLeftPoint, bottomRightPoint)
//        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(500, 0), new Point(1000, 500))), J.getColumns(), 8, 8);
//        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(0, 500), new Point(500, 1000))), K.getColumns(), 8, 8);
//        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(500, 500), new Point(1000, 1000))), L.getColumns(), 8, 8);

//        NervousSystem nervousSystem = new NervousSystem(neocortex, null, retina); // no LGN with circle surround input for now

        // HeapTracker.stopTrace();
        return null;
        //return nervousSystem;
    }

    /*
    public void test_HowToRunSingleLearningAlgorithmOnNervousSystem() throws IOException {
        Neocortex neocortex = this.partialNervousSystem.getCNS().getBrain()
                .getCerebrum().getCerebralCortex().getNeocortex();

        //ImageViewer imageViewer = new ImageViewer("imageOfHumanFace1000x1000pixels.bmp",
        //        this.partialNervousSystem.getPNS().getSNS().getRetina());

        //runForreal(neocortex, imageViewer);

        // save partialNervousSystemObject object in JSON format
        String partialNervousSystemObject = this.gson
                .toJson(this.partialNervousSystem);
        JsonFileInputOutput
                .saveObjectToTextFile(partialNervousSystemObject,
                        "./experiments/model/MARK_II/vision/PartialNervousSystem_MARK_II.json");
    }
    */

//    public void test_FillerTest() {
//        assertEquals("TODO: write test", "TODO: write test");
//    }

    public void runForreal(Neocortex neocortex, ImageViewer imageViewer) throws IOException {
        this.spatialPooler = new SpatialPooler(neocortex.getRegion("I"));
        this.spatialPooler.setLearningState(true);

        this.temporalPooler = new TemporalPooler(this.spatialPooler, 25);
        this.temporalPooler.setLearningState(true);

        // initialize view
        imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(new Point3D(500, 500, 500));

        String[] secondLayer3RegionNames = {"A", "B"};
        String[] secondLayer4RegionNames = {"C", "D"};
        String[] firstLayer3RegionNames = {"E", "F", "G", "H"};
        String[] firstLayer4RegionNames = {"I", "J", "K", "L"};

        final int NUMBER_OF_TIMES_TO_RUN_LEARNING_ALGORITHM = 1000;
        for (int i = 0; i < NUMBER_OF_TIMES_TO_RUN_LEARNING_ALGORITHM; i++) {

            for(String regionName : firstLayer4RegionNames){
                Region currRegion = neocortex.getRegion(regionName);
                this.spatialPooler.changeRegion(currRegion);
                this.spatialPooler.performPooling();
            }
            for(String regionName : firstLayer3RegionNames){
                Region currRegion = neocortex.getRegion(regionName);
                this.spatialPooler.changeRegion(currRegion);
                this.spatialPooler.performPooling();

                this.temporalPooler.performPooling();
                this.temporalPooler.nextTimeStep();
            }

            Layer5Region layer5Region = (Layer5Region) neocortex.getRegion("M");
            Point3D nextRetinaPosition = layer5Region
                    .getMotorOutput(imageViewer.getBoxRetinaIsStuckIn());

            imageViewer.saccadeRetinaToNewPositionAndGetWhatItSees(nextRetinaPosition);

            for(String regionName : secondLayer4RegionNames){
                Region currRegion = neocortex.getRegion(regionName);
                this.spatialPooler.changeRegion(currRegion);
                this.spatialPooler.performPooling();
            }
            for(String regionName : secondLayer3RegionNames){
                Region currRegion = neocortex.getRegion(regionName);
                this.spatialPooler.changeRegion(currRegion);
                this.spatialPooler.performPooling();

                this.temporalPooler.performPooling();
                this.temporalPooler.nextTimeStep();
            }

           this.spatialPooler.changeRegion(neocortex.getRegion("root"));
           this.temporalPooler.performPooling();
           this.temporalPooler.nextTimeStep();
       }
    }
}