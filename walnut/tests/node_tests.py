#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Licensed under the GNU GENERAL PUBLIC LICENSE Version 3 - https://www.gnu.org/licenses/gpl-3.0.en.html

"""
Nose tests for the `htmmodel.node` class.
"""


from nose.tools import assert_equal
from walnut.model.htmmodel.node import Node


def test_add_unique_pattern():
    # test adding first unique pattern
    node = Node()
    pattern_1 = {(0, 1), (0, 2)}
    assert_equal(node.add_unique_pattern(pattern_1)[1], True)
    expected_memory = [pattern_1]
    assert_equal(node.memory, expected_memory)

    # test adding second unique pattern
    pattern_2 = {(0, 2), (0, 3)}
    assert_equal(node.add_unique_pattern(pattern_2)[1], True)
    expected_memory_2 = [pattern_1, pattern_2]
    assert_equal(node.memory, expected_memory_2)

    # test adding same pattern
    pattern_3 = {(0, 1), (0, 2)}
    assert_equal(node.add_unique_pattern(pattern_3)[1], False)
    assert_equal(node.memory, expected_memory_2)
