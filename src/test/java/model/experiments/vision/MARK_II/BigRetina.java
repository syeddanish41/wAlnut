package model.experiments.vision.MARK_II;

import com.google.gson.Gson;
import model.MARK_II.Region;
import model.MARK_II.VisionCell;
import model.Retina;
import model.util.FileInputOutput;
import model.util.Rectangle;

import java.io.File;
import java.io.IOException;

/**
 * NOTE: Refer to Retina.java for summary of how a Retina is represented.
 * <p/>
 * PROBLEM: When a user wants to create a NervousSystem object that is too large
 * for the Java Heap.
 * <p/>
 * SOLUTION: This class provides the same public API as Retina.java but will
 * be loaded into the heap only when needed.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @date 6/11/2015.
 */
public class BigRetina {
    private String pathAndRetinaFileName; // where BigRetina is saved as JSON file
    private Gson gson;


    public BigRetina(int numberOfVisionCellsAlongYAxis, int numberOfVisionCellsAlongXAxis,
                     String pathAndRetinaFileName) throws IOException {
        Retina retina = new Retina(numberOfVisionCellsAlongYAxis, numberOfVisionCellsAlongXAxis);

        this.pathAndRetinaFileName = pathAndRetinaFileName;
        this.gson = new Gson();
        String retinaAsJSON = this.gson.toJson(retina);
        FileInputOutput.saveObjectToTextFile(retinaAsJSON, this
                .pathAndRetinaFileName);
    }

    // TODO: other public API with saving to file
}
