from nose.tools import *
from zipfile import ZipFile
from PIL import Image
from model.retina import Retina
from model.layer import Layer
from model.network import Network
from model.common_cortical_algorithm_v1 import CommonCorticalAlgorithmV1
from model.connect_types import ConnectTypes


def test_classify_digits():
    retina = Retina(8)
    layer_level1 = Layer(2, 'layer_1')
    layer_level2 = Layer(4, 'layer_2')
    # layer_level3 = Layer(1, 'layer_3')
    # layers = [layer_level1, layer_level2, layer_level3]
    test = 0
    ConnectTypes.rectangle_connect(retina.vision_cells, layer_level1, 0, 0, test, layer_level2)
    #ConnectTypes.rectangle_connect(layer_level1.nodes, layer_level2.nodes, 0, 0)
    #ConnectTypes.rectangle_connect(layer_level2.nodes, layer_level3.nodes, 0, 0)
    print('test = ' + str(test))
    print('layer_level2.name = ' + layer_level2.name)

    for r in range(len(layer_level1.nodes)):
        for c in range(len(layer_level1.nodes[0])):
            # 2) get receptive field from input layer
            print('nodes[' + str(r) + '][' + str(c) + '].receptive_field_dimensions = ' \
                  + str(layer_level1.nodes[r][c].receptive_field_dimensions))

    # network = Network(layers, retina)
    # cca_v1 = CommonCorticalAlgorithmV1(network)
    #
    # number_training_timesteps = 1
    # t = 0
    # print_to_console = False
    # # train network on digit dataset to form memory and temporal groups
    # with ZipFile('model/datasets/digit_0.zip') as archive:
    #     for entry in archive.infolist():
    #         with archive.open(entry) as file:
    #             if (t >= number_training_timesteps):
    #                 break
    #             else:
    #                 binary_image = Image.open(file)
    #                 if (print_to_console):
    #                     print 'timestep = ' + str(t)
    #                 input_layer = retina.see_binary_image(binary_image, print_to_console)
    #
    #                 # run 1 time step for all levels in hierarchy?
    #                 cca_v1.learn_one_time_step(input_layer)
    #
    #                 t += 1

                    # now we have trained the network using cca_v1 on dataset
                    # TODO:
                    # 1) assert elements in memory for nodes are correct at each level?
                    # 2) assert temporal groups in nodes are correct at each level?
