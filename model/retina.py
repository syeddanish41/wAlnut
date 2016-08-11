import numpy as np


class Retina(object):
    """binary 2D boolean array

    :param length: number of binary cells along each side of the square retina
    """
    def __init__(self, length):
        self.vision_cells = np.zeros((length, length), dtype=bool)

    def see_binary_image(self, image):
        """stores the seen part of the world

        :param image: a binary square section of the world
        :return:
        """
        image_width = image.size[0]
        image_height = image.size[1]
        pixels = image.load()
        for r in range(image_width):
            for c in range(image_height):
                if pixels[r,c] == 1:
                    self.vision_cells[r][c] = True
                else:
                    self.vision_cells[r][c] = False