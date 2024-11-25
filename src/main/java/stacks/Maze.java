package stacks;

public class Maze {
	// The size of the maze in squares:
	private static final int WIDTH = 50, HEIGHT = 50;
	// The maze itself:
	@SuppressWarnings("unused")
	private byte[][] maze;

	// Flag constants representing the different sides of a square (used by the
	// mark, unmark and isMarked methods, see below):
	@SuppressWarnings("unused")
	private static final byte NORTH = 0x01, EAST = 0x02, SOUTH = 0x04,
			WEST = 0x08, ALL = 0x0F;

	// Flag constants representing the different states of a square (used by the
	// mark, unmark and isMarked methods, see below):
	@SuppressWarnings("unused")
	private static final byte VISITED = 0x10, PATH = 0x20, EXIT = 0x40,
			START = (byte) 0x80;

	public Maze() {
		maze = new byte[WIDTH][HEIGHT];
	}

	@SuppressWarnings("unused")
	private static class Position {
		// Each Maze. Position object represents the position of the square at
		// column number x and row number y in a maze.
		private int x, y;

		private Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
