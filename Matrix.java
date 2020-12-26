package processor;

public class Matrix implements Operations{

    private int rows,cols;
    private int[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
    }
    public void setElement(int row, int col, int value) {
       matrix[row][col] = value;
    }

    public int getElement(int row, int col){
        return matrix[row][col];
    }

    public void setRow(int row, int ... values){
        for(int col = 0; col < cols; col++) {
            setElement(row,col,values[col]);
        }
    }

    public void setRow(int row , String values) {
        String[] temp = values.split(" ");
        int[] elements = new int[cols];
        for(int col = 0; col < cols; col++) {
            elements[col] = Integer.parseInt(temp[col]);
        }
        setRow(row,elements);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
       for(int[] elements : matrix){
           for(int element : elements){
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
                int value = m.getElement(row,col) + matrix[row][col];
                result.setElement(row, col, value);
            }
        }
        return result;
    }
}
