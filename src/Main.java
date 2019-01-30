
public class Main {


    private class GameRound implements Runnable{

        private Board b;
        private Player p;

        public GameRound(Board b, Player p)
        {
            this.b = b;
            this.p = p;
        }

        @Override
        public void run() {
            Graphics.draw(b);
            int movesAvailable = b.getNbrLegalMoves(p.getColor());
            if(movesAvailable > 0){
                p.makeMove(b);
            }
            else{
                System.out.println(p.getColorName() + " has no moves left!");
            }
        }
    }

    public static void main(String [] args)
    {
//            String[] player0 = args[0].split("=");
//            String[] player1 = args[1].split("=");
//            int color0 = player0[1].equals("w") ? 1 : -1;
//            int color1 = color0 == -1 ? 1 : -1;
//            runGame(player0[0].equals("ai") ? new AI(color0) : new Human(color0),
//                    player1[0].equals("ai") ? new AI(color1) : new Human(color1));
        runGame();
    }

    private static void runGame() {
        int timeLimit = getTimeLimit();
        Player black = initPlayer("black");
        Player white = initPlayer("white");
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
            System.out.println("White wins with " + whiteCount + "-" + blackCount);
        }
        else{
            System.out.println("Draw! " + blackCount + "-" + whiteCount);
        }
    }

    private static int getTimeLimit() {
        return 0;
    }

    private static Player initPlayer(String color) {
        return null;
    }
}
