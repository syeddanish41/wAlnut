from model.node import Node


class Layer(object):
    """ 2D square array of Node objects.

    :param length: number of nodes along each side of the square layer
    """
    def __init__(self, length):
        self.length = length
        node = Node()
        self.nodes = [[node for i in range(0, length)] for j in range(0, length)]

    def get_node(self, row, column):
        return self.nodes[row][column]
