package chess;

import java.awt.Point;

/**
 * The queen may move as if it were a rook and a bishop. Meaning it can move horizontally, vertically, or diagonally any
 * amount of squares, as long as it does not pass through any other pieces. The queen may capture the piece it moves to.
 */
final class Queen extends Piece {

    private final boolean isWhite;

    Queen(boolean isWhite) {
        this.isWhite = isWhite;
        final int[][] pixels =
                {
                        {0, 1, 0, 0, 1, 0},
                        {0, 0, 1, 1, 0, 0},
                        {1, 0, 1, 1, 0, 1},
                        {1, 0, 1, 1, 0, 1},
                        {0, 1, 1, 1, 1, 0},
                        {1, 1, 1, 1, 1, 1}
                };
        getColor(pixels);
    }

    @Override
    boolean isWhite() {
        return isWhite;
    }

    @Override
    boolean isActionLegal(Point start, Point end) {
        final Bishop bishop = new Bishop(isWhite);
        if (bishop.isEachCoordinateDeltaSame(start, end)) {
            return bishop.isBishopActionLegal(start, end) && wouldNotPutKingIntoCheck(start, end);
        }
        final Rook rook = new Rook(isWhite);
        return rook.isRookActionLegal(start, end) && wouldNotPutKingIntoCheck(start, end);
    }
}
