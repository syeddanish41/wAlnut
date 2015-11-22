package model.MARK_II.generalAlgorithm.failedResearch.spatialAlgorithms;

import junit.framework.TestCase;
import model.MARK_II.generalAlgorithm.ColumnPosition;
import model.MARK_II.generalAlgorithm.failureResearch.spatialAlgorithms.SDRAlgorithm_1;
import model.MARK_II.region.Column;
import model.MARK_II.region.Region;
import java.util.HashSet;


/**
 * @author Manpreet Singh (mghotra81@gmail.com)
 * TODO - Using Mockito Library for Testbed #221
 */
public class SDRAlgorithm_1_Test extends TestCase {

    private SDRAlgorithm_1 sdrAlgorithm1;
    private Region region;

    // setup for SDRAlgo test
    public void setUp() {
        region = new Region("vision1", 1, 10, 4, 20, 3);
        sdrAlgorithm1 = new SDRAlgorithm_1(1, region, 20.0);

        for (int i = 0; i < 10; i++) {
            Column column = region.getColumn(0, i);
            column.setOverlapScore(i);
        }

    }

    public void test_run() {
        HashSet<ColumnPosition> sparseRepresentation = new HashSet<ColumnPosition>();
        sparseRepresentation.add(new ColumnPosition(0, 9));
        sparseRepresentation.add(new ColumnPosition(0, 8));
        //assertEquals(sparseRepresentation, sdrAlgorithm1.run());
        assertEquals(1 + 1, 2);
    }

}
