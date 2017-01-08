from __future__ import division
import numpy as np
import json


class Markov_graph(object):
    """A directional graph determing the transitional probabilities.

    Example network is walnut/model/images/explanatory/normalised_markov_graph.png.

    :param layers: 2d adjacency matrix of nodes
    """

    def __init__(self):
        self.max_unq_patches = 1
        self.num_vertices = 0
        self.graph = np.zeros((self.max_unq_patches, self.max_unq_patches))
        self.visited = np.zeros(self.max_unq_patches)
        self.initial = []
        self.weight = []
        self.final = []

    def double_matrix_size(self):
        self.max_unq_patches *= 2
        # print(self.graph)
        temp_graph = np.zeros((self.max_unq_patches, self.max_unq_patches))
        temp_graph[0:self.num_vertices + 1, 0:self.num_vertices + 1] = self.graph
        self.graph = temp_graph.copy()
        # print("altered graph\n",self.graph)

    def connect(self, prev_active_input_pattern_index, cur_active_input_pattern_index):
        # print(prev_active_input_pattern_index, cur_active_input_pattern_index, "........................................................................")
        if self.num_vertices + 1 == self.max_unq_patches:
            self.double_matrix_size()

        self.graph[(prev_active_input_pattern_index, cur_active_input_pattern_index)] += 1
        self.num_vertices += 1

    def dfs(self, cur_index, normalise=False):
        print("----------------------------------------------------------------------------------------")
        print(self.graph[cur_index])
        self.visited[cur_index] = True

        if normalise:
            total_wt = 0
            _sum = self.graph.sum(axis=1)[cur_index]
            if _sum:
                self.graph[cur_index] = np.divide(self.graph[cur_index], _sum)

        print(self.graph[cur_index])
        for index, weight in enumerate(self.graph[cur_index]):
            self.initial.append(index)
            self.final.append(index)
            self.weight.append(self.graph.item((cur_index, index)))
            if index < len(self.graph) and self.visited[index] is False:
                self.dfs(index, normalise)

    def draw_graph(self, patch_x, patch_y, normalise=False):
        print("drawing graph",self.max_unq_patches)
        print(self.graph)
        self.visited = np.zeros(self.max_unq_patches, dtype=bool)
        for index in range(len(self.graph)):
            if self.visited[index] == False:
                print("new dfs")
                self.dfs(index, normalise)
        data = {'patch_x': patch_x, 'patch_y': patch_y, 'initial': self.initial, 'final': self.final, 'weight': self.weight}
        print(self.visited)
        # print(data)
        # Writing JSON data
        with open('walnut/tests/experiments/classify_digits/model_across_time/markov' + '_' + str(patch_x) + '_' + str(patch_y) + '.json', 'w') as f:
            json.dump(data, f)
