class Network(object):
    """A set of layers arranged in a hierarchy from largest layer at the
    bottom and the smallest layer at the top.

    Example network is model/images/explanatory/network_structure.png.

    Attributes:
        layers: 2d array of nodes
    """
    def __init__(self, layers):
        self.layers = layers
