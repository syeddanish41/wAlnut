package model.MARK_II.generalAlgorithm.failedResearch.spatialAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.generalAlgorithm.ColumnPosition;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.region.Cell;
import model.MARK_II.region.Column;
import model.MARK_II.region.Region;
import model.MARK_II.region.Synapse;
import model.MARK_II.sensory.VisionCell;

import java.util.HashSet;


/**
 * @author Manpreet Singh (mghotra81@gmail.com)
 * @author Aarathi Raghuraman (raarathi@vt.edu)
 * TODO - Using Mockito Library for Testbed #221
 */
public class SDRAlgorithm_1_Test extends TestCase {

    private SDRAlgorithm_1 sdrAlgorithm1;
    private Region region;

    // setup for SDRAlgo test
    public void setUp() {

        // Creating a region with 3 columns and 1,2,3 active synapses respectively
        region = new Region("vision1", 1, 3, 4, 20, 3);
        Column column00 = region.getColumn(0, 0);
        Column column01 = region.getColumn(0, 1);
        Column column02 = region.getColumn(0, 2);

        VisionCell visionCell_1 = new VisionCell();
        visionCell_1.setActiveState(true);

        VisionCell visionCell_2 = new VisionCell();
        visionCell_2.setActiveState(true);

        VisionCell visionCell_3 = new VisionCell();
        visionCell_3.setActiveState(true);

        Synapse<Cell> syn00 = new Synapse<Cell>(visionCell_1, 1, 1);
        Synapse<Cell> syn01 = new Synapse<Cell>(visionCell_2, 1, 2);
        Synapse<Cell> syn02 = new Synapse<Cell>(visionCell_3, 1, 3);

        column00.getProximalSegment().addSynapse(syn00);
        column01.getProximalSegment().addSynapse(syn00);
        column01.getProximalSegment().addSynapse(syn01);
        column02.getProximalSegment().addSynapse(syn00);
        column02.getProximalSegment().addSynapse(syn01);
        column02.getProximalSegment().addSynapse(syn02);

        sdrAlgorithm1 = new SDRAlgorithm_1(1, region,66.67);

    }

    // Test the SDRAlgorithm_1 for 67% activity
    public void test_run() {
        HashSet<ColumnPosition> sparseRepresentation = new HashSet<ColumnPosition>();
        sparseRepresentation.add(new ColumnPosition(0, 1));
        sparseRepresentation.add(new ColumnPosition(0, 2));
        assertEquals(sparseRepresentation, sdrAlgorithm1.run());
    }

}
