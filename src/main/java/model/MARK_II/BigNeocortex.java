package model.MARK_II;

import com.google.gson.Gson;
import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.util.BigClassUtil;
import model.util.FileInputOutput;
import model.util.HeapTracker;
import model.util.Rectangle;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * NOTE: Refer to Neocortex.java for summary of how a Neocortex is represented.
 * <p/>
 * PROBLEM: When a user wants to create a Neocortex object that is too large for
 * the Java Heap. This is usually when you want to build a Neocortex with 0
 * .5+ million Neurons for a computer with 4GB of RAM.
 * <p/>
 * SOLUTION: This class provides a easy to use API for creating your
 * Neocortex as separately
 * saved JSON files.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @version 6/15/2015.
 */
public class BigNeocortex {

    private final double MAX_HEAP_USE_PERCENTAGE;
    private String rootRegionName;
    private Region currentRegion;
    private AbstractRegionToRegionConnect connectType;
    private String pathAndFolderName; // BigNeocortex is saved as JSON file

    private Gson gson;
    private HeapTracker heapTracker;

    /**
     * @param maxSizeOfARegionInMB
     * @param regionParameterListInOrder       Every 6 elements in list
     *                                         represent a
     *                                         list of parameters for 1
     *                                         Region. The
     *                                         parameters for the root region
     *                                         must
     *                                         be the first 6 elements in the
     *                                         list.
     * @param neocortexRegionToNeocortexRegion
     * @param connectionParameterListInOrder   Every 7 elements in list
     *                                         represent a list of parameters
     *                                         for
     *                                         1 directed connection between the
     *                                         currentRegion and the region
     *                                         provided in the list. When changing
     *                                         the current region the element
     *                                         MUST be in the format
     *                                         "change to region REGION_NAME"
     * @param pathAndFolderName
     */
    public BigNeocortex(int maxSizeOfARegionInMB, String[]
            regionParameterListInOrder,
                        AbstractRegionToRegionConnect
                                neocortexRegionToNeocortexRegion,
                        String[] connectionParameterListInOrder, String
                                pathAndFolderName) throws IOException {
        this.rootRegionName = regionParameterListInOrder[0];
        this.connectType =
                neocortexRegionToNeocortexRegion;
        this.createUniqueFolderToSaveBigNeocortex(pathAndFolderName);

        this.gson = new Gson();
        this.heapTracker = new HeapTracker(false);
        double maxHeapSizeInMB = (double) this.heapTracker
                .getHeapMaxSizeInBytes() / 1000000;
        this.MAX_HEAP_USE_PERCENTAGE = (double) maxSizeOfARegionInMB * 2 /
                maxHeapSizeInMB;
        if (this.MAX_HEAP_USE_PERCENTAGE > 1.0) {
            throw new IllegalArgumentException("maxSizeOfARegionInMB is too " +
                    "large making MAX_HEAP_USE_PERCENTAGE > 1.0");
        }

        this.instantiateAndSaveAllUnconnectedRegions
                (regionParameterListInOrder);

        this.connectAllRegions(connectionParameterListInOrder);

        this.heapTracker.printAllHeapDataToFile("" +
                "./src/test/java/model/experiments/vision/MARK_II" +
                "/heapSizeLogData_BigNeocortex.txt");
    }

    void connectAllRegions(String[] connectionParameterListInOrder) throws IOException {
        for (int i = 0; i < connectionParameterListInOrder.length; i = i + 7) {
            // NOTE: new connection pattern every 7 elements
            // with 1 extra command in the following format: "change to region REGION_NAME"
            if (connectionParameterListInOrder[i].toLowerCase().contains("change to region")) {
                // extract region name to change currentRegion to
                int numberOfCharIn_change_to_region = 17;
                String regionName = connectionParameterListInOrder[i].substring(numberOfCharIn_change_to_region);
                i++;

                this.changeCurrentRegionTo(regionName);
            } else {
                // the next 7 elements specify a connection pattern between the
                // currentRegion and the given region name

                Rectangle rectanglePartOfParentRegionToConnectTo = new Rectangle(
                        new Point(Integer.valueOf(connectionParameterListInOrder[i]),
                                  Integer.valueOf(connectionParameterListInOrder[i+1])),
                        new Point(Integer.valueOf(connectionParameterListInOrder[i+2]),
                                  Integer.valueOf(connectionParameterListInOrder[i+3])));

                Region childRegion = this.getRegion(connectionParameterListInOrder[i+4]);
                int numberOfColumnsToOverlapAlongNumberOfRows = Integer.valueOf(connectionParameterListInOrder[i+5]);
                int numberOfColumnsToOverlapAlongNumberOfColumns = Integer.valueOf(connectionParameterListInOrder[i+6]);

                this.addToCurrentRegion(rectanglePartOfParentRegionToConnectTo, childRegion,
                        numberOfColumnsToOverlapAlongNumberOfRows, numberOfColumnsToOverlapAlongNumberOfColumns);
            }
        }
    }

