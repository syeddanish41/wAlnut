package model.MARK_II.connectTypes;

import model.MARK_II.*;
import java.awt.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 13, 2013
 */
public class NewRegionToRegionRectangleConnect extends
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

            Point rowReceptiveField = updateReceptiveFieldDimensionLength(topRowLength, botRowLength, rowT);
            rowBinitial = (int) rowReceptiveField.getX();
            rowBfinal = (int) rowReceptiveField.getY();

            for(int colT = 0; colT < topColLength; colT++) {
                Point colReceptiveField = updateReceptiveFieldDimensionLength(topColLength, botColLength, colT);
                colBinitial = (int) rowReceptiveField.getX();
                colBfinal = (int) rowReceptiveField.getY();
                
                //////////

                
            }
        }

    }
    
    public Point updateReceptiveFieldDimensionLength (int topLength, int botLength, int topIndex) {
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
}
