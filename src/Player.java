public abstract class Player {

    private static short nbrPlayers = 0;
    protected int color;

    public Player()
    {
        if(nbrPlayers == 2)
        {
            System.out.println("Warning! More than 2 players have been created.");
        }
        color = (nbrPlayers % 2 == 0) ? Board.BLACK : Board.WHITE;
        nbrPlayers++;
    }

    public int getColor() {return color;}

    public String getColorName()
    {
        return (color == Board.WHITE) ? "white" : "black";
    }

    public abstract void makeMove(Board b) throws InterruptedException;
}
