package model.MARK_II.vision;

import com.google.gson.Gson;
import model.util.JsonFileInputOutput;
import java.io.IOException;

/**
 * Created by Huanqing on 11/12/2014.
 */
public class JSONtest {
    public static void main(String[] args) throws IOException {
        int[] array = new int[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = i*i;
        }

        Gson gson = new Gson();

        String output = gson.toJson(array);
        JsonFileInputOutput.saveObjectToTextFile(output,
                "./experiments/model/MARK_II/vision/array.txt");

        // Open up gnuplot and use the command: plot "array.txt" title "whatDataIs" with lines
    }
}
