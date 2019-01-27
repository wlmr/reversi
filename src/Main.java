public class Main {



    public static void main()
    {
        runGame();
    }

    private static void runGame() {
        Board b = new Board();
        while(!b.gameOver()){}
    }
}
