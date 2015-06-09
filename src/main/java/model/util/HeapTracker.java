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
        System.out.println("usedHeapSize = " + this.getUsedHeapInBytes() / 1000000 + " MB");

        double currentRunTimeInMilliseconds = System.currentTimeMillis() - this.START_TIME;
        double currentRunTimeInSeconds = currentRunTimeInMilliseconds / 1000;
        System.out.println("currentRunTimeInSeconds = " + currentRunTimeInSeconds);

        String addToFile = Double.toString(currentRunTimeInSeconds) + " " + this.getUsedHeapInBytes() + "\n";
        allHeapData += addToFile;
    }

    public String getAllHeapData() {
        return this.allHeapData;
    }

    public void printAllHeapDataToFile(String pathAndFileName) throws IOException {
        FileInputOutput.saveObjectToTextFile(this.allHeapData, pathAndFileName);
    }

    public long getUsedHeapInBytes() {
        long heapFreeSizeInBytes = Runtime.getRuntime().freeMemory();
        long heapSizeInBytes = Runtime.getRuntime().totalMemory();
        long usedHeapSizeInBytes = heapSizeInBytes - heapFreeSizeInBytes;
        return usedHeapSizeInBytes;
    }

    public long getHeapMaxSizeInBytes() {
        return Runtime.getRuntime().maxMemory();
    }
}
