package model.experiments.vision.MARK_II;

import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;

/**
 * PROBLEM: When a user wants to create a Neocortex object that is too large for
 * the Java Heap. This is usually when you want to build a Neocortex with 0.5+ million Neurons.
 *
 * SOLUTION: This class provides a easy to use API for creating your Neocortex as separately saved JSON files.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/27/2015.
 */
public class NeocortexBuilder {

    public NeocortexBuilder(String folderNameNeocortexIsSaved, int maxSizeOfARegionInMB, String[] regionListDetails,
                            AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion) {


    }
}
