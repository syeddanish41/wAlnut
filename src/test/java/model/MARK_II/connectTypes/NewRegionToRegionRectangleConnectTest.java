package model.MARK_II.connectTypes;

import junit.framework.TestCase;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import model.MARK_II.*;
import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.MARK_II.connectTypes.NewRegionToRegionRectangleConnect;
import model.MARK_II.ProximalSegment;
import model.util.Point3D;

/**
 * Created by nwagg14@vt.edu on 10/16/14.
 */

public class NewRegionToRegionRectangleConnectTest extends TestCase{

    private NewRegionToRegionRectangleConnect connectType;

    private Region topRegion;
    private Region bottomRegion;
    private static final int bottomRegionColumnLength = 2;

    Set<Point3D> expectedPositionsForColumnAt00;
    Set<Point3D> expectedPositionsForColumnAt01;
    Set<Point3D> expectedPositionsForColumnAt10;
    Set<Point3D> expectedPositionsForColumnAt11;

    public void setUp(){
        this.connectType = new NewRegionToRegionRectangleConnect();

        this.topRegion = new Region("topRegion", 2, 2, 1, 50, 2);
        this.bottomRegion = new Region("bottomRegion", 4, 8, bottomRegionColumnLength, 50, 2);

        this.setUpExpectedPositionsForColumnsWithOverlap();
    }

    public void test_updateReceptiveFieldDimensionLength(){

        // base case
        Point a = connectType.updateReceptiveFieldDimensionLength(1, 1, 0);
        assertEquals(new Point(0,0), a);

        // simple evenly divisible
        Point b = connectType.updateReceptiveFieldDimensionLength(2, 4, 0);
        assertEquals(new Point(0,1), b);
        Point c = connectType.updateReceptiveFieldDimensionLength(2, 4, 1);
        assertEquals(new Point(2,3), c);

        // larger evenly divisible
        Point d = connectType.updateReceptiveFieldDimensionLength(3, 6, 0);
        assertEquals(new Point(0,1), d);
        Point e = connectType.updateReceptiveFieldDimensionLength(3, 6, 1);
        assertEquals(new Point(2,3), e);
        Point f = connectType.updateReceptiveFieldDimensionLength(3, 6, 2);
        assertEquals(new Point(4,5), f);

        // unevenly divisible
        Point g = connectType.updateReceptiveFieldDimensionLength(3, 5, 0);
        assertEquals(new Point(0,1), g);
        Point h = connectType.updateReceptiveFieldDimensionLength(3, 5, 1);
        assertEquals(new Point(2,3), h);
        Point i = connectType.updateReceptiveFieldDimensionLength(3, 5, 2);
        assertEquals(new Point(4,4), i);

        // larger evenly divisible
        Point j = connectType.updateReceptiveFieldDimensionLength(3, 8, 0);
        assertEquals(new Point(0,2), j);
        Point k = connectType.updateReceptiveFieldDimensionLength(3, 8, 1);
        assertEquals(new Point(3,5), k);
        Point l = connectType.updateReceptiveFieldDimensionLength(3, 8, 2);
        assertEquals(new Point(6,7), l);
    }

    public void test_updateReceptiveFieldDimensionLengthWithComplexInput() {
        Point p;

        for(int i = 0; i < 5; i++){
            p = connectType.updateReceptiveFieldDimensionLength(5, 104, i);
            if(i < 4){
                int correctInitial = 0 + i * 21;
                int correctFinal = 20 + i * 21;
                assertEquals(new Point(correctInitial, correctFinal), p);
            }

            else { // i == 5
                assertEquals(new Point(84, 103), p);
            }
        }
    }

    public void test_connect() {
        AbstractRegionToRegionConnect regionToRegion = new NewRegionToRegionRectangleConnect();
        regionToRegion.connect(this.bottomRegion, this.topRegion, 1, 2);
        int topRowLength = topRegion.getNumberOfRowsAlongRegionYAxis();
        int topColLength = topRegion.getNumberOfColumnsAlongRegionXAxis();

        Set<Point3D> actualConnectedNeuronPositions = new HashSet<Point3D>();
        for(int rowT = 0; rowT < topRowLength; rowT++) {
            for (int colT = 0; colT < topColLength; colT++) {
                Segment proximalSegment = topRegion.getColumn(rowT, colT).getProximalSegment();
                //System.out.println("Column at (row, column) = (" + rowT + ", " + colT + ")");
                for(Synapse<Cell> synapse : proximalSegment.getSynapses()) {
                    for (int i = 0; i < bottomRegionColumnLength; i++) {
                        Point3D neuronPosition = new Point3D(synapse.getCellXPosition(), synapse.getCellYPosition(), i);
                        actualConnectedNeuronPositions.add(neuronPosition);
                        //System.out.println("added neuronPosition (r, c, i) = ("
                        //        + synapse.getCellXPosition() + ", " + synapse.getCellYPosition() + ", " + i + ")");
                    }
                }

                if (rowT == 0 && colT == 0) {
                    assertEquals(this.expectedPositionsForColumnAt00, actualConnectedNeuronPositions);
                } else if (rowT == 0 && colT == 1) {
                    assertEquals(this.expectedPositionsForColumnAt01, actualConnectedNeuronPositions);
                } else if (rowT == 1 && colT == 0) {
                    assertEquals(this.expectedPositionsForColumnAt10, actualConnectedNeuronPositions);
                } else { // rowT == 1 && colT == 1
                    assertEquals(this.expectedPositionsForColumnAt11, actualConnectedNeuronPositions);
                }
                actualConnectedNeuronPositions.clear();
            }
        }
    }

    private void setUpExpectedPositionsForColumnsWithOverlap() {
        expectedPositionsForColumnAt00 = new HashSet<Point3D>();
        for(int r = 0; r < 2 + 1; r++) {
            for(int c = 0; c < 4 + 2; c++) {
                for (int z = 0; z < bottomRegionColumnLength; z++) {
                    expectedPositionsForColumnAt00.add(new Point3D(r, c, z));
                }
            }
        }

        expectedPositionsForColumnAt01 = new HashSet<Point3D>();
        for(int r = 0; r < 2 + 1; r++) {
            for(int c = 4 - 2; c < 8; c++) {
                for (int z = 0; z < bottomRegionColumnLength; z++) {
                    expectedPositionsForColumnAt01.add(new Point3D(r, c, z));
                }
            }
        }

        expectedPositionsForColumnAt10 = new HashSet<Point3D>();
        for(int r = 2 - 1; r < 4; r++) {
            for(int c = 0; c < 4 + 2; c++) {
                for (int z = 0; z < bottomRegionColumnLength; z++) {
                    expectedPositionsForColumnAt10.add(new Point3D(r, c, z));
                }
            }
        }

        expectedPositionsForColumnAt11 = new HashSet<Point3D>();
        for(int r = 2 - 1; r < 4; r++) {
            for(int c = 4 - 2; c < 8; c++) {
                for (int z = 0; z < bottomRegionColumnLength; z++) {
                    expectedPositionsForColumnAt11.add(new Point3D(r, c, z));
                }
            }
        }
    }
}
