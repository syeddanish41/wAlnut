from zipfile import ZipFile
from PIL import Image
from walnut.model.retina import Retina
from walnut.model.htmmodel.layer import Layer
from walnut.model.htmmodel.network import Network
from walnut.model.htmmodel.common_cortical_algorithm_v1 import CommonCorticalAlgorithmV1
from walnut.model.htmmodel.connect_types import ConnectTypes
from walnut.utils.file import File
import jsonpickle
import json


def test_classify_digits():
    retina = Retina(32)
    layer_level1 = Layer(8, 'layer_1')
    layer_level2 = Layer(4, 'layer_2')
    layer_level3 = Layer(1, 'layer_3')
    layers = [layer_level1, layer_level2, layer_level3]
    ConnectTypes.rectangle_connect(retina.vision_cells, layer_level1, 0, 0)
    ConnectTypes.rectangle_connect(layer_level1.nodes, layer_level2, 0, 0)
    ConnectTypes.rectangle_connect(layer_level2.nodes, layer_level3, 0, 0)

    number_training_timesteps = 10
    network = Network(layers, retina)
    cca_v1 = CommonCorticalAlgorithmV1(network, time_steps=number_training_timesteps)

    t = 0
    print_to_console = True
    # train network on digit dataset to form memory and temporal groups
    with ZipFile('walnut/datasets/video_1.zip') as archive:
        for entry in archive.infolist():
            with archive.open(entry) as file:
                binary_image = Image.open(file)
                if print_to_console:
                    print('timestep = ' + str(t))
                input_layer = retina.see_binary_image(binary_image, print_to_console)

                # run 1 time step for all levels in hierarchy?
                cca_v1.learn_one_time_step(input_layer)
                _save_model_at_current_timestep(t, network)
                t += 1
                # now we have trained the network using cca_v1 on data set
                if t >= number_training_timesteps:
                    break

    # 2) TODO: assert temporal groups in nodes are correct at each level?

    # save model across time logs into 1 zip file
    File.save_data_into_1_file('walnut/tests/experiments/classify_digits/model_across_time', 'classify_digits_experiment', 'walnut/tests/experiments/classify_digits')


def _save_model_at_current_timestep(timestep, network):
    """
    Generate a JSON file representing the state of the model at each timestep.
    """
    pickled_network = jsonpickle.encode(network)
    # print('picked_network = ' + pickled_network)

    with open('walnut/tests/experiments/classify_digits/model_across_time/t=' + str(timestep) + '.json', 'w') as outfile:
        json.dump(pickled_network, outfile)
