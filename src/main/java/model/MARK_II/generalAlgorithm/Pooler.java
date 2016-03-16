package model.MARK_II.generalAlgorithm;

import model.MARK_II.region.Column;
import model.MARK_II.region.Region;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class extended by algorithm classes.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version 9/7/2015
 */
public abstract class Pooler {
    // region: 2D array of columns
    protected Region region;
    protected Set<Column> activeColumns;
    protected Set<ColumnPosition> activeColumnPositions;

    // whether or not the algorithm is learning right now
    private boolean learningState;

    protected AlgorithmStatistics algorithmStatistics;

    public Pooler(int numberOfTimesToRunAlgorithm) {
        this.activeColumns = new HashSet<>();
        this.activeColumnPositions = new HashSet<>();
        this.learningState = false;

        this.algorithmStatistics = new AlgorithmStatistics(numberOfTimesToRunAlgorithm);
    }

    abstract public void nextTimeStep();

    public Pooler() {
        this.learningState = false;
    }

    public void changeRegion(Region newRegion) {
        if (newRegion == null) {
            throw new IllegalArgumentException(
                    "newRegion in Pooler class changeRegion method cannot be null");
        }

        this.region = newRegion;
    }

    public Set<ColumnPosition> getActiveColumnPositions() {
        return this.activeColumnPositions;
    }

    public void setActiveColumnPositions(Set<ColumnPosition> activeColumnPositions) {
        this.activeColumnPositions = activeColumnPositions;
    }

    public Set<Column> getActiveColumns() {
        return this.activeColumns;
    }

    public boolean getLearningState() {
        return this.learningState;
    }

    public void setLearningState(boolean learningState) {
        this.learningState = learningState;
    }

    public Region getRegion() {
        return this.region;
    }

    public AlgorithmStatistics getAlgorithmStatistics() {
        return this.algorithmStatistics;
    }
}
