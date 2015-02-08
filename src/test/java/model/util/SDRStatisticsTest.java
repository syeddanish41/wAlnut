package model.util;

import junit.framework.TestCase;

import java.math.BigInteger;

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

    public void test_combination() {
        assertEquals(new BigInteger("14628688452830972398381012578234922987" +
                "60600478383709499868425589357644160"),
                this.SDR_statistics.combination(1024, 40));
    }

    public void test_factorial() {
        assertEquals(new BigInteger("93326215443944152681699238856266700490" +
                "7159682643816214685929638952175999932299156089414639761565" +
                "1828625369792082722375825118521091686400000000000000000000" +
                "0000"), this.SDR_statistics.factorial(100));
    }
}
