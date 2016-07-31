from model.node import Node

class Layer(object):
	""" 2D square array of Node objects. 

	Attributes:
		length: number of nodes along each side of the layer
	"""
	def __init__(self, length):
		self.length = length
		self.nodes = [[Node for i in range(0, length)] for j in range(0, length)]