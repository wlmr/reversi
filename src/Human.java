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
        try{
            int row = (int) data.charAt(1) - (int) '1';
            return row;
        } catch (StringIndexOutOfBoundsException s){
            return -1;
        }

    }

    private static int parseCol(String data)
    {
        int init = (int) 'a';
        try {
            int charInt = (int) data.charAt(0);
            return charInt - init;
        }catch (StringIndexOutOfBoundsException s)
        {
            return -1;
        }
    }



    @Override
    public void makeMove(Board b) {
        int row, col;
        boolean legalMove;
        System.out.println("Your move, " + getColorName() + " human. (input on the format \"a4\")");

        String input;
        do{
            input = scanner.nextLine();
            row = parseRow(input);
            col = parseCol(input);
            legalMove = b.isLegal(row, col, this.color);
            if(!legalMove){
                System.out.println("Illegal move.");
            }
        }while(!legalMove);
        b.setBrick(row, col, this.color);
        System.out.println(getColorName() + " brick added to " + input);

    }
}
