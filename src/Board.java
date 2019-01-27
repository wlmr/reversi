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



    private boolean outsideBounds(int row, int col)
    {
        return row >= 0 && row < BOARD__SIZE &&
                col >= 0 && col < BOARD__SIZE;
    }

    public boolean isLegal(int row, int col)
    {
        if(state[row][col] != EMPTY) return false;
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++) {
                if (i * j != 0 || outsideBounds(row + i, col + j)) continue;
                if(state[i][j] != EMPTY)
                {
                    return true;
                }

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
