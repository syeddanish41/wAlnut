package model.experiments.vision.MARK_II;

import model.MARK_II.Region;
import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.util.Rectangle;

import java.io.File;

/**
 * PROBLEM: When a user wants to create a Neocortex object that is too large for
 * the Java Heap. This is usually when you want to build a Neocortex with 0.5+ million Neurons.
 *
 * SOLUTION: This class provides a easy to use API for creating your Neocortex as separately
 * saved JSON files.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/27/2015.
 */
public class BigNeocortex {

    private final int MAX_SIZE_OF_A_REGION_IN_MB;
    private String[] regionParameterListInOrder;
    private AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion;
    private String[] connectionParameterListInOrder;

    private String currentRegionName;
    private Region currentRegion; 
    private String rootRegionName;

    public BigNeocortex(int maxSizeOfARegionInMB, String[] regionParameterListInOrder,
                        AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion,
                        String[] connectionParameterListInOrder, String pathAndFolderName) {
        this.MAX_SIZE_OF_A_REGION_IN_MB = maxSizeOfARegionInMB;
        this.regionParameterListInOrder = regionParameterListInOrder;
        this.neocortexRegionToNeocortexRegion = neocortexRegionToNeocortexRegion;
        this.connectionParameterListInOrder = connectionParameterListInOrder;

        this.currentRegionName = regionParameterListInOrder[0];
        this.rootRegionName = currentRegionName;

        //this.saveConnectedNeocortexInFolder(pathAndFolderName);
    } 

    boolean saveConnectedNeocortexInFolder(String pathAndFolderName) {
        File file = new File(pathAndFolderName);

        if (file.mkdir() == false) {
            throw new IllegalArgumentException(pathAndFolderName + " is a file that already" +
                    "exists");
        }

        // TODO:
        return false;
    }

    public void changeCurrentRegionTo(String newCurrentRegionBiologicalName) {
        // TODO: implement this method with total used heap size < MAX_SIZE_OF_A_REGION_IN_MB
    }

    public Region getRegion(String regionBiologicalName) {
        // TODO: implement this method with total used heap size < MAX_SIZE_OF_A_REGION_IN_MB
        return null;
    }

    public void addToCurrentRegion(Rectangle rectanglePartOfParentRegionToConnectTo,
                                   Region childRegion,
                                   int numberOfColumnsToOverlapAlongNumberOfRows,
                                   int numberOfColumnsToOverlapAlongNumberOfColumns) {
        // TODO: implement this method with total used heap size < MAX_SIZE_OF_A_REGION_IN_MB
    }

    public Region getCurrentRegion() {
        // TODO: implement this method with total used heap size < MAX_SIZE_OF_A_REGION_IN_MB
        return null;
    }
}
