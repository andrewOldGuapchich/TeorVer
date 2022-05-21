public class TableOfDistribution {
    private final int rows;
    private final int columns;
    private final double[][] table;
    private double[] valueOfX;
    private double[] valueOfY;
    public TableOfDistribution(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        table = new double[this.rows][this.columns];
    }

    public double[][] getTable() {
        return table;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[] getValueOfX() {
        return valueOfX;
    }

    public void setValueOfX(double[] valueOfX) {
        this.valueOfX = valueOfX;
    }

    public double[] getValueOfY() {
        return valueOfY;
    }

    public void setValueOfY(double[] valueOfY) {
        this.valueOfY = valueOfY;
    }
}
