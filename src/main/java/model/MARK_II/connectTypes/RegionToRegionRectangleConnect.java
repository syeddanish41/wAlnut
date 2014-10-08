package model.MARK_II.connectTypes;

import model.MARK_II.*;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 13, 2013
 */
public class RegionToRegionRectangleConnect extends
        AbstractRegionToRegionConnect {
    @Override
    public void connect(Region bottomLayer, Region topLayer,
                        int numberOfColumnsToOverlapAlongNumberOfRows,
                        int numberOfColumnsToOverlapAlongNumberOfColumns) {
        int rowBinitial, rowBfinal, colBinitial, colBfinal;

        int topRowLength = topLayer.getNumberOfRowsAlongRegionYAxis();
        int topColLength = topLayer.getNumberOfColumnsAlongRegionXAxis();
        int botRowLength = bottomLayer.getNumberOfRowsAlongRegionYAxis();
        int botColLength = bottomLayer.getNumberOfColumnsAlongRegionXAxis();

        for(int rowT = 0; rowT < topRowLength; rowT++){

            if (rowT < botRowLength % topRowLength) {
                rowBinitial = rowT * (botRowLength/topRowLength) + rowT;
                rowBfinal = (rowT + 1) * (botRowLength/topRowLength) + (rowT + 1) -1; // -1 of next rowBinitial
            } else {
                rowBinitial = rowT * (botRowLength/topRowLength) + botRowLength % topRowLength;
                rowBfinal = (rowT + 1) * (botRowLength/topRowLength) + botRowLength % topRowLength - 1;
            }

            for(int colT = 0; colT < topColLength; colT++) {
                if(colT < botColLength % topColLength) {
                    colBinitial = colT * (botColLength/topColLength) + colT;
                    colBfinal = (colT + 1) * (botColLength/topColLength) + (colT + 1) -1; // -1 of next rowBinitial
                } else {
                    colBinitial = colT * (botColLength/topColLength) + botColLength %
                            topColLength;
                    rowBfinal = (colT + 1) * (botColLength/topColLength) + botColLength %
                            topColLength - 1;
                }
            }
        }

    }
}
