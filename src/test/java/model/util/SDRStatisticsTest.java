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

    public void test_probabilityOfFalsePositive() {
        SDRStatistics SDR_statistics2 = new SDRStatistics(1024, 4, 2);
        assertEquals("With 50.000000 % noise there is a .006855 % of false " +
                "positive = The chance of SDR Y matching SDR X with noise " +
                "bound but not representing the same image. We want this to " +
                "be small.", SDR_statistics2.probabilityOfFalsePositive());
    }

    public void test_overlapSet() {
        // NOTE: 3rd parameter theta isn't used in overlap set calculation
        SDRStatistics SDR_statistics2 = new SDRStatistics(1024, 2, -1);
        assertEquals(new BigInteger("521731"), SDR_statistics2.overlapSet(0));

        // NOTE: the set size increases extremely rapidly as w increases
        SDRStatistics SDR_statistics3 = new SDRStatistics(1024, 5, -1);
        assertEquals(new BigInteger("9066119080578"), SDR_statistics3.overlapSet(0));
    }

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
