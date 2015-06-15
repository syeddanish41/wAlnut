package model.experiments.vision.MARK_II;

import com.google.gson.Gson;
import model.MARK_II.Column;
import model.MARK_II.Region;
import model.MARK_II.SensorCell;
import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.MARK_II.connectTypes.AbstractSensorCellsToRegionConnect;
import model.Retina;
import model.util.*;
import model.util.Rectangle;

import java.awt.*;
import java.io.IOException;

/**
 * PROBLEM: When a user wants to create a NervousSystem object that is too large for
 * the Java Heap. This is usually when you want to build a NervousSystem with 0.5+ million Neurons.
 *
 * SOLUTION: This class provides a easy to use API for creating your NervousSystem as separately
 * saved JSON files.
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @date 6/11/2015.
 */
public class BigNervousSystem {

    private final double MAX_HEAP_USE_PERCENTAGE;
    private BigNeocortex bigNeocortex;

    private HeapTracker heapTracker;

    public BigNervousSystem(int maxSizeOfBigNervousSystemInMB, BigNeocortex bigNeocortex, Dimension retinaDimension,
                            AbstractSensorCellsToRegionConnect opticNerve,
                            String[] connectionParameterListInOrder,
                            String pathAndRetinaFileName) throws IOException {
        double maxHeapSizeInMB = (double) this.heapTracker
                .getHeapMaxSizeInBytes() / 1000000;
        this.MAX_HEAP_USE_PERCENTAGE = (double) maxSizeOfBigNervousSystemInMB
                / maxHeapSizeInMB;
        if (this.MAX_HEAP_USE_PERCENTAGE > 1.0) {
            throw new IllegalArgumentException("maxSizeOfBigNervousSystemInMB" +
                    " is too large making MAX_HEAP_USE_PERCENTAGE > 1.0");
        }
        this.bigNeocortex = bigNeocortex;

        // create Retina
        // TODO: replace with BigRetina
        Retina retina = new Retina((int) retinaDimension.getHeight(), (int) retinaDimension.getWidth());

        // connect Retina
        AbstractSensorCellsToRegionConnect opticNerveConnectType = opticNerve;

        for (int i = 0; i < connectionParameterListInOrder.length; i = i+7) {
            SensorCell[][] sensorCells = retina.getVisionCells(new Rectangle(
                    new Point(
                            Integer.valueOf(connectionParameterListInOrder[i]),
                            Integer.valueOf(connectionParameterListInOrder[i+1])),
                    new Point(
                            Integer.valueOf(connectionParameterListInOrder[i+2]),
                            Integer.valueOf(connectionParameterListInOrder[i+3]))));

            Region region = this.bigNeocortex.getRegion(
                    connectionParameterListInOrder[i+4]);
            Column[][] regionColumns = region.getColumns();
            int numberOfColumnsToOverlapAlongXAxisOfSensorCells =
                    Integer.valueOf(connectionParameterListInOrder[i+5]);
            int numberOfColumnsToOverlapAlongYAxisOfSensorCells =
                    Integer.valueOf(connectionParameterListInOrder[i+6]);

            opticNerveConnectType.connect(sensorCells, regionColumns,
                    numberOfColumnsToOverlapAlongXAxisOfSensorCells,
                    numberOfColumnsToOverlapAlongYAxisOfSensorCells);

            // resave used regions in BigNeocortex since each Region has been changed
            this.bigNeocortex.saveRegion(region);
        }

        // TODO: save connected Retina
    }
}