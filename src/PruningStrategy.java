import java.util.ArrayList;

public class PruningStrategy extends Strategy {


    public PruningStrategy(int difficulty) {
        super(difficulty);
    }

    @Override
    public Move calculateBestMove(Board b, int color) {
        ArrayList<int []> children = b.getLegalMoves(color);
        if(children.size() == 0) return null;

        Move bestMove = null;
        int bestScore = (color == Board.MAXIMIZING_PLAYER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        if(color == Board.MAXIMIZING_PLAYER)
        {
            for(int [] c: children)
            {
                Board temp = new Board(b);
                temp.setBrick(c[0],c[1], color);
                Move m = new Move(c, temp.getPointSum());
                int futureOutcome = calculateBestOutcome(temp, DEPTH - 1, Board.getOppositeColor(color), alpha, beta);
                    if(futureOutcome > bestScore)
                    {
                        bestMove = m;
                        bestScore = futureOutcome;
                    }
                }
        }
        else
        {
            for(int [] c: children)
            {
                Board temp = new Board(b);
                temp.setBrick(c[0],c[1], color);
                Move m = new Move(c, temp.getPointSum());
                int futureOutcome = calculateBestOutcome(temp, DEPTH - 1, Board.getOppositeColor(color), alpha, beta);
                if(futureOutcome < bestScore)
                {
                    bestMove = m;
                    bestScore = futureOutcome;
                }
                }

            }


        return bestMove;
    }


    private int calculateBestOutcome(Board b, int depth, int color, int alpha, int beta)
    {
        if(depth == 0) return b.getPointSum();
        ArrayList<int []> children = b.getLegalMoves(color);
        if(children.size() == 0) return b.getPointSum();
        int score;
        if(color == Board.MAXIMIZING_PLAYER)
        {
            score = Integer.MIN_VALUE;
            for(int [] child: children) {
                Board temp = new Board(b);
                temp.setBrick(child[0], child[1], color);
                score = Math.max(score, calculateBestOutcome(temp, depth - 1, Board.getOppositeColor(color), alpha, beta));
                alpha = Math.max(alpha, score);
                if(alpha >= beta)
                {
                    break;
                }
            }
        }
        else
        {
            score = Integer.MAX_VALUE;
            for(int [] child: children) {
                Board temp = new Board(b);
                temp.setBrick(child[0], child[1], color);
                score = Math.min(score, calculateBestOutcome(temp, depth - 1, Board.getOppositeColor(color), alpha, beta));
                beta = Math.min(beta, score);
                if(alpha >= beta)
                {
                    break;
                }
            }
        }
        return score;
    }

}
