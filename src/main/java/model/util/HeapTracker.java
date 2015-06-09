package model.util;

import java.io.IOException;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @date 6/9/2015.
 */
public class HeapTracker {
    private String allHeapData;
    private long START_TIME;

    public HeapTracker() {
        this.allHeapData = new String();
        this.START_TIME = System.currentTimeMillis();

        long heapMaxSizeInMB = Runtime.getRuntime().maxMemory() / 1000000;
        System.out.println("heapMaxSize = " + heapMaxSizeInMB + " MB");
    }

    public void updateHeapData() {
        long heapFreeSizeInBytes = Runtime.getRuntime().freeMemory();
        long heapSizeInBytes = Runtime.getRuntime().totalMemory();
        long usedHeapSizeInBytes = heapSizeInBytes - heapFreeSizeInBytes;

        long usedHeapSizeInMB = (heapSizeInBytes - heapFreeSizeInBytes) / 1000000;
        System.out.println("usedHeapSize = " + usedHeapSizeInMB + " MB");

        double currentRunTimeInMilliseconds = System.currentTimeMillis() - this.START_TIME;
        double currentRunTimeInSeconds = currentRunTimeInMilliseconds / 1000;
        System.out.println("currentRunTimeInSeconds = " + currentRunTimeInSeconds);

        String addToFile = Double.toString(currentRunTimeInSeconds) + " " + usedHeapSizeInBytes + "\n";
        allHeapData += addToFile;
    }

    public String getAllHeapData() {
        return this.allHeapData;
    }

    public void printAllHeapDataToFile(String pathAndFileName) throws IOException {
        FileInputOutput.saveObjectToTextFile(this.allHeapData, pathAndFileName);
    }
}
