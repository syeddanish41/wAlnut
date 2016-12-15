from __future__ import division
import numpy as np


class MarkovGraph(object):
    """A directional graph determining the transitional probabilities between vertices.

    To understand Markov Graph's better read this: http://setosa.io/ev/markov-chains/

    Example image of markov graph used for vision times series data is
    walnut/model/images/explanatory/normalised_markov_graph.png
    """

    def __init__(self):
        self.current_max_vertices = 4
        self.square_matrix = np.zeros(self.current_max_vertices)
        self.number_of_vertices = 0
        self.vertex_names_hashtable = {}

    def add_vertex(self, vertex_name):
        self.number_of_vertices += 1
        if self.number_of_vertices == self.current_max_vertices:
            self.__double_matrix_size()

        # then add vertex to graph
        self.vertex_names_hashtable[vertex_name] = self.number_of_vertices

    def __double_matrix_size(self):
        # Create new matrix
        self.current_max_vertices *= 2
        new_square_matrix = np.zeros(self.current_max_vertices)

        # copy original matrix into new matrix
        new_square_matrix[0:self.number_of_vertices+1, 0:self.number_of_vertices+1] = self.square_matrix
        # Why + 1? Because Python uses half-open ranges. So the slice [0:1] is the half-open range [0, 1),
        # meaning just the index 0; the slice [0:2] is the half-open range [0, 2), meaning the indices 0 and 1
        self.square_matrix = new_square_matrix

    def record_transition_from_vertex_A_to_B(self, vertex_A_name, vertex_B_name):
        # TODO: throw illegal argument exception if vertexA or vertexB do not exist in markov graph
        vertex_A_row_index = self.vertex_names_hashtable[vertex_A_name] - 1
        vertex_B_row_index = self.vertex_names_hashtable[vertex_B_name] - 1
        position = (vertex_A_row_index, vertex_B_row_index)
        old_value = self.square_matrix.item(position)
        self.square_matrix.itemset(position, old_value + 1)

    #def normalize_graph(self):
