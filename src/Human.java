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
        String [] text = data.split(",");
        int row = Integer.valueOf(text[1].trim());
        return row;
    }

    private static int parseCol(String data)
    {
        String [] text = data.split(",");
        int col = Integer.valueOf(text[0].trim());
        return col;
    }

    @Override
    public void makeMove(Board b) {
        int row = 0, col = 0;
        do{
            System.out.println("Your move, human. (input on the format \"3, 4\")");
            String input = scanner.nextLine();
            row = parseRow(input);
            col = parseCol(input);

        }while(b.isLegal(row, col));

    }
}
