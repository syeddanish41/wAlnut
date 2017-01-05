#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Licensed under the GNU GENERAL PUBLIC LICENSE Version 3 - https://www.gnu.org/licenses/gpl-3.0.en.html

"""
Nose tests for the `htmmodel.markov_graph` class.
"""


from nose.tools import assert_equal
import json


# Must be run after "classify_digits_tests.py" as it generates the json files with number_training_timesteps = 10
def test_mkgraph():
    for i in range(8):
        for j in range(8):
            # Loading generated markov graph from json files
            with open('walnut/tests/experiments/classify_digits/model_across_time/markov' + '_' + str(i) + '_' + str(j) + '.json', 'r') as f:
                data = json.load(f)
            # Loading hardcoded markov graph
            with open('walnut/tests/datasets/markov_test/markov' + '_' + str(i) + '_' + str(j) + '.json', 'r') as f:
                expected = json.load(f)
            assert_equal(data, expected)
