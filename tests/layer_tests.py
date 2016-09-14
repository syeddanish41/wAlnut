from nose.tools import *
from model.layer import Layer
from model.connect_types import ConnectTypes

def test_constructor():
    layer = Layer(1, 'layer_1')
    assert_equal(layer.length, 1)
    assert_equal(layer.get_node(0, 0).active_temporal_group_index, -1)