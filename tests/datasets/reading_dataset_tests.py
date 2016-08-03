from nose.tools import *
from zipfile import ZipFile
from PIL import Image


def test_reading_zip():
    # NOTE: since tests are run at the root directory the path to the zip file MUST
    #       be relative starting at root instead of relative to this files locations

    with ZipFile('model/datasets/digit_0.zip') as archive:
        for entry in archive.infolist():
            with archive.open(entry) as file:
                binary_image = Image.open(file)
                print(binary_image.size, binary_image.mode, len(binary_image.getdata()))