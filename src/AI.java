public class AI extends Player {
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
}
