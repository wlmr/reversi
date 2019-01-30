public class AI extends Player {

    private Strategy s;

    public AI(int color) {
        super();
        s = new NaiveStrategy();
        this.color = color;
    }

    @Override
    public void makeMove(Board b) {
        Move m = s.calculateBestMove(b, this.color);
        if(m == null){
            System.out.println("AI has no moves!");
        }
        else{
            b.setBrick(m.getRow(), m.getCol(), this.color);
        }

    }


    //When depth == 0 or no more moves, return somehow
    //currentColor changes if the function should maximize or minimize
    // the result from boards getPointSum

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
