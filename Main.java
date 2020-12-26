package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] sizes1 = scanner.nextLine().split(" ");

        int rows1 = Integer.parseInt(sizes1[0]);
        int cols1 = Integer.parseInt(sizes1[1]);

        Matrix matrix1 = new Matrix(rows1, cols1);

        for (int row = 0; row < rows1; row++){
            matrix1.setRow(row,scanner.nextLine());
        }

//        String[] sizes2 = scanner.nextLine().split(" ");

//        int rows2 = Integer.parseInt(sizes2[0]);
//        int cols2 = Integer.parseInt(sizes2[1]);
//
//        Matrix matrix2 = new Matrix(rows2, cols2);
//
//        for (int row = 0; row < rows2; row++){
//            matrix2.setRow(row,scanner.nextLine());
//        }
//
//        if (rows1 == rows2 && cols1 == cols2) {
//            Matrix result = matrix1.add(matrix2);
//            System.out.println(result);
//        } else {
//            System.out.println("ERROR");
//        }

        int mult = scanner.nextInt();
        System.out.println(matrix1.multiply(mult));

    }
}
