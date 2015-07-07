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
        this.algorithmStatistics = new AlgorithmStatistics();
    }

    public void test_addingHistory() {
        for (int i = 0; i < 10; i++) {
            this.algorithmStatistics.getSP_activeSynapsesHistory().add(new
                    Integer(i));
        }

        List<Integer> expectedList = new ArrayList<Integer>();

        assertNotSame(expectedList, this.algorithmStatistics.getSP_activeSynapsesHistory());
        this.algorithmStatistics.clearAllData();
        assertEquals(expectedList, this.algorithmStatistics.getSP_activeSynapsesHistory());
    }
}
