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

    public Matrix(double[][] matrix){
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
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

    public double[] getColumn(int col){
        double[] elements = new double[rows];
        for(int i = 0; i < rows; i++){
            elements[i] = getElement(i, col);
        }
        return elements;
    }

    public double[] getRow(int row){
        double[] elements = new double[cols];
        for(int col = 0; col <cols; col++){
            elements[col] = getElement(row, col);
        }
        return elements;
    }


    public Matrix clone(){
        return new Matrix(matrix);
    }

    public void setColumn(int col, double[] values){
        for(int row = 0; row < rows; row++){
            matrix[row][col] = values[row];
        }
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
    public Matrix multiply(double a) {
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

    @Override
    public Matrix transpose() {
        Matrix t = new Matrix(cols,rows);
        for (int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){

                t.setElement(col, row, matrix[row][col]);
            }
        }
        return t;
    }

    @Override
    public Matrix sideTranspose() {
        Matrix t = new Matrix(cols, rows);
        for (int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                t.setElement(col, row, matrix[rows - 1 - row][cols - 1 - col]);
            }
        }
        return t;
    }

    @Override
    public Matrix horizontalTranspose() {
        Matrix t = this.clone();
        int middle = rows/ 2;
        double[] line;
        for(int row = 0; row < middle; row++){
            line = t.getRow(row);
            t.setRow(row, t.getRow(rows - 1 - row));
            t.setRow(rows - 1 - row , line);
        }
        return t;
    }

    @Override
    public Matrix verticalTranspose() {
        Matrix t = clone();
        double[]  line;
        int middle = cols / 2;
        for(int col = 0; col < middle; col++) {
            line = getColumn(col);
            t.setColumn(col ,t.getColumn(cols - 1 - col));
            t.setColumn(cols - 1 - col, line);
        }
        return t;
    }

    public Matrix commatrice(int row, int col) {
        Matrix result = new Matrix(rows -1, cols - 1);
        for(int i = 0; i < rows; i++){
            if (i == row){
                continue;
            }
            for(int j = 0; j < cols; j++){
                if (j == col){
                    continue;
                }
                int x = i > row ? i - 1 : i;
                int y = j > col ? j - 1 : j;
                result.setElement(x,y,matrix[i][j]);
            }
        }
        return result;
    }

    @Override
    public double det() {
        if (rows == 1 && cols == 1){
            return matrix[0][0];
        }
        double value = 0;
        for(int col = 0; col < cols; col++){
            value += getElement(0, col) * Math.pow(-1, col + 2) * commatrice(0,col).det() ;
        }
        return value;
    }

    @Override
    public Matrix inverse() {
       double d = det();
       Matrix t = new Matrix(rows, cols);
       for(int row = 0; row < rows; row++){
           for(int col = 0; col < cols; col++){
               t.setElement(row,col,  Math.pow(-1, row + col + 2) * commatrice(row,col).det());
           }
       }
       return t.transpose().multiply(1/d);
    }
}
