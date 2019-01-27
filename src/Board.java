public class Board {


    private final static int BOARD__SIZE = 8;
    private final static int WHITE = 1;
    private final static int BLACK = -1;
    private final static int EMPTY = 0;

    public static int getBoardSize()
    {
        return BOARD__SIZE;
    }

    private short [][] board;


    private void initBoard()
    {
        for(int i = 0; i < BOARD__SIZE; i++)
        {
            for(int j = 0; j  < BOARD__SIZE; j++)
            {
                board[i][j] = EMPTY;
            }
        }
    }

    public Board(){
        this.board = new short[BOARD__SIZE][BOARD__SIZE];
        initBoard();
    }

    public boolean gameOver() {
        return false;
    }
}
