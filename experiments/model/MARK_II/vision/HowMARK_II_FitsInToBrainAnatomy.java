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
 * @version 12/18/2014
 */
public class HowMARK_II_FitsInToBrainAnatomy extends junit.framework.TestCase {
    private NervousSystem partialNervousSystem;

    /**
     * For saving the Java NervousSystem object as a JSON file later on.
     */
    private Gson gson;
    private SpatialPooler spatialPooler;
    private TemporalPooler temporalPooler;

    public void setUp() throws IOException {
        //this.partialNervousSystem = this.buildNervousSystem();

        this.gson = new Gson();
    }

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
     * +<---- E   F    G   H (Layer 3 runs spatial pooling & temporal pooling)
     * |      |   |    |   |
     * M----->I   J    K   L (Layer 4 runs spatial pooling)
     * |      |   |    |   |
     * |       \  |    |  /
     * |  +-----\-|----|-/-----+
     * |  |      \|    |/      |
     * +--------> Retina       | <= box Retina is stuck in
     *    |                    |
     *    ImageRetinaIsLookingAt
     */
    private NervousSystem buildNervousSystem() {
        final int L3NPM = 4; // = layer 3 neurons per column
        final int L4NPM = 1; // = layer 4 neurons per column
        double PMO = 20; // = percent minimum overlap
        int DLA = 3; // = desired local activity

        Neocortex neocortex = new Neocortex(new Region("root", 60, 60, L3NPM, PMO, DLA), new RegionToRegionRectangleConnect());
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(29, 59)), new Region("A", 125, 125, L3NPM, PMO, DLA), 0, 0);
        neocortex.addToCurrentRegion(new Rectangle(new Point(30, 0), new Point(59, 59)), new Region("B", 125, 125, L3NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("A");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), new Region("C", 125, 125, L4NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("B");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), new Region("D", 125, 125, L4NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("C");
        Region E = new Region("E", 125, 125, L3NPM, PMO, DLA);
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(62, 124)), E, 0, 0);
        neocortex.addToCurrentRegion(new Rectangle(new Point(63, 0), new Point(124, 124)), new Region("F", 125, 125, L3NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("D");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(62, 124)), new Region("G", 125, 125, L3NPM, PMO, DLA), 0, 0);
        neocortex.addToCurrentRegion(new Rectangle(new Point(63, 0), new Point(124, 124)), new Region("H", 125, 125, L3NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("E");
        Region I = new Region("I", 250, 250, L4NPM, PMO, DLA);
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), I, 0, 0);

        neocortex.changeCurrentRegionTo("I");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(249, 249)), new Layer5Region("M", 250, 250, L3NPM, PMO, DLA), 0, 0);

        neocortex.changeCurrentRegionTo("M");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(249, 249)), E, 0, 0);

        Region J = new Region("J", 250, 250, L4NPM, PMO, DLA);
        neocortex.changeCurrentRegionTo("F");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), J, 0, 0);

        Region K = new Region("K", 250, 250, L4NPM, PMO, DLA);
        neocortex.changeCurrentRegionTo("G");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), K, 0, 0);

        Region L = new Region("L", 250, 250, L4NPM, PMO, DLA);
        neocortex.changeCurrentRegionTo("H");
        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), L, 0, 0);

        // NOTE: I, J, K, & L are connected to different parts of the same Retina
        Retina retina = new Retina(1000, 1000);

        AbstractSensorCellsToRegionConnect opticNerve = new SensorCellsToRegionRectangleConnect();
        // now we can overlap
        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(0, 0), new Point(499, 499))), I, 0, 0); // .getVisionCells(topLeftPoint, bottomRightPoint)
        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(499, 0), new Point(999, 499))), J, 0, 0);
        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(0, 499), new Point(499, 999))), K, 0, 0);
        opticNerve.connect(retina.getVisionCells(new Rectangle(new Point(499, 499), new Point(999, 999))), L, 0, 0);

        NervousSystem nervousSystem = new NervousSystem(neocortex, null, retina); // no LGN with circle surround input for now

        return nervousSystem;
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

    public void test_FillerTest() {
        assertEquals("TODO: write test", "TODO: write test");
    }

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