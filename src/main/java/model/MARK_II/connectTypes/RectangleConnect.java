package model.MARK_II.connectTypes;

import java.awt.*;

/**
 * @author Nathan Waggoner(nwagg14@vt.edu)
 * @author Quinn Liu(quinnliu@vt.edu)
 * @version 12/26/2014
 */
public class RectangleConnect {

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
        Point withoutOverlap = updateReceptiveFieldDimensionLength(topLength, botLength, topIndex);

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
