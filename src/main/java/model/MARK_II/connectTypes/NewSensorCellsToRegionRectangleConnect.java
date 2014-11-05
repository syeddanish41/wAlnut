package model.MARK_II.connectTypes;

import model.MARK_II.*;
import java.awt.*;

/**
 * Created by Huanqing on 11/5/2014.
 */
public class NewSensorCellsToRegionRectangleConnect extends
            AbstractSensorCellsToRegionConnect {
    @Override
    public void connect(SensorCell[][] sensorCells, Region region,
                        int numberOfColumnsToOverlapAlongXAxisOfSensorCells,
                        int numberOfColumnsToOverlapAlongYAxisOfSensorCells) {

        int rowBinitial, rowBfinal, colBinitial, colBfinal;

        int topRowLength = region.getNumberOfRowsAlongRegionYAxis();
        int topColLength = region.getNumberOfColumnsAlongRegionXAxis();
        int botRowLength = sensorCells.length;
        int botColLength = sensorCells[0].length;

        for(int rowT = 0; rowT < topRowLength; rowT++){
            Point rowReceptiveField = updateReceptiveFieldDimensionLengthWithOverlap(topRowLength, botRowLength, rowT, numberOfColumnsToOverlapAlongYAxisOfSensorCells);
            rowBinitial = (int) rowReceptiveField.getX();
            rowBfinal = (int) rowReceptiveField.getY();

            for(int colT = 0; colT < topColLength; colT++) {
                Point colReceptiveField = updateReceptiveFieldDimensionLengthWithOverlap(topColLength, botColLength, colT, numberOfColumnsToOverlapAlongXAxisOfSensorCells);
                colBinitial = (int) colReceptiveField.getX();
                colBfinal = (int) colReceptiveField.getY();

                // actually add synapses from bottom layer receptive field to top layer column
                Column topColumn = region.getColumn(rowT, colT);
                for (int rowB = rowBinitial; rowB <= rowBfinal; rowB++){
                    for (int colB = colBinitial; colB <= colBfinal; colB++) {
                        //for(Neuron neuron : bottomLayer.getColumn(rowB, colB).getNeurons()){
                            topColumn.getProximalSegment().addSynapse(new Synapse<Cell>(sensorCells[rowB][colB], rowB, colB));
                        //}
                    }
                }
            }
        }
    }

    Point updateReceptiveFieldDimensionLength (int topLength, int botLength, int topIndex) {
        if (topLength > botLength) {
            throw new IllegalStateException("In class NewRegionToRegionRectangleConnect" +
                    " method updateReceptiveFieldDimensionLength() topLength must be <= botLength");
        }

        int Binitial;
        int Bfinal;
        if (topIndex < botLength % topLength) {
            Binitial = topIndex * (botLength/topLength) + topIndex;
            Bfinal = (topIndex + 1) * (botLength/topLength) + (topIndex + 1) -1; // -1 of next rowBinitial
        } else {
            Binitial = topIndex * (botLength/topLength) + botLength % topLength;
            Bfinal = (topIndex + 1) * (botLength/topLength) + botLength % topLength - 1;
        }

        return new Point(Binitial, Bfinal);
    }

    Point updateReceptiveFieldDimensionLengthWithOverlap(int topLength, int botLength, int topIndex, int overlap ) {
        Point withoutOverlap = updateReceptiveFieldDimensionLength (topLength, botLength, topIndex);

        int newBinitial = (int) withoutOverlap.getX() - overlap;
        if (newBinitial < 0) {
            newBinitial = 0;
        }

        int newBfinal = (int) withoutOverlap.getY() + overlap;
        if (newBfinal > botLength - 1) {
            newBfinal = botLength - 1;
        }

        return new Point(newBinitial, newBfinal);
    }
}