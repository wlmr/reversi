public class AI extends Player {

    public AI(int color){
        super();
        this.color = color;
    }

    @Override
    public void makeMove(Board b) {
        for(int i = 0; i < Board.getBoardSize(); i++)
        {
            for(int j = 0; j < Board.getBoardSize(); j++)
            {
                if(b.isLegal(i, j, this.color))
                {
                    b.setBrick(i, j, color);
                    return;
                }
            }
        }
    }

    private class Move implements Comparable<Move> {
        private Board b;
       private int pointSum;
       private int row;
       private int col;

        Move(Board b, int row, int col){
            this.b   = b;
            this.row = row;
            this.col = col;

        }

        @Override
        public int compareTo(Move o) {
            if(this.pointSum == o.pointSum) return 0;
            else if(this.pointSum > o.pointSum) return 1;
            else return -1;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }


    private Move calculateBestMove(Board b){
        return calculateBestMove(b, 8, Integer.MAX_VALUE, Integer.MIN_VALUE, this.color);
    }

    private Move calculateBestMove(Board b, int depth, int alpha, int beta, int currentColor){
        if (depth == 0) {
            return
        }
        if (this.color == currentColor){
            for(int[] m : b.getLegalMoves(this.color)) {
                Board bNext = new Board(b);
                bNext.setBrick(m[0],m[1],currentColor);
                int nextColor = currentColor == Board.WHITE ? Board.BLACK : Board.WHITE;
                alpha = Math.max(alpha, calculateBestMove(bNext, depth-1, alpha, beta, nextColor));
                if (beta <= alpha) break;
            }
            return alpha;

        }else {
            for(int[] m : b.getLegalMoves(this.color)) {
                Board bNext = new Board(b);
                bNext.setBrick(m[0],[1],currentColor);
                int nextColor = currentColor == Board.WHITE ? Board.BLACK : Board.WHITE;
                beta = Math.min(beta, calculateBestMove(bNext, depth-1, alpha, beta, nextColor));
                if (beta <= alpha) break;
            }
            return beta;
        }


    //When depth == 0 or no more moves, return somehow
    //currentColor changes if the function should maximize or minimize
    // the result from boards getPointSum
    }
    //function alphabeta(node, depth, α, β, Player)
    //if  depth = 0 or node is a terminal node
    //    return score
    //if  Player = MaxPlayer
    //    for each child of node
    //α := max(α, alphabeta(child, depth-1, α, β, not(Player) ))
    //        if β ≤ α
    //            break                             (* Beta cut-off *)
    //        return α
    //else
    //        for each child of node
    //β := min(β, alphabeta(child, depth-1, α, β, not(Player) ))
    //        if β ≤ α
    //            break                             (* Alpha cut-off *)
    //        return β
}