    /**
     * Sets root Region as currentRegion after completion.
     *
     * @param regionParameterListInOrder
     * @throws IOException
     */
    void instantiateAndSaveAllUnconnectedRegions(String[]
                                                         regionParameterListInOrder) throws IOException {
        for (int i = 0; i < regionParameterListInOrder.length; i = i + 6) {
            // NOTE: new region every 6 elements
            Region region = null;

            // convert String parameters into correct type
            String biologicalName = regionParameterListInOrder[i];
            int numberOfColumnsAlongRowsDimension = Integer.valueOf(
                    regionParameterListInOrder[i + 1]);
            int numberOfColumnsAlongColumnsDimension = Integer.valueOf(
                    regionParameterListInOrder[i + 2]);
            int cellsPerColumn = Integer.valueOf(
                    regionParameterListInOrder[i + 3]);
            double percentMinimumOverlapScore = Double.valueOf(
                    regionParameterListInOrder[i + 4]);
            int desiredLocalActivity = Integer.valueOf(
                    regionParameterListInOrder[i + 5]);

            if (biologicalName.toLowerCase().contains("layer 5")) {
                region = new Layer5Region(biologicalName,
                        numberOfColumnsAlongRowsDimension,
                        numberOfColumnsAlongColumnsDimension,
                        cellsPerColumn, percentMinimumOverlapScore,
                        desiredLocalActivity);
            } else {
                region = new Region(biologicalName,
                        numberOfColumnsAlongRowsDimension,
                        numberOfColumnsAlongColumnsDimension,
                        cellsPerColumn, percentMinimumOverlapScore,
                        desiredLocalActivity);
            }

            // 30% because we want enough room for at least 2 Regions and later
            // for each Region to grow in size for all new synapses and
            // dendrites created
            if (this.heapTracker.isUsedHeapPercentageOver(this
                    .MAX_HEAP_USE_PERCENTAGE)) {
                throw new IllegalArgumentException("your parameters for " +
                        "Region " + region.getBiologicalName() +
                        " are using too much of the heap and must be " +
                        "decreased");
            }

            // save Region as JSON file
            this.saveRegion(region);

            if (i == 0) {
                // this is the root region's parameters
                this.currentRegion = region;
            }
        }
    }

    /**
     * @param pathAndFolderName
     * @return The old path and new folder name the BigNeocortex object will
     * be saved in.
     */
    void createUniqueFolderToSaveBigNeocortex(String pathAndFolderName) {
        File file = new File(pathAndFolderName);
        File path = new File(BigClassUtil.extractPath(pathAndFolderName));

        String newFolderName = BigClassUtil.extractFolderName(pathAndFolderName);

        String pathAndNewFolderName = pathAndFolderName;
        if (file.mkdir() == false) {
            // if there is already a folder/file with the same name add
            // a number to the folder name to be created
            boolean foundUniqueName = false;
            int i = 0;
            while (foundUniqueName == false) {
                System.out.println("newFoldername = " + newFolderName);
                System.out.println("path = " + path.toString());
                if (BigClassUtil.isFolderInList(newFolderName, path.listFiles())) {
                    // we need to change newFolderName to something unique
                    CharSequence twoUnderscores = "__";
                    if (newFolderName.contains(twoUnderscores)) {
                        // this is not the first time this folder has been
                        // created so increment number after 2 underscores
                        int indexOf2Underscores = newFolderName.indexOf("__");
                        int indexOfFolderNumber = indexOf2Underscores + 2;
                        String folderNumber = newFolderName.substring
                                (indexOfFolderNumber);
                        int folderNumberPlusOne = Integer.valueOf
                                (folderNumber) + 1;

                        String newFolderNumber = String.valueOf
                                (folderNumberPlusOne);
                        newFolderName = newFolderName.replace(folderNumber,
                                newFolderNumber);
                    } else {
                        // this is the 2nd time this folder will be created
                        // with an extra number 0 at the end
                        newFolderName += "__" + String.valueOf(i);
                    }
                } else {
                    foundUniqueName = true;
                }
            }
            // now newFolderName is a unique name every time the program is run

            // create a new folder to store BigNeocortex object
            pathAndNewFolderName = path + "/" + newFolderName;
            File whereToSaveBigNeocortex = new File(pathAndNewFolderName);
            whereToSaveBigNeocortex.mkdir();
        } else {
            // file.mkdir() worked so there is no need to create a unique
            // folder name
        }
        this.pathAndFolderName = pathAndNewFolderName;
    }

