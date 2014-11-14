package model.MARK_II.connectTypes;

import junit.framework.TestCase;
import java.awt.Point;

/**
 * Created by nwagg14@vt.edu on 10/16/14.
 */

public class NewRegionToRegionRectangleConnectTest extends TestCase{

    private NewRegionToRegionRectangleConnect connectType;
    public void setUp(){
        this.connectType = new NewRegionToRegionRectangleConnect();

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
}
