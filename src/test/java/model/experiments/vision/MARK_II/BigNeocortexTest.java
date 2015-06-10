package model.experiments.vision.MARK_II;

import junit.framework.TestCase;
import model.MARK_II.Region;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.util.Rectangle;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 6/9/2015.
 */
public class BigNeocortexTest extends TestCase {

    private BigNeocortex bigNeocortex;
    private String[] regionParameterListInOrder;
    private String[] regionConnectionParameterListInOrder;
    private String pathAndFolderName;

    public void setUp() throws IOException {
        int maxSizeOfARegionInMB = 256;
        // pass it an array of all Region names
        // Example List:
        // index_0 = root, 60, 60, 4, 20, 3
        // index_1 = A   , 60, 60, 4, 20, 3

        // NOTE: new region every 6 elements
        this.regionParameterListInOrder = new String[] {"root", "60", "60", "4", "20", "3",
                                               "A", "60", "60", "4", "20", "3"};

        // NOTE: new connection pattern every 7 elements
        this.regionConnectionParameterListInOrder = new String[] {
                "0", "0", "30", "60", "A", "4", "4",
                "change to region A"};

        this.pathAndFolderName = "" +
                "./src/test/java/model/experiments/vision/MARK_II" +
                "/BigNeocortexTest__0";
        this.bigNeocortex = new BigNeocortex(maxSizeOfARegionInMB,
                regionParameterListInOrder, new
                RegionToRegionRectangleConnect(),
                regionConnectionParameterListInOrder,
                pathAndFolderName);
    }

    public void test_instantiateAndSaveAllUnconnectedRegions() throws IOException {
        // make sure root.json and A.json are in MARK_II/BigNeocortexTest__0
        File path__0 = new File("" +
                "./src/test/java/model/experiments/vision/MARK_II/BigNeocortexTest__0");
        assertTrue(this.bigNeocortex.isFileInList("root.json", path__0
                .listFiles()));
        assertTrue(this.bigNeocortex.isFileInList("A.json", path__0.listFiles
                ()));

        this.bigNeocortex.createUniqueFolderToSaveBigNeocortex(this.pathAndFolderName);
        this.bigNeocortex.instantiateAndSaveAllUnconnectedRegions(this.regionParameterListInOrder);

        // make sure root.json and A.json are in MARK_II/BigNeocortexTest__1
        File path__1 = new File("" +
                "./src/test/java/model/experiments/vision/MARK_II/BigNeocortexTest__1");
        assertTrue(this.bigNeocortex.isFileInList("root.json", path__1
                .listFiles()));
        assertTrue(this.bigNeocortex.isFileInList("A.json", path__1.listFiles()));

        deleteFolder(path__0);
        deleteFolder(path__1);
    }

    public void test_createUniqueFolderToSaveBigNeocortex() {
        File path = new File("" +
                "./src/test/java/model/experiments/vision/MARK_II/");
        // currently there is no folder by that name in the folder MARK_II
        assertFalse(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex", path.listFiles()));

        String pathAndFolderName = "" +
                "./src/test/java/model/experiments/vision/MARK_II" +
                "/test_createUniqueFolderToSaveBigNeocortex";
        this.bigNeocortex.createUniqueFolderToSaveBigNeocortex(pathAndFolderName);
        assertTrue(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex", path.listFiles()));

        assertFalse(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__0", path.listFiles()));
        this.bigNeocortex.createUniqueFolderToSaveBigNeocortex(pathAndFolderName);

        assertTrue(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__0", path.listFiles()));

        assertFalse(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__1", path.listFiles()));
        this.bigNeocortex.createUniqueFolderToSaveBigNeocortex(pathAndFolderName);
        assertTrue(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__1", path.listFiles()));

        assertFalse(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__2", path.listFiles()));
        this.bigNeocortex.createUniqueFolderToSaveBigNeocortex(pathAndFolderName);
        assertTrue(this.bigNeocortex.isFolderInList
                ("test_createUniqueFolderToSaveBigNeocortex__2", path.listFiles()));

        File folder = new File(pathAndFolderName);
        deleteFolder(folder);
        File folder__0 = new File(pathAndFolderName + "__0");
        deleteFolder(folder__0);
        File folder__1 = new File(pathAndFolderName + "__1");
        deleteFolder(folder__1);
        File folder__2 = new File(pathAndFolderName + "__2");
        deleteFolder(folder__2);

        // delete folder created by constructor
        File firstPath = new File(this.pathAndFolderName);
        deleteFolder(firstPath);
    }

    void deleteFolder(File file){
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteFolder(f);
            }
        }
        file.delete();
    }

    public void test_isFolderInList() {
        File path = new File("" +
                "./src/test/java/model/experiments/vision/");

        assertFalse(this.bigNeocortex.isFolderInList("fakeFolder", path
                .listFiles()));
        assertTrue(this.bigNeocortex.isFolderInList
                ("MARK_II", path.listFiles()));

        // delete folder created by constructor
        File firstPath = new File(this.pathAndFolderName);
        deleteFolder(firstPath);
    }

    public void test_getRegion() throws IOException {
        Region A = this.bigNeocortex.getRegion("A");
        assertEquals("A", A.getBiologicalName());

        assertNull(this.bigNeocortex.getRegion("B"));

        // delete folder created by constructor
        File firstPath = new File(this.pathAndFolderName);
        deleteFolder(firstPath);
    }

    public void test_addToCurrentRegion() throws IOException {
        Region A_BeforeAddingB = this.bigNeocortex.getRegion("A");
        Rectangle connectionLocation = new Rectangle(new Point(0, 0), new Point(9, 9));
        Region B = new Region("B", 10, 10, 4, 20, 3);
        this.bigNeocortex.addToCurrentRegion(connectionLocation, B, 0, 0);

        Region A_AfterAddingB = this.bigNeocortex.getRegion("A");

        assertNotSame(A_BeforeAddingB, A_AfterAddingB);

        // delete folder created by constructor
        File firstPath = new File(this.pathAndFolderName);
        deleteFolder(firstPath);
    }
}
