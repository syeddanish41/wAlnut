from __future__ import division
import numpy as np


class Temporal_groups(object):
    """ Set of multiple subsets of Memory with no overlapping subsets. Each subset temporal
                         group will be represented as g1, g2, ...
        Example:
        memory = [c1, c2, c3, c4, c5]
        temporal_group = [ (c1, c3, c5), (c2, c4) ] where g1 = (c1, c3, c5) and g2 = (c2, c4)

        :param clusters: set of temporal groups

        NOTE: At nodes high in the hierarchy temporal groups will begin to represent complex objects.
              After understanding model/images/explanatory/object_manifolds.png one can think of a temporal group as an
              object manifold.
    """

    def __init__(self):
        self.clusters = set([])
        # Remove cluster_map after visualization
        self.cluster_map = {}

    def expand(self, matrix, exp):
        return np.linalg.matrix_power(matrix, exp)  # np.einsum('ij,ij->ij', matrix, matrix)

    def inflate(self, matrix, inf):
        return np.power(matrix, inf)

    def form_groups(self, AdjacencyMatrix, inf=2, exp=2, itr=100):
        """ Function for forming temporal groups based on MArkov chain clustering.

            :param
                AdjacencyMatrix: Transition matrix of the corresponding node
                inf: Inflation parameter
                exp: Expansion parameter
                itr: Number of iterations required to converge

            :returns A set of temporal groups
        """
        total_nodes = AdjacencyMatrix.shape[0]
        print("No of Nodes", total_nodes)
        # Add Self Loop
        for i in range(0, total_nodes):
            AdjacencyMatrix[i][i] = 1
        for itr in range(itr):
            # Store copy of old data
            prev = AdjacencyMatrix
            # Expand
            AdjacencyMatrix = self.expand(AdjacencyMatrix, exp)
            # Inflate
            AdjacencyMatrix = self.inflate(AdjacencyMatrix, inf)
            # Normalize
            AdjacencyMatrix = AdjacencyMatrix / np.sum(AdjacencyMatrix, axis=0)
            # Check Convergence
            if(np.array_equal(AdjacencyMatrix, prev)):
                print("MCL converged in iteration ", itr + 1)
                break
            # Prune the data
            for index, val in np.ndenumerate(AdjacencyMatrix):
                if (val <= 0.0000001):
                    AdjacencyMatrix[index[0]][index[1]] = 0
        # Find Clusters
        for row in AdjacencyMatrix:
            myList = np.where(row > 0)[0]
            if myList.any():
                self.clusters.add(tuple(myList))
        print(self.clusters)
        print("Number of Clusters formed", len(self.clusters))
        for idx, clusters in enumerate(self.clusters):
            for node in clusters:
                self.cluster_map[node + 1] = idx + 1
        # For visualization [WIP]
        # Write in clu file
        fileObject = open("file_M" + str(exp) + "E" + str(inf) + ".clu", 'w')
        fileObject.write("*Partition PartitionName")
        fileObject.write("\n")
        fileObject.write("*Vertices " + str(int(total_nodes)))
        fileObject.write("\n")

        for key in self.cluster_map:
            fileObject.write(str(self.cluster_map.get(key)))
            fileObject.write("\n")
        # return self.clusters