    public void changeCurrentRegionTo(String newCurrentRegionBiologicalName)
            throws IOException {
        this.currentRegion = this.getRegion(newCurrentRegionBiologicalName);
    }

    public Region getRegion(String regionBiologicalName) throws IOException {
        if (regionBiologicalName == null) {
            throw new IllegalArgumentException(
                    "newCurrentRegionBiologicalName in class Neocortex method" +
                            " getRegion() cannot be null");
        }

        File path = new File(this.pathAndFolderName);
        boolean regionInNeocortex = BigClassUtil.isFileInList(regionBiologicalName + ".json"
                , path.listFiles());
        if (regionInNeocortex == false) {
            return null;
        }

        String finalPathAndFile = this.pathAndFolderName + "/" +
                regionBiologicalName + ".json";
        String regionAsJSON = FileInputOutput.openObjectInTextFile
                (finalPathAndFile);

        Region region;
        if (regionAsJSON.toLowerCase().contains("layer 5")) {
            region = this.gson.fromJson(regionAsJSON, Layer5Region.class);
        } else {
            region = this.gson.fromJson(regionAsJSON, Region.class);
        }

        if (this.heapTracker.isUsedHeapPercentageOver(this
                .MAX_HEAP_USE_PERCENTAGE)) {
            throw new IllegalStateException("the region you are trying" +
                    " to get is taking too much space in the Java heap");
        }

        return region;
    }

    public void addToCurrentRegion(Rectangle
                                           rectanglePartOfParentRegionToConnectTo,
                                   Region childRegion,
                                   int numberOfColumnsToOverlapAlongNumberOfRows,
                                   int numberOfColumnsToOverlapAlongNumberOfColumns) throws IOException {
        if (childRegion == null) {
            throw new IllegalArgumentException(
                    "childRegion in class BigNeocortex method " +
                            "addToCurrentRegion cannot be null");
        }

        Region regionAlreadyInNeocortex = this.getRegion(childRegion
                .getBiologicalName());

        // NOTE: The first if and else if statement ARE necessary and
        //       it is important to understand why nothing should be done
        if (regionAlreadyInNeocortex == null) {
            // childRegion is new so we can add given childRegion to current
            // region. Note this is not an error.
        } else if (regionAlreadyInNeocortex.equals(childRegion)) {
            // the user is trying to make a cycle connection within regions in
            // the Neocortex which is allowed
        } else if (regionAlreadyInNeocortex != null) {
            throw new IllegalArgumentException(
                    "childRegion in class BigNeocortex method addToCurrentRegion" +
                            " already exists within the BigNeocortex as another region " +
                            "with the same name");
        }

        this.currentRegion.addChildRegion(childRegion);

        this.connectType.connect(childRegion.getColumns(),
                this.currentRegion.getColumns
                        (rectanglePartOfParentRegionToConnectTo),
                numberOfColumnsToOverlapAlongNumberOfRows,
                numberOfColumnsToOverlapAlongNumberOfColumns);

        if (this.heapTracker.isUsedHeapPercentageOver(this
                .MAX_HEAP_USE_PERCENTAGE)) {
            throw new IllegalArgumentException("The current region and child region" +
                    "you are adding is taking too much space in the heap");
        }

        // We just changed currentRegion so resave it
        File pathToRegionFile = new File(this.pathAndFolderName + "/" +
                this.currentRegion.getBiologicalName() + ".json");
        pathToRegionFile.delete();
        this.saveRegion(this.currentRegion);
    }

    public Region getCurrentRegion() {
        return this.currentRegion;
    }

    void saveRegion(Region region) throws IOException {
        String regionAsJSON = this.gson.toJson(region);
        String finalPathAndFile = this.pathAndFolderName + "/" +
                region.getBiologicalName() + ".json";
        FileInputOutput.saveObjectToTextFile(regionAsJSON,
                finalPathAndFile);
    }
}
