from nose.tools import *
from zipfile import ZipFile
from PIL import Image
from model.retina import Retina
from model.layer import Layer
from model.network import Network
from model.common_cortical_algorithm_v1 import CommonCorticalAlgorithmV1
from model.connect_types import ConnectTypes


def test_classify_digits():
    retina = Retina(32)
    layer_level1 = Layer(8, 'layer_1')
    layer_level2 = Layer(4, 'layer_2')
    layer_level3 = Layer(1, 'layer_3')
    layers = [layer_level1, layer_level2, layer_level3]
    ConnectTypes.rectangle_connect(retina.vision_cells, layer_level1, 0, 0)
    ConnectTypes.rectangle_connect(layer_level1.nodes, layer_level2, 0, 0)
    ConnectTypes.rectangle_connect(layer_level2.nodes, layer_level3, 0, 0)

    network = Network(layers, retina)
    cca_v1 = CommonCorticalAlgorithmV1(network)

    number_training_timesteps = 1
    t = 0
    print_to_console = True
    # train network on digit dataset to form memory and temporal groups
    with ZipFile('model/datasets/digit_0.zip') as archive:
        for entry in archive.infolist():
            with archive.open(entry) as file:
                if t >= number_training_timesteps:
                    break
                else:
                    binary_image = Image.open(file)
                    if print_to_console:
                        print('timestep = ' + str(t))
                    input_layer = retina.see_binary_image(binary_image, print_to_console)

                    # run 1 time step for all levels in hierarchy?
                    cca_v1.learn_one_time_step(input_layer)

                    t += 1

                    # now we have trained the network using cca_v1 on data set

    # 2) TODO: assert temporal groups in nodes are correct at each level?
