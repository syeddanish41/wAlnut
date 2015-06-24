package model.MARK_II.generalAlgorithm;

import model.MARK_II.region.Cell;
import model.MARK_II.region.Column;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Contains the state of information attained after 1 computational
 * cycle of computeFn() for TemporalSequenceMemory.java.
 * The algorithm uses data from previous compute cycles to derive new data for
 * the current cycle through a comparison between states of those different
 * cycles. TODO: move to OO design
 *
 * @author Q Liu (quinnliu@vt.edu)
 * @version 6/23/2015
 */
public class ComputeCycle {
    private Set<Cell> activeCells, winnerCells;
    private Set<Column> successfullyPredictedColumns;

    public ComputeCycle() {
        this.activeCells = new LinkedHashSet<Cell>();
        this.winnerCells = new LinkedHashSet<Cell>();
        this.successfullyPredictedColumns = new LinkedHashSet<Column>();
    }

    public Set<Cell> getActiveCells() {
        return activeCells;
    }

    public Set<Cell> getWinnerCells() {
        return winnerCells;
    }

    public Set<Column> getSuccessfullyPredictedColumns() {
        return successfullyPredictedColumns;
    }
}
