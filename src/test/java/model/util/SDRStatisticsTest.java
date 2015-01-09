package model.util;

import junit.framework.TestCase;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 1/9/2015
 */
public class SDRStatisticsTest extends TestCase {
    private SDRStatistics SDR_statistics;

    public void setUp() {
        this.SDR_statistics = new SDRStatistics(1024, 40, 20);
    }

    // TODO: test remaining methods after finding a way to find large
    // factorials

    public void test_factorial() {
        assertEquals(120, this.SDR_statistics.factorial(5));
    }
}
