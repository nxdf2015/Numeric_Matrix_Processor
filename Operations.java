package processor;

public interface Operations {
    public Matrix add(Matrix m);
    public Matrix multiply(double a);
    public Matrix multiply(Matrix m) throws  IllegalArgumentException;
    public Matrix transpose();
    public Matrix sideTranspose();
    public Matrix horizontalTranspose();
    public Matrix verticalTranspose();
    public double det();
    public Matrix inverse();
}
