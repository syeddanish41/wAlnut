package model.MARK_II.generalAlgorithm;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Q Liu (quinnliu@vt.edu)
 * @version 7/7/2015
 */
public class AlgorithmStatisticsTest extends TestCase {
    private AlgorithmStatistics algorithmStatistics;

    public void setUp() {
        this.algorithmStatistics = new AlgorithmStatistics(10);
    }

    public void test_addingHistory() {
        for (int i = 0; i < 10; i++) {
            this.algorithmStatistics.getSP_activeSynapsesHistory().add(new
                    Integer(i));

            if (i % 2 == 0) {
                this.algorithmStatistics.getSP_activeSynapsesHistory().add(i,
                        new Integer(i));
            }
        }

        List<Integer> expectedList = new ArrayList<Integer>(10);
        expectedList.add(new Integer(0));
        expectedList.add(new Integer(1));
        expectedList.add(new Integer(2+2));
        expectedList.add(new Integer(3));
        expectedList.add(new Integer(4+4));
        expectedList.add(new Integer(5));
        expectedList.add(new Integer(6+6));
        expectedList.add(new Integer(7));
        expectedList.add(new Integer(8+8));
        expectedList.add(new Integer(9));
    }
}
