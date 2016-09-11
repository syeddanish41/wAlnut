class CommonCorticalAlgorithmV1(object):
    """ 1st version of common learning algorithm.

    :param network: hierarchical connected layers
    """
    def __init__(self, network):
        self.network = network

    def learn_one_time_step(self):
        # TODO:
        for i in xrange(0, 3):
            print self.network.get_layer(i).get_name() + 'i = ' + str(i)

        # 1) start at bottom layer -> top layer
        return None

    def switch_to_sensing_mode(self):
        # TODO: return category of input image it is current looking at
        return None

    def catagorize_current_image(self):
        # TODO: catogorize current input image
        return None