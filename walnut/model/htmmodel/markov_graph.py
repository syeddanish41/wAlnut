from collections import defaultdict

class Markov_graph(object):
    """A directional graph determing the transitional probabilities.

    Example network is walnut/model/images/explanatory/normalised_markov_graph.png.

    :param layers: 2d array of nodes
    """

    def __init__(self):
        self.graph = defaultdict(list)

    def connect(self, u, v):
        self.graph[u].append(v)

    def dfs(self, v, visited):
        visited[v] = True
        print v,

        for i in self.graph[v]:
            if visited[i] is False:
                self.dfs(i, visited)

    def draw_graph(self, v):

        visited = [False] * (len(self.graph))

        self.dfs(v, visited)
