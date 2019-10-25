public class TileDriver {
	// test the tileGrid method on a sizexsize board with a painted tile located
	// at (paintedX, paintedY)
	public static boolean testTiling(int size, int paintedX, int paintedY) {
		Grid board = new BasicBoard(size, paintedX, paintedY);
		MissingTile.tileGrid(board);
		return board.isFullyTiled();
	}

	public static void main(String[] args) {
		// change this if you want to try something else
		if (testTiling(1024, 0, 0)) {
			System.out.println("Tiling seems OK!");
		} else
			System.out.println("Tiling is broken.");
	}
}
