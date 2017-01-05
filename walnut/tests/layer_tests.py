#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Licensed under the GNU GENERAL PUBLIC LICENSE Version 3 - https://www.gnu.org/licenses/gpl-3.0.en.html

"""
Nose tests for the `htmmodel.layer` class.
"""


from nose.tools import assert_equal
from walnut.model.htmmodel.layer import Layer


def test_constructor():
    layer = Layer(1, 'layer_1')
    assert_equal(layer.length, 1)
    assert_equal(layer.nodes[0][0].active_temporal_group_index, -1)
