package model.experiments.vision.MARK_II;

import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;

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

    private int maxSizeOfARegionInMB;
    private String[] regionListDetails;
    private AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion;
    private String[] connectionPattern;

    public BigNeocortex(int maxSizeOfARegionInMB, String[] regionListDetails,
                        AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion,
                        String[] connectionPattern) {
        this.maxSizeOfARegionInMB = maxSizeOfARegionInMB;
        this.regionListDetails = regionListDetails;
        this.neocortexRegionToNeocortexRegion = neocortexRegionToNeocortexRegion;
        this.connectionPattern = connectionPattern;

        this.saveConnectedNeocortexInFolder("VisionExperiment_1_brain_model");

    }

    boolean saveConnectedNeocortexInFolder(String folderName) {
        // TODO:
        return false;
    }
}
