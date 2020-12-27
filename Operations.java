package processor;

public interface Operations {
    public Matrix add(Matrix m);
    public Matrix multiply(int a);
    public Matrix multiply(Matrix m) throws  IllegalArgumentException;
    public Matrix transpose();
    public Matrix sideTranspose();
    public Matrix horizontalTranspose();
    public Matrix verticalTranspose();
}
