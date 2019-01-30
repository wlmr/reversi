import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static class GameRound implements Runnable{

        private Board b;
        private Player p;

        GameRound(Board b, Player p)
        {
            this.b = b;
            this.p = p;
        }

        @Override
        public void run() {
            Graphics.draw(b);
            int movesAvailable = b.getNbrLegalMoves(p.getColor());
            if(movesAvailable > 0){
                try {
                    p.makeMove(b);
                } catch (InterruptedException e) {
                    System.out.println("Game interrupted. Are you losing badly, player " + p.getColorName() + "?");
                    System.exit(-1);

                }
            }
            else{
                System.out.println(p.getColorName() + " has no moves left!");
            }
        }
    }

    public static void main(String [] args)
    {
        runGame();
    }

    private static void runGame() {
        int timeLimit = getTimeLimit();
        Player black = initPlayer("black");
        Player white = initPlayer("white");
        Board b = new Board();
        Player [] order = {black, white};

        int totalMoves;
        do{
            for(Player p: order)
            {
                ExecutorService ex = Executors.newSingleThreadExecutor();
                Future future = ex.submit(new GameRound(b, p));
                ex.shutdown();
                try{
                    future.get(timeLimit, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    System.out.println("Time limit exceeded!");
                } catch (Exception n){
                    System.out.println("Interrupt!");
                    return;
                }

            }
            totalMoves = b.getNbrLegalMoves(Board.BLACK) + b.getNbrLegalMoves(Board.WHITE);
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
        System.out.println("Enter a time limit per round, in seconds!");
        boolean isDone = false;
        int value = -1;
        do{

            try{
                String input = scan.nextLine();
                value = Integer.valueOf(input);
                if(value < 1)
                {
                    System.out.println("Give me POSITIVE numerical input!");
                }else{
                    isDone = true;
                }
            }catch (NumberFormatException n)
            {
                System.out.println("Give me numerical input!");
            }
            catch (NoSuchElementException n)
            {
                System.exit(-1);
            }
        }while(!isDone);
        return value;
    }

    private static Player initPlayer(String color) {
        int cl = (color.toLowerCase().equals("black")) ? Board.BLACK: Board.WHITE;
        boolean isDone = false;
        System.out.println("Initialize " + color + " player:\n1: Human\n2: AI");
        int value = -1;
        do{

            try{
                String input = scan.nextLine();
                value = Integer.valueOf(input);
                if(value < 1)
                {
                    System.out.println("Give me the number 1 or 2!");
                }else{
                    isDone = true;
                }
            }catch (NumberFormatException n)
            {
                System.out.println("Give me the number 1 or 2!");
            }catch (NoSuchElementException n)
            {
                System.exit(-1);
            }
        }while (!isDone);
        if(value == 1){
            return new Human(cl);
        }
        return createAI(cl);
        //return new Human(cl);
    }

    private static AI createAI(int color)
    {
        boolean isDone = false;
        System.out.println("How hard do you want it?");
        //Some Doom-references. Why not
        System.out.println("1: I'm too young to die\n2: Hey, not too rough" +
                "\n3: Hurt me plenty\n4: Ultra-violence\n5: Nightmare!");
        int value = -1;
        do{

            try{
                String input = scan.nextLine();
                value = Integer.valueOf(input);
                if(value < 1 || value > 6)
                {
                    System.out.println("Give me the number between 1 and 5!");
                }else{
                    isDone = true;
                }
            }catch (NumberFormatException n)
            {
                System.out.println("Give me the number between 1 and 5!");
            }
            catch (NoSuchElementException n)
            {
                System.exit(-1);
            }
        }while (!isDone);
        int difficulty;
        switch (value){
            case 1:
                difficulty = AI.VERY_EASY;
                break;
            case 2:
                difficulty = AI.EASY;
                break;
            case 3:
                difficulty = AI.NORMAL;
                break;
            case 4:
                difficulty = AI.HARD;
                break;
            case 5:
            default:
                difficulty = AI.VERY_HARD;
        }
        return new AI(color, difficulty);
    }
}
