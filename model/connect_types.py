class ConnectTypes(object):

    @staticmethod
    def rectangle_connect(input_cells, nodes, x_axis_overlap, y_axis_overlap):
        """connects an rectangle portion of an input layer to a layer of nodes

        :param input_cells: 2D int array where '1' is black and '0' is white
        :param layer: layer to connect input_cells to
        :param x_axis_overlap: number of input cells to overlap along x axis input cells
        :param y_axis_overlap: number of input cells to overlap along y axis input cells
        """
        topRowLength = len(nodes)
        topColLength = len(nodes[0])
        botRowLength = len(input_cells)
        botColLength = len(input_cells[0])

        for rowT in xrange(topRowLength):
            rowReceptiveField = ConnectTypes.__update_receptive_field_dimension_length_with_overlap(topRowLength, botRowLength, rowT, y_axis_overlap)
            rowBinitial = rowReceptiveField[0]
            rowBfinal = rowReceptiveField[1]

            for colT in xrange(topColLength):
                colReceptiveField = ConnectTypes.__update_receptive_field_dimension_length_with_overlap(topColLength, botColLength, colT, x_axis_overlap);
                colBinitial = colReceptiveField[0]
                colBfinal = colReceptiveField[1]

                # actually add connection dimensions from bottom input cells receptive field to top layer node
                current_node = nodes[rowT][colT]
                current_node.set_receptive_field_dimensions((rowBinitial, colBinitial, rowBfinal, colBfinal))

    @staticmethod
    def __update_receptive_field_dimension_length(top_length, bot_length, top_index):
        if (top_length > bot_length):
            raise ValueError('top_length must be <= bot_length')

        if (top_index < bot_length % top_length):
            b_initial = top_index * (bot_length/top_length) + top_index
            b_final = (top_index + 1) * (bot_length/top_length) + (top_index + 1) -1 # -1 of next row b_initial
        else:
            b_initial = top_index * (bot_length/top_length) + bot_length % top_length
            b_final = (top_index + 1) * (bot_length/top_length) + bot_length % top_length - 1

        return (b_initial, b_final)


    @staticmethod
    def __update_receptive_field_dimension_length_with_overlap(top_length, bot_length, top_index, overlap ):
        without_overlap = ConnectTypes.__update_receptive_field_dimension_length(top_length, bot_length, top_index)

        new_B_initial = without_overlap[0] - overlap
        if (new_B_initial < 0):
            new_B_initial = 0

        new_B_final = without_overlap[1] + overlap
        if (new_B_final > bot_length - 1):
            new_B_final = bot_length - 1

        return (new_B_initial, new_B_final)