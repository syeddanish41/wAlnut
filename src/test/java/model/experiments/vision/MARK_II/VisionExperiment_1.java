package model.experiments.vision.MARK_II;

import model.MARK_II.Neocortex;
import model.MARK_II.Region;
import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;

import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/27/2015.
 */
public class VisionExperiment_1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Running VisionExperiment_1.main() ...");

        // Nervous System API creates a folder to save the model
        //   folder name = "VisionExperiment_1_brain_model"

        // regions
//        Region root = new Region("root", 60, 60, fourNeurons, PMO, DLA);
//        Region A = new Region("A", 60, 60, fourNeurons, PMO, DLA);

        // pass it an array of all Region names
        // Example List:
        // index_0 = root, 60, 60, 4, 20, 3
        // index_1 = A   , 60, 60, 4, 20, 3
        String[] regionListDetails = {"root", "60", "60", "4", "20", "3", "A", "60", "60", "4", "20", "3"};

        String folderNameNeocortexIsSaved = "VisionExperiment_1_brain_model";
        int maxSizeOfARegionInMB = 1024;
        NeocortexBuilder neocortexBuilder = new NeocortexBuilder(folderNameNeocortexIsSaved, maxSizeOfARegionInMB,
                regionListDetails, new RegionToRegionRectangleConnect());

        System.out.println("Finished VisionExperiment_1.main()");
    }
}
