class Network(object):
    """A set of layers arranged in a hierarchy from largest layer at the
    bottom and the smallest layer with a single node at the top.

    They are connected bottom up. Example network is model/images/explanatory/network_structure.png.
    """
    def __init__(self, layers):
        self.layers = layers
