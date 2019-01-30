public class Move implements Comparable<Move> {
    private int pointSum;
    private int row;
    private int col;

    Move(int row, int col, int pointSum) {
        this.pointSum = pointSum;
        this.row = row;
        this.col = col;

    }

    Move(int [] coordinates, int pointSum)
    {
        this(coordinates[0], coordinates[1], pointSum);
    }


    Move(int [] coordinates, Board b)
    {
        this(coordinates[0], coordinates[1], b.getPointSum());
    }


    @Override
    public int compareTo(Move o) {
        if (this.pointSum == o.pointSum) return 0;
        else if (this.pointSum > o.pointSum) return 1;
        else return -1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPointSum()
    {
        return pointSum;
    }



}