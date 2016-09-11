class CommonCorticalAlgorithmV1(object):
    """ 1st version of common learning algorithm.

    :param network: hierarchical connected layers
    """
    def __init__(self, network):
        self.network = network

    def learn_one_time_step(self):
        # start at the bottom layer
        for i in xrange(0, 3):
            print self.network.get_layer(i).get_name() + ' i = ' + str(i)
            if (i == 0):
                # we are at the bottom most layer
                retina = self.network.get_retina()


        return None

    def switch_to_sensing_mode(self):
        # TODO: return category of input image it is current looking at
        return None

    def catagorize_current_image(self):
        # TODO: catogorize current input image
        return None