import java.util.ArrayList;

public class NaiveStrategy extends Strategy {

    public NaiveStrategy(int difficulty) {
        super(difficulty);
    }

    @Override
    public Move calculateBestMove(Board b, int color) {
        ArrayList<int []>children = b.getLegalMoves(color);
        if(children.size() == 0) return null;
        Move bestMove = null;
        int bestScore = (color == Board.MAXIMIZING_PLAYER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for(int [] c: children)
        {
            Board temp = new Board(b);
            temp.setBrick(c[0],c[1], color);
            Move m = new Move(c, temp.getPointSum());
            int futureOutcome = calculateBestOutcome(temp, DEPTH - 1, Board.getOppositeColor(color));
            if(color == Board.MAXIMIZING_PLAYER)
            {
                if(futureOutcome > bestScore)
                {
                    bestMove = m;
                    bestScore = futureOutcome;
                }
            }
            else
            {
                if(futureOutcome < bestScore)
                {
                    bestMove = m;
                    bestScore = futureOutcome;
                }
            }

        }
        return bestMove;
    }


    private int calculateBestOutcome(Board b, int depth, int color)
    {
        if(depth == 0) return b.getPointSum();
        ArrayList<int []> children = b.getLegalMoves(color);
        if(children.size() == 0) return b.getPointSum();
        int bestScore = (color == Board.MAXIMIZING_PLAYER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for(int [] child: children)
        {
            Board temp = new Board(b);
            temp.setBrick(child[0],child[1], color);
            int outcome = calculateBestOutcome(temp, depth - 1, Board.getOppositeColor(color));
            if(color == Board.MAXIMIZING_PLAYER)
            {
                if(outcome > bestScore)
                {
                    bestScore = outcome;
                }
            }
            else
            {
                if(outcome < bestScore)
                {
                    bestScore = outcome;
                }

            }
        }
        return bestScore;
    }

}
