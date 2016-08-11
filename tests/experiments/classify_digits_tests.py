from nose.tools import *
from zipfile import ZipFile
from PIL import Image
from model.retina import Retina
from model.layer import Layer
from model.network import Network
from model.common_cortical_algorithm_v1 import CommonCorticalAlgorithmV1


def test_classify_digits():
    retina = Retina(32)
    layer_level1 = Layer(3)
    layer_level2 = Layer(2)
    layer_level3 = Layer(1)
    # TODO: connect all 3 layers bottom up

    layers = { layer_level1, layer_level2, layer_level3 }
    network = Network(layers)
    cca_v1 = CommonCorticalAlgorithmV1(network)

    # train network on digit dataset to form memory and temporal groups
    with ZipFile('model/datasets/digit_0.zip') as archive:
        for entry in archive.infolist():
            with archive.open(entry) as file:
                binary_image = Image.open(file)
                assert_equal(binary_image.size, (32, 32))


                retina.see_binary_image(binary_image)
                break
                # run 1 time step for all levels in hierarchy?
                # TODO: cca_v1.run_one_time_step()

    # now we have trained the network using cca_v1 on dataset
    # TODO:
    # 1) assert elements in memory for nodes are correct at each level?
    # 2) assert temporal groups in nodes are correct at each level?






