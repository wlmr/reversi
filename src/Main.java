
public class Main {

    public static void main(String [] args)
    {
            String[] player0 = args[0].split("=");
            String[] player1 = args[1].split("=");
            int color0 = player0[1].equals("w") ? 1 : -1;
            int color1 = color0 == -1 ? 1 : -1;
            runGame(player0[0].equals("ai") ? new AI(color0) : new Human(color0),
                    player1[0].equals("ai") ? new AI(color1) : new Human(color1));
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
