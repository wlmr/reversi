public class Board {


    private final static int BOARD__SIZE = 8;
    public final static int WHITE = 1;
    public final static int BLACK = -1;
    public final static int EMPTY = 0;

    public static int getBoardSize()
    {
        return BOARD__SIZE;
    }

    private short [][] state;


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
    }

    public short [][] getState()
    {
        return state;
    }


    public boolean isLegal(int row, int col)
    {
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++) {
                if (i * j == 0) continue;

            }

        }
        return true;
    }

    public void setBrick(int row, int col, short color)
    {
        //Performs a few checks to see if the move is legal.

    }



    public Board(){
        this.state = new short[BOARD__SIZE][BOARD__SIZE];
        initState();
    }

    public boolean gameOver() {
        return false;
    }
}
