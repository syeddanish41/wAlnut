''' Installation script for python wAlnut package'''

import os
import sys
from setuptools import find_packages, setup

setup(
    name='wAlnut',
    version='0.0.1',
    author='Mohit Rathore',
    author_email='mrmohitrathoremr@gmail.com',
    description='Work in progress towards an artificial general intelligence',
    # Workaround for handling error of easy_install numpy
    setup_requires=['numpy'],
    install_requires=[
        'nose',
        'Pillow',
        'numpy',
        'jsonpickle',
        'coverage',
    ],
    classifiers=[
        'Development Status :: Pre-Alpha',
        'Intended Audience :: Developers',
        'License :: OSI Approved :: GNU Lesser General Public License v3 (LGPLv3)',
        'Programming Language :: Python :: 2',
        'Programming Language :: Python :: 3',
    ],
    url='https://github.com/WalnutiQ/wAlnut/',
    license='GPL-3.0',
    # Automatically find packages inside wAlnut to install
    packages=find_packages(),
)
