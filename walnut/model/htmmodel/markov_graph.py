from __future__ import division
from collections import defaultdict
# alibaba: added here for markov graph json dump
import json


class Markov_graph(object):
    """A directional graph determing the transitional probabilities.

    Example network is walnut/model/images/explanatory/normalised_markov_graph.png.

    """
    def __init__(self):
        self.graph = defaultdict(list)
        # alibaba: added markov graph matrix having initial, final and weight
        self.initial = []
        self.final = []
        self.weight = []

    def connect(self, prev_active_input_pattern_index, cur_active_input_pattern_index):
        # print (cur_active_input_pattern_index)
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
            self.initial.append(cur_index)
            self.final.append(edge[0])
            self.weight.append(edge[1])
            if edge[0] < len(self.graph) and visited[edge[0]] is False:
                self.dfs(edge[0], visited, normalise)

    # alibaba: added two new variables in markov_graph.draw_graph
    # patch_x and patch_y locates the 4*4 patch form the present 64 patches
    def draw_graph(self, patch_x, patch_y, normalise=False):
        visited = [False] * (len(self.graph))
        for index in range(len(self.graph)):
            if visited[index] is False:
                self.dfs(index, visited, normalise)
        data = {'patch_x': patch_x, 'patch_y': patch_y, 'initial': self.initial, 'final': self.final, 'weight': self.weight}
        print(data)
        # Writing JSON data
        # alibaba: added for markov graph dump
        with open('walnut/tests/experiments/classify_digits/model_across_time/markov' + '_' + str(patch_x) + '_' + str(patch_y) + '.json', 'w') as f:
            json.dump(data, f)
