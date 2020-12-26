package processor;

public class Matrix implements Operations{

    private int rows,cols;
    private double[][] matrix;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
    }
    public void setElement(int row, int col, double value) {
       matrix[row][col] = value;
    }

    public double getElement(int row, int col){
        return matrix[row][col];
    }

    public void setRow(int row, double ... values){
        for(int col = 0; col < cols; col++) {
            setElement(row,col,values[col]);
        }
    }

    public void setRow(int row , String values) {
        String[] temp = values.split(" ");
        double[] elements = new double[cols];
        for(int col = 0; col < cols; col++) {
            elements[col] = Double.parseDouble(temp[col]);
        }
        setRow(row,elements);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
       for(double[] elements : matrix){
           for(double element : elements){
               builder.append(element)
                       .append(" ");
           }
           builder.append(System.lineSeparator());
       }
       return builder.toString();
    }

    @Override
    public Matrix add(Matrix m) {
        Matrix result = new Matrix(rows, cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                double value = m.getElement(row,col) + matrix[row][col];
                result.setElement(row, col, value);
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(int a) {
        Matrix result = new Matrix(rows, cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                double value = a *  matrix[row][col];
                result.setElement(row, col, value);
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix m) throws IllegalArgumentException {
        if (cols != m.getRows()) {
            throw new IllegalArgumentException("The operation can be performed");
        }
        Matrix result = new Matrix(rows, m.getCols());
        for(int row = 0; row <rows; row++){

            for(int col = 0; col < m.getCols(); col++){
                double value = 0;
                 for(int i = 0; i < cols; i++) {
                     value += getElement(row, i) * m.getElement(i, col);
                 }
                 result.setElement(row, col, value);
            }
        }
        return result;
    }
}
