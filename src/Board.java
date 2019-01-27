public class Board {


    private final static int BOARD__SIZE = 8;
    public final static int WHITE = 1;
    public final static int BLACK = -1;
    public final static int EMPTY = 0;

    public static int getBoardSize()
    {
        return BOARD__SIZE;
    }

    private int [][] state;


    private void initState()
    {
        //TODO! add initial state which is not completely empty!
        for(int i = 0; i < BOARD__SIZE; i++)
        {
            for(int j = 0; j  < BOARD__SIZE; j++)
            {
                state[i][j] = EMPTY;
            }
        }
        state[3][3] = WHITE;
        state[3][4] = BLACK;
        state[4][3] = BLACK;
        state[4][4] = WHITE;
    }

    public int [][] getState()
    {
        return state;
    }



    boolean outsideBounds(int row, int col)
    {
        return row < 0 || row >= BOARD__SIZE ||
                col < 0 || col >= BOARD__SIZE;
    }


    private boolean existsLine(int row, int col, int dx, int dy, int color)
    {
        int opposite = (color == WHITE) ? BLACK: WHITE;
        int nbrOpposites = 0;
        while(!outsideBounds(row, col))
        {
            int currentSlot = state[row][col];
            if(currentSlot == EMPTY) return false;
            else if(currentSlot == opposite) nbrOpposites++;
            else if(currentSlot == color) return nbrOpposites > 0;
            row = row + dx;
            col = col + dy;
        }
        return false;
    }

    /**
     * Checks all of the directions from a given position on a table and a given color.
     * The move is considered legal if, in any direction (horizontal, vertical, diagonal),
     * there is a direct line of at least one brick of the opposite color, ended with a brick
     * of the same color. you can not place a brick on top of another brick, either.
     * @param row
     * @param col
     * @param color
     * @return
     */
    public boolean isLegal(int row, int col, int color)
    {
        if(state[row][col] != EMPTY) return false;
        for(int i = -1; i <= 1; i++) //horizontal delta
        {
            for(int j = -1; j <= 1; j++) { //Vertical delta
                boolean outside = outsideBounds(row + i, col + j);
                if ((i == 0 && j == 0) || outside) continue;
                if(existsLine(row + i, col + j, i,j, color)) return true;
            }
        }
        return false;
    }

    public boolean setBrick(int row, int col, int color)
    {
        //Performs a few checks to see if the move is legal.
        if(outsideBounds(row, col) ) return false;//|| !isLegal(row, col) || state[row][col] != EMPTY) return false;
        state[row][col] = color;
        return true;
    }



    public Board(){
        this.state = new int[BOARD__SIZE][BOARD__SIZE];
        initState();
    }

    public boolean gameOver() {
        return false;
    }
}
