class Node(object):
    """ Each Node class has 2 main objects:
    1) Memory = Array of unique input patterns this node has seen from it's receptive field of lower nodes during
                training. These unique patterns will be refered to as c1, c2, ...

    2) Temporal Groups = Array of multiple subsets of Memory with no overlapping subsets. Each subset temporal 
                         group will be represented as g1, g2, ...
        Example: 
        memory = [c1, c2, c3, c4, c5]
        temporal_group = [ {c1, c3, c5}, {c2, c4} ] where g1 = {c1, c3, c5} and g2 = {c2, c4}

        NOTE: At nodes high in the hierarchy temporal groups will begin to represent complex objects. 
              After understanding model/images/object_manifolds.png one can think of a temporal group as an 
              object manifold. 

    Returns: 
        After training returns an active temporal group for each input pattern
    """
    def __init__(self):
        self.memory = []
        self.active_input_pattern_index = -1

        self.temporal_groups = []
        self.active_temporal_group_index = -1