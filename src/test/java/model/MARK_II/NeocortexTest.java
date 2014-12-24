package model.MARK_II;

import model.MARK_II.connectTypes.RegionToRegionRectangleConnect;
import model.util.Rectangle;

import java.awt.*;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 12/24/2014
 */
public class NeocortexTest extends junit.framework.TestCase {
    private Neocortex neocortex;
    private final int L3NPM = 4; // = layer 3 neurons per column
    private final int L4NPM = 1; // = layer 4 neurons per column
    private double PMO = 20; // = percent minimum overlap
    private int DLA = 3; // = desired local activity

    public void setUp() {

        this.neocortex = new Neocortex(new Region("root", 10, 10, L3NPM, PMO, DLA), new RegionToRegionRectangleConnect());
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(29, 59)), new Region("A", 125, 125, L3NPM, PMO, DLA), 0, 0);
//        neocortex.addToCurrentRegion(new Rectangle(new Point(30, 0), new Point(59, 59)), new Region("B", 125, 125, L3NPM, PMO, DLA), 0, 0);
//
//        neocortex.changeCurrentRegionTo("A");
//        neocortex.addToCurrentRegion(new Rectangle(new Point(0, 0), new Point(124, 124)), new Region("C", 125, 125, L4NPM, PMO, DLA), 0, 0);
    }

    public void test_addToCurrentRegion() {

    }

    public void test_recursiveFind() {
        Rectangle connectionLocation = new Rectangle(new Point(0, 0), new Point(9, 9));

        Region A = new Region("A", 10, 10, L4NPM, PMO, DLA);
        this.neocortex.addToCurrentRegion(connectionLocation, A, 0, 0);
    }
}