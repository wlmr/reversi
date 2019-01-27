
public class Main {

    public static void main(String [] args)
    {
        runGame(new Human(), new AI());
    }

    private static void runGame(Player black, Player white) {
        Board b = new Board();
        Player [] order = {black, white};
        int totalMoves, movesAvailable;
        do{
            totalMoves = 0;
            for(Player p: order)
            {

                Graphics.draw(b);
                movesAvailable = b.getNbrLegalMoves(p.getColor());
                if(movesAvailable > 0){
                    totalMoves += movesAvailable;
                    p.makeMove(b);
                }
                else{
                    System.out.println(p.getColorName() + " has no moves left!");
                }
            }
        }while(totalMoves > 0);
        int blackCount = b.count(Board.BLACK);
        int whiteCount = b.count(Board.WHITE);
        if(blackCount > whiteCount)
        {
            System.out.println("Black wins with " + blackCount + "-" + whiteCount);
        }else if(whiteCount > blackCount)
        {
            System.out.println("White wins with " + blackCount + "-" + whiteCount);
        }
        else{
            System.out.println("Draw! " + blackCount + "-" + whiteCount);
        }
    }
}
