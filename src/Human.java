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

    @Override
    public void makeMove(Board b) {
        int row, col;
        do{
            System.out.println("Your move, human. (input on the format \"a4\")");
            String input = scanner.nextLine();
            row = parseRow(input);
            col = parseCol(input);

        }while(!b.isLegal(row, col));
        b.setBrick(row, col, this.color);

    }
}
