package model.experiments.vision.MARK_II;

import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.Retina;

import java.awt.*;
import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 5/27/2015.
 */
public class VisionExperiment_1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Running VisionExperiment_1.main() ...");

        int maxSizeOfARegionInMB = 1024;

        // pass it an array of all Region names
        // Example List:
        // index_0 = root, 60, 60, 4, 20, 3
        // index_1 = A   , 60, 60, 4, 20, 3

        // NOTE: new region every 6 elements
        String[] regionParameterListInOrder = {"root", "60", "60", "4", "20", "3",
                                               "A", "60", "60", "4", "20", "3"};

        // NOTE: new connection pattern every 7 elements
        String[] connectionParameterListInOrder = {"0", "0", "30", "60", "A", "4", "4",
                                                   "change to region A"};

        BigNeocortex bigNeocortex = new BigNeocortex(maxSizeOfARegionInMB,
                regionParameterListInOrder, new RegionToRegionRectangleConnect(), connectionParameterListInOrder);

        Dimension retinaDimension = new Dimension(1000, 1000);
        BigNervousSystem bigNervousSystem = new BigNervousSystem(1024, bigNeocortex, retinaDimension);


        System.out.println("Finished VisionExperiment_1.main()");
    }
}
