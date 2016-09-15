from nose.tools import *
from model.layer import Layer

def test_constructor():
    layer = Layer(1, 'layer_1')
    assert_equal(layer.length, 1)
    assert_equal(layer.nodes[0][0].active_temporal_group_index, -1)