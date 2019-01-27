public class Board {


    private final static int BOARD__SIZE = 8;
    private final static int WHITE = 1;
    private final static int BLACK = -1;
    private final static int EMPTY = 0;

    public static int getBoardSize()
    {
        return BOARD__SIZE;
    }

    private short [][] state;


    private void initState()
    {
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

    public Board(){
        this.state = new short[BOARD__SIZE][BOARD__SIZE];
        initState();
    }

    public boolean gameOver() {
        return false;
    }
}
