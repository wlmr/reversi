import java.util.ArrayList;

public class Board {


    private final static int BOARD_SIZE = 8;
    public final static int WHITE = 1;
    public final static int BLACK = -1;
    public final static int EMPTY = 0;

    public final static int MAXIMIZING_PLAYER = WHITE;
    private int[][] state;

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public static String coordinatesToString(int row, int col)
    {
        char cl = (char) (col + (int)'a');
        return String.valueOf(cl) + String.valueOf(row + 1);
    }

    public static String coordinatesToString(int []c)
    {
        return coordinatesToString(c[0], c[1]);
    }


    public Board() {
        this.state = new int[BOARD_SIZE][BOARD_SIZE];
        initState();
    }

    public Board(final Board other) {
        this.state = new int[BOARD_SIZE][BOARD_SIZE];
        initState(other.state);
    }


    public static int getOppositeColor(int color) {
        return (color == WHITE) ? BLACK : WHITE;
    }


    private void initState() {
        //TODO! add initial state which is not completely empty!
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                state[i][j] = EMPTY;
            }
        }
        state[3][3] = WHITE;
        state[3][4] = BLACK;
        state[4][3] = BLACK;
        state[4][4] = WHITE;
    }


    private void initState(int[][] otherState) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.state[i][j] = otherState[i][j];
            }
        }
    }


    public int[][] getState() {
        return state;
    }


    boolean outsideBounds(int row, int col) {
        return row < 0 || row >= BOARD_SIZE ||
                col < 0 || col >= BOARD_SIZE;
    }


    private boolean existsLine(int row, int col, int dx, int dy, int color) {
        int opposite = getOppositeColor(color);
        int nbrOpposites = 0;
        while (!outsideBounds(row, col)) {
            int currentSlot = state[row][col];
            if (currentSlot == EMPTY) return false;
            else if (currentSlot == opposite) nbrOpposites++;
            else if (currentSlot == color) return nbrOpposites > 0;
            row = row + dy;
            col = col + dx;
        }
        return false;
    }

    /**
     * Checks all of the directions from a given position on a table and a given color.
     * The move is considered legal if, in any direction (horizontal, vertical, diagonal),
     * there is a direct line of at least one brick of the opposite color, ended with a brick
     * of the same color. you can not place a brick on top of another brick, either.
     *
     * @param row
     * @param col
     * @param color
     * @return
     */
    public boolean isLegal(int row, int col, int color) {
        if (outsideBounds(row, col) || state[row][col] != EMPTY) return false;
        for (int dx = -1; dx <= 1; dx++) //horizontal delta
        {
            for (int dy = -1; dy <= 1; dy++) { //Vertical delta
                boolean outside = outsideBounds(row + dy, col + dx);
                if ((dx == 0 && dy == 0) || outside) continue;
                if (existsLine(row + dy, col + dx, dx, dy, color)) return true;
            }
        }
        return false;
    }

    public boolean setBrick(int row, int col, int color) {
        //Performs a few checks to see if the move is legal.
        if (outsideBounds(row, col)) return false;//|| !isLegal(row, col) || state[row][col] != EMPTY) return false;
        state[row][col] = color;
        update(row, col);
        return true;
    }

    public ArrayList<int[]> getLegalMoves(int color) {
        ArrayList<int[]> legalMoves = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (isLegal(row, col, color)) legalMoves.add(new int[]{row, col});
            }
        }
        return legalMoves;
    }

    public int getNbrLegalMoves(int color) {
        return getLegalMoves(color).size();
    }

    private void invert(int row, int col) {
        state[row][col] = getOppositeColor(state[row][col]);
    }

    private void performFlips(int row, int col, int dx, int dy, int color) {
        ArrayList<int[]> foundFlips = new ArrayList<>();
        final int oppositeColor = getOppositeColor(color);
        while (!outsideBounds(row, col)) {
            int currentBrick = state[row][col];
            if (currentBrick == EMPTY) {
                return;
            } else if (currentBrick == oppositeColor) {
                foundFlips.add(new int[]{row, col});
                row = row + dy;
                col = col + dx;
            } else {
                break;
            }
        }
        if (outsideBounds(row, col)) return;
        for (int[] pair : foundFlips) {
            invert(pair[0], pair[1]);
        }
    }


    private void applyRules(int row, int col) {
        int currentColor = state[row][col];
        if (currentColor == EMPTY) return;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (outsideBounds(row + dy, col + dx)) continue;
                else if (dx == 0 && dy == 0) continue;
                ;
                performFlips(row + dy, col + dx, dx, dy, currentColor);

            }
        }
    }


    public int getPointSum() {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                count += state[i][j];
            }
        }
        return count;
    }


    private void update(int row, int col) {
        applyRules(row, col);
    }


    public int count(int color) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (state[i][j] == color) count++;
            }
        }
        return count;
    }
}
