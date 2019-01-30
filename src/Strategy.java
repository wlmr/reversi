public abstract class Strategy {



    protected final int DEPTH;

    public Strategy(int difficulty)
    {
        this.DEPTH = difficulty;
    }

    public abstract Move calculateBestMove(Board b, int color);
}
