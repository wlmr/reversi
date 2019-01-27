import java.util.Scanner;

public class Human extends Player {


    private Scanner scanner;

    public Human()
    {
        super();
        this.scanner = new Scanner(System.in);
    }

    private static int parseRow(String data)
    {
        int row = (int) data.charAt(1) - (int) '0';
        return row;
    }

    private static int parseCol(String data)
    {
        int init = (int) 'a';
        int charInt = (int) data.charAt(0);
        return charInt - init;
    }

    private String getColorName()
    {
        return (color == Board.WHITE) ? "white" : "black";
    }

    @Override
    public void makeMove(Board b) {
        int row, col;
        boolean legalMove;
        do{

            System.out.println("Your move, " + getColorName() + " human. (input on the format \"a4\")");
            String input = scanner.nextLine();
            row = parseRow(input);
            col = parseCol(input);
            legalMove = b.isLegal(row, col, this.color);
            if(!legalMove){
                System.out.println("Illegal move.");
            }
        }while(!legalMove);
        b.setBrick(row, col, this.color);

    }
}
