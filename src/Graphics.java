public class Graphics {

    //private static final char BLACK = 'o';
    //private static final char WHITE = '●';
    private static final char BLACK = 'x';
    private static final char WHITE = 'o';
    private static final char BLANK = ' ';


    public static void draw(Board b){
        int[][] state = b.getState();
        System.out.println("    a   b   c   d   e   f   g   h  ");
        System.out.println("  ---------------------------------");
        for(int i = 0; i < Board.getBoardSize(); i++){
           System.out.print(i + 1 +" |");
           for(int j = 0; j < Board.getBoardSize(); j++){
               System.out.print(' ');
               System.out.print(squareToChar(state[i][j]));
               System.out.print(' ');
               System.out.print('|');
           }
           System.out.println("\n  ---------------------------------");
        }
    }

    private static char squareToChar(int s) {
        switch (s) {
            case 1:
                return WHITE;
            case 0:
                return BLANK;
            default:
                return BLACK;
        }
    }
    //test
    public static void main(String[] args){
        Board beta = new Board();
        draw(beta);
    }
}
