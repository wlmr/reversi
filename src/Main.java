
public class Main {



    public static void main()
    {
        runGame(new Human(), new Human());
    }

    private static void runGame(Player black, Player white) {
        Board b = new Board();
        Player [] order = {black, white};
        boolean gameRunning = true;
        do{
            for(Player p: order)
            {
                if(b.gameOver()) break;
                p.makeMove(b);
            }
        }while(gameRunning);
    }
}
