from nose.tools import *
#from model.node import Node
from model.layer import Layer

def test_constructor():
	layer = Layer(1)
	assert_equal(layer.length, 1)
    #assert_equal(layer.nodes, [[]])