class CommonCorticalAlgorithmV1(object):
    """ 1st version of common learning algorithm.

    :param network: hierarchical connected layers
    """
    def __init__(self, network):
        self.network = network

    def learn_one_time_step(self, input_layer):
        """
        :param input_layer: 2D int array where '1' is black and '0' is white
        """
        # start at the bottom layer
        for i in xrange(0, 3):
            current_layer = self.network.layers[i]
            print(current_layer.name + ' i = ' + str(i))
            if i == 0:
                # we are at the bottom most layer
                print('in bottom most layer')
                # 1) iterate through nodes in this layer
                nodes = current_layer.nodes
                # for r in xrange(len(nodes)):
                #     for c in xrange(len(nodes[0])):
                #         # 2) get receptive field from input layer
                #         print 'nodes[' + str(r) + '][' + str(c) + '].receptive_field_dimensions = ' \
                #               + str(nodes[r][c].receptive_field_dimensions)
                        # r_initial = receptive_field_dimensions[0]
                        # r_final = receptive_field_dimensions[2] + 1 # xrange(1, 3) = 1, 2
                        # c_inital = receptive_field_dimensions[1]
                        # c_final = receptive_field_dimensions[3] + 1
                        #
                        # receptive_field = set()
                        # for x in xrange(r_initial, r_final):
                        #     for y in xrange(c_inital, c_final):
                        #         if input_layer[x,y] == 1:
                        #             receptive_field.append((x, y))
                        #
                        # # 2) if you find a unique receptive field add it to the node's memory
                        # nodes[r][c].add_unique_pattern(receptive_field)

        return None

    def switch_to_sensing_mode(self):
        # TODO: return category of input image it is current looking at
        return None

    def catagorize_current_image(self):
        # TODO: catogorize current input image
        return None