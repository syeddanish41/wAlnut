package model.MARK_II;

import model.MARK_II.connectTypes.AbstractRegionToRegionConnect;
import model.util.Rectangle;

/**
 * Neocortex is a tree(undirected graph) of Regions. Each Region in the Neocortex
 * can have as many children Regions as necessary. Each Region can receive
 * input from input SensorCellLayers, a lower Region, or a higher Region.
 *
 * Input to Neocortex: activity of Cells within VisionCellLayer, AudioCellLayer,
 * etc.
 *
 * Output from Neocortex: activity of Cells/Columns within all Regions.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Michael Cogswell (cogswell@vt.edu)
 * @author Nathan Waggoner(nwagg14@vt.edu)
 * @version 12/18/2014
 */
public class Neocortex {
    private Region rootRegion;
    private Region currentRegion;
    private AbstractRegionToRegionConnect connectType;

    private int totalNumberOfRegions;

    public Neocortex(Region rootRegion, AbstractRegionToRegionConnect neocortexRegionToNeocortexRegion) {
        if (rootRegion == null) {
            throw new IllegalArgumentException(
                    "rootRegion in Neocortex constructor cannot be null");
        }
        this.rootRegion = rootRegion;
        this.currentRegion = this.rootRegion;

        if (neocortexRegionToNeocortexRegion == null) {
            throw new IllegalArgumentException(
                    "connectType in class Neocortex constructor cannot be null");
        }
        this.connectType = neocortexRegionToNeocortexRegion;
    }

    /**
     * Traverses the regions within this neocortex to find the region the desired biological name
     * and changes the currentRegion to point to this region.
     *
     * @param newCurrentRegionBiologicalName
     */
    public void changeCurrentRegionTo(String newCurrentRegionBiologicalName) {
        if (newCurrentRegionBiologicalName == null) {
            throw new IllegalArgumentException(
                    "newCurrentRegionBiologicalName in class Neocortex method changeCurrentRegionTo() cannot be null");
        }
        Region newCurrentRegion = this.getRegion(newCurrentRegionBiologicalName);
        if (newCurrentRegion == null) {
            throw new IllegalArgumentException("newCurrentRegionBiologicalName = " + newCurrentRegionBiologicalName
                    + " in class Neocortex method changeCurrentRegionTo() does not exist in the Neocortex");
        }
        this.currentRegion = newCurrentRegion;
    }

    public Region getRegion(String regionBiologicalName) {
        if (regionBiologicalName == null) {
            throw new IllegalArgumentException(
                    "newCurrentRegionBiologicalName in class Neocortex method changeCurrentRegionTo() cannot be null");
        }

        // search the neocortex for region
        return this.recursiveFind(regionBiologicalName, this.rootRegion, 0);
    }

    Region recursiveFind(String regionBiologicalName, Region current, int numberOfRegionsVisited) {
        if (numberOfRegionsVisited > this.totalNumberOfRegions) {
            return null;
        }
        else if(current.getBiologicalName().equals(regionBiologicalName)) {
            return current;
        }
        else{
            for(Region child : current.getChildRegions()){
                Region possible = recursiveFind(regionBiologicalName, child, numberOfRegionsVisited + 1);
                if(possible != null){
                    return possible;
                }
            }
        }
        return null;
    }

    public boolean addToCurrentRegion(Rectangle rectanglePartOfParentRegionToConnectTo, Region childRegion,
                                      int numberOfColumnsToOverlapAlongNumberOfRows,
                                      int numberOfColumnsToOverlapAlongNumberOfColumns) {
        if (childRegion == null) {
            throw new IllegalArgumentException(
                    "childRegion in class Neocortex method addToCurrentRegion cannot be null");
        }

        Region regionAlreadyInNeocortex = this.getRegion(childRegion.getBiologicalName());
        if (regionAlreadyInNeocortex == null) {
            // childRegion is new so we can add
        } else if (regionAlreadyInNeocortex.equals(childRegion)) {
            // the user is trying to make a cycle connection within regions in
            // the Neocortex which is allowed
        } else if (regionAlreadyInNeocortex != null) {
            throw new IllegalArgumentException(
                    "childRegion in class Neocortex method addToCurrentRegion" +
                            " already exists within the Neocortex as another region " +
                            "with the same name");
        }

        // TODO: connect specific parts of top Region to bottom Region

        this.currentRegion.addChildRegion(childRegion);
        this.totalNumberOfRegions++;
        // connect currentRegion to childRegion
        this.connectType.connect(childRegion.getColumns(), this.currentRegion.getColumns(), // .getColumns(rectanglePartOfParentRegionToConnectTo),
                numberOfColumnsToOverlapAlongNumberOfRows,
                numberOfColumnsToOverlapAlongNumberOfColumns);
        return false;
    }

    public Region getCurrentRegion() {
        return this.currentRegion;
    }
}
