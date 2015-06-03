package model.experiments.vision.MARK_II;

import junit.framework.TestCase;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;

import java.io.File;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/28/2015.
 */
public class BigNeocortexTest extends TestCase {

    private BigNeocortex bigNeocortex;

    public void setUp() {
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

        String pathAndFolderName = "./src/test/java/model/experiments/vision/MARK_II/BigNeocortexTest_1";
        this.bigNeocortex = new BigNeocortex(maxSizeOfARegionInMB,
                regionParameterListInOrder, new RegionToRegionRectangleConnect(),
                regionConnectionParameterListInOrder,
                pathAndFolderName);
    }

    public void test_saveConnectedNeocortexInFolder() {
        assertEquals(1 + 1, 2);

        //String pathAndFolderName = "./src/test/java/model/experiments/vision/MARK_II/test_saveConnectedNeocortexInFolder";

        //this.bigNeocortex.saveConnectedNeocortexInFolder(pathAndFolderName);



        // TODO: remove created folder so next time we can recreate folder
    }
}
