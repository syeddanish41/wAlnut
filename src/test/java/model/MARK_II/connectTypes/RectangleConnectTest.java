package model.MARK_II.connectTypes;

import junit.framework.TestCase;

import java.awt.*;

/**
 * @author Nathan Waggoner(nwagg14@vt.edu)
 * @author Quinn Liu(quinnliu@vt.edu)
 * @version 12/26/2014
 */
public class RectangleConnectTest extends TestCase {
    private RectangleConnect rectangleConnect;

    public void setUp() {
        this.rectangleConnect = new RectangleConnect();
    }

    public void test_updateReceptiveFieldDimensionLength(){

        // base case
        Point a = this.rectangleConnect.updateReceptiveFieldDimensionLength(1, 1, 0);
        assertEquals(new Point(0,0), a);

        // simple evenly divisible
        Point b = this.rectangleConnect.updateReceptiveFieldDimensionLength(2, 4, 0);
        assertEquals(new Point(0,1), b);
        Point c = this.rectangleConnect.updateReceptiveFieldDimensionLength(2, 4, 1);
        assertEquals(new Point(2,3), c);

        // larger evenly divisible
        Point d = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 6, 0);
        assertEquals(new Point(0,1), d);
        Point e = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 6, 1);
        assertEquals(new Point(2,3), e);
        Point f = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 6, 2);
        assertEquals(new Point(4,5), f);

        // unevenly divisible
        Point g = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 5, 0);
        assertEquals(new Point(0,1), g);
        Point h = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 5, 1);
        assertEquals(new Point(2,3), h);
        Point i = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 5, 2);
        assertEquals(new Point(4,4), i);

        // larger evenly divisible
        Point j = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 8, 0);
        assertEquals(new Point(0,2), j);
        Point k = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 8, 1);
        assertEquals(new Point(3,5), k);
        Point l = this.rectangleConnect.updateReceptiveFieldDimensionLength(3, 8, 2);
        assertEquals(new Point(6,7), l);
    }

    public void test_updateReceptiveFieldDimensionLengthWithComplexInput() {
        Point p;

        for(int i = 0; i < 5; i++){
            p = this.rectangleConnect.updateReceptiveFieldDimensionLength(5, 104, i);
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
}
