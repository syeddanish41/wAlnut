from __future__ import division
from collections import defaultdict


class Markov_graph(object):
    """A directional graph determing the transitional probabilities.

    Example network is walnut/model/images/explanatory/normalised_markov_graph.png.

    :param layers: 2d adjacency matrix of nodes
    """

    def __init__(self):
        self.graph = defaultdict(list)

    def connect(self, prev_active_input_pattern_index, cur_active_input_pattern_index):
        for index, edge in enumerate(self.graph[prev_active_input_pattern_index]):
            if cur_active_input_pattern_index == edge[0]:
                self.graph[prev_active_input_pattern_index][index][1] += 1
                return

        self.graph[prev_active_input_pattern_index].append([cur_active_input_pattern_index, 1])

    def dfs(self, cur_index, visited, normalise=False):
        visited[cur_index] = True

        if normalise:
            total_wt = 0
            for edge in self.graph[cur_index]:
                total_wt += edge[1]

            for index, edge in enumerate(self.graph[cur_index]):
                self.graph[cur_index][index][1] /= total_wt

        for edge in self.graph[cur_index]:
            if edge[0] < len(self.graph) and visited[edge[0]] is False:
                self.dfs(edge[0], visited, normalise)

    def draw_graph(self, normalise=False):
        visited = [False] * (len(self.graph))

        for index in range(len(self.graph)):
            if visited[index] is False:
                self.dfs(index, visited, normalise)
