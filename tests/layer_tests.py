from nose.tools import *
from model.layer import Layer


def test_constructor():
    layer = Layer(1)
    assert_equal(layer.length, 1)
    assert_equal(layer.get_node(0, 0).get_active_temporal_group_index(), -1)