class Main{
    public static void main(String[] args){
        System.out.println("_________________MUTABLE MATRIX_________________\n");
        Matrix matrix1 = new Matrix(3), matrix2 = new Matrix(4, 5);
        matrix1.genericMatrix(4);
        System.out.println("-----------------------------------\n\nMatrix1:\n");
        matrix1.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("Matrix1 is equal to matrix2: "+matrix1.equals(matrix2));
        System.out.println("\n-----------------------------------\n\nMatrix3:\n");
        Matrix matrix3 = new Matrix(matrix1);
        matrix3.getMatrix();
        System.out.println("-----------------------------------\n\nMatrix1 after filling element:\n");
        matrix3.fill_element(3.45, 1, 1);
        matrix3.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("Size of matrix1: "+matrix1.getSize());
        double[][] arr = {{1, 2}, {3, 4}}, arr1 = {{2, 1, 5}, {4, 3, 6}};
        Matrix result = new Matrix(arr.length, arr1[0].length);
        matrix1.fill(arr);
        matrix2.fill(arr1);
        System.out.println("\n-----------------------------------\n\nMatrix1 after filling:\n");
        matrix1.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("HashCode of Matrix1: "+matrix1.hashCode());
        matrix1.fill_element(50, 0, 1);
        System.out.println("HashCode of Matrix1 after filling element: "+matrix1.hashCode());
        System.out.println("\n-----------------------------------\n\nMatrix1*Matrix2:\n");
        result.fill(matrix1.multiplyMatrix(matrix2));
        result.getMatrix();
        System.out.println("-----------------------------------\n");
        try {
            System.out.println("Get element from result ([1][1]): "+result.getElement(1,1));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            System.out.println("Get row from result (1): "+result.getRow(1));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            System.out.println("Get column from result (2): "+result.getColumn(2));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            matrix1.inverseMatrix();
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        matrix2.generateRowMatrix(4);
        System.out.println("-----------------------------------\n");
        System.out.println("Size of RowMatrix: "+matrix2.getSize());
        System.out.println("\n-----------------------------------\n\nRowMatrix:\n");
        matrix2.getMatrix();


        System.out.println("_________________IMMUTABLE MATRIX_________________\n");
        IMatrix imatrix1 = new IMatrix(3), imatrix2 = new IMatrix(arr1), imatrix3;
        double[][] mtrx = {{10,2,3}, {4,5,6}, {7,8,9}};
        imatrix3 = new IMatrix(mtrx);
        System.out.println("IMatrix3:\n");
        imatrix3.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("IMatrix3 is equal to Matrix2: "+imatrix3.equals(imatrix2));
        System.out.println("\n-----------------------------------\n\nIMatrix4:\n");
        IMatrix imatrix4 = new IMatrix(imatrix3);
        imatrix4.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("Size of IMatrix3: "+imatrix1.getSize());
        System.out.println("-----------------------------------\n");
        System.out.println("HashCode of IMatrix3: "+imatrix3.hashCode());
        System.out.println("\n-----------------------------------\n\nIMatrix1*IMatrix2:\n");
        IMatrix iresult = new IMatrix(imatrix2.multiplyMatrix(imatrix3));
        iresult.getMatrix();
        System.out.println("-----------------------------------\n");
        try {
            System.out.println("Get element from iresult ([1][1]): "+iresult.getElement(1,1));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            System.out.println("Get row from iresult (1): "+iresult.getRow(1));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            System.out.println("Get column from iresult (2): "+iresult.getColumn(2));
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------\n");
        try {
            imatrix3.inverseMatrix();
        }
        catch(MyException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------\n");
        System.out.println("Matrix1 is equal to IMatrix1: "+matrix2.equals(imatrix2));


        System.out.println("\n\n_________________GENERIC MATRIX_________________\n\nGMatrix1:\n");
        GMatrix<Double> gmatrix1 = new GMatrix<>(2);
        Double[][] arr10 = {{10.0, 2.0}, {4.0, 5.0}};
        gmatrix1.fill(arr10);
        gmatrix1.getMatrix();
        System.out.println("-----------------------------------\n");
        System.out.println("Get row from gmatrix1: "+gmatrix1.getRow(1));
        System.out.println("\n-----------------------------------\n\nGMatrix2:\n");
        GMatrix<String> gmatrix2 = new GMatrix<>(2);
        String[][] arr11 = {{"Hello", "world"}, {"From", "Java"}};
        gmatrix2.fill(arr11);
        gmatrix2.fill_element("Bye-bye", 0, 0);
        gmatrix2.getMatrix();
        System.out.println("-----------------------------------\n");
    }
}