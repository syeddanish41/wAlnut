from nose.tools import *
import zipfile



def test_reading_zip():
    assert_equal(2 - 1, 1)

    z = zipfile.ZipFile('../../model/datasets/digit_0.zip', 'r')
    #z = zipfile.ZipFile('../digit_0.zip', 'r')
    z.printdir()
    print('!!!!!!!!!!!in test_contructor')
    z.close()