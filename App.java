package processor;

import java.util.Scanner;

public class App {
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);

    }
    private String menu(){
        return "1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice:";
    }

    private Matrix readMatrix(String label){
        System.out.println("Enter size of " + label + ":");
        String[] sizes = scanner.nextLine().split(" ");

        int rows = Integer.parseInt(sizes[0]);
        int cols = Integer.parseInt(sizes[1]);

        Matrix matrix = new Matrix(rows, cols);
        System.out.println("Enter " + label + ":");
        for (int row = 0; row < rows; row++){
            matrix.setRow(row,scanner.nextLine());
        }
        return matrix;
    }
    public void start(){
        while(true) {
            System.out.println(System.lineSeparator());
            System.out.print(menu());
            int choice = Integer.parseInt(scanner.nextLine());
            System.lineSeparator();
            Matrix m , n , result = null;
            int mult;
            switch (choice) {
                case 1:
                    //add matrix;
                      m = readMatrix("first matrix");
                      n = readMatrix("second matrix");
                      result = m .add(n);
                    break;
                case 2:
                    //mutiply by a constant;
                    m = readMatrix("matrix");
                    System.out.println("Enter constant:");
                    mult = Integer.parseInt(scanner.nextLine());
                    result = m.multiply(mult);
                    break;
                case 3:
                    //multiply matrix
                    m = readMatrix("first matrix");
                    n = readMatrix("second matrix");
                    try {
                        result = m.multiply(n);
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line\n" +
                            "Your choice:");
                    choice = Integer.parseInt(scanner.nextLine());
                    m = readMatrix("matrix");
                    switch (choice) {
                        case 1:
                            result = m.transpose();
                            break;
                        case 2:
                            result = m.sideTranspose();
                            break;
                        case 3:
                            result = m.verticalTranspose();
                            break;
                        case 4:
                            result = m.horizontalTranspose();

                    }
                    break;
                case 5:
                    m = readMatrix("matrix");
                    System.out.println(m.det());
                    break;
                case 6:
                    m = readMatrix("matrix");
                    result = m.inverse();
                    break;
                default:
                    System.exit(0);

            }
            if (result != null) {
                System.out.println(result);
            }
            }
        }
    }
