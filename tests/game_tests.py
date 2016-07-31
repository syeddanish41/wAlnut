import nose.tools
from model.game import Room


def test_room():
    gold = Room("GoldRoom",
                """This room has gold in it you can grab. There's a
                door to the north.""")
    nose.tools.assert_equal(gold.name, "GoldRoom")
    nose.tools.assert_equal(gold.paths, {})


def test_room_paths():
    center = Room("Center", "Test room in the center.")
    north = Room("North", "Test room in the north.")
    south = Room("South", "Test room in the south.")

    center.add_paths({'north': north, 'south': south})
    nose.tools.assert_equal(center.go('north'), north)
    nose.tools.assert_equal(center.go('south'), south)


def test_map():
    start = Room("Start", "You can go west and down a hole.")
    west = Room("Trees", "There are trees here, you can go east.")
    down = Room("Dungeon", "It's dark down here, you can go up.")

    start.add_paths({'west': west, 'down': down})
    west.add_paths({'east': start})
    down.add_paths({'up': start})

    nose.tools.assert_equal(start.go('west'), west)
    nose.tools.assert_equal(start.go('west').go('east'), start)
    nose.tools.assert_equal(start.go('down').go('up'), start)