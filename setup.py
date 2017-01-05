import os
import sys
from setuptools import find_packages, setup

setup(
    name='wAlnut',
    version='0.0.1',
    author='Mohit Rathore',
    author_email='mrmohitrathoremr@gmail.com',
    description='Work in progress towards an artificial general intelligence',
    setup_requires=['numpy>=1.11.1'],
    install_requires=[
        'nose>=1.3.7',
        'Pillow>=2.9.0',
        'numpy>=1.11.1',
        'jsonpickle>=0.9.3',
        'coverage>=4.3.1',
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
    packages=find_packages(),
)
