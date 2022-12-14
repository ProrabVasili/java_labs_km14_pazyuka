import java.util.Arrays;
import java.util.Objects;

class MyException extends Exception {
    public MyException (String message) {
        super (message);
    }
}

class Matrix {
    double[][] matrix;
    int n, m;


    public Matrix(){
        initMatrix(0 , 0);
    }

    public Matrix(int n, int m){
        initMatrix(n, m);
    }

    public Matrix(int n){
        initMatrix(n, n);
    }

    public Matrix(Matrix mtrx){
        fill(mtrx.matrix);
    }

    public void fill(double[][] arr){
        if (arr != null) {
            initMatrix(arr.length, arr[0].length);
            for (int i = 0;i<this.n;i++)
                for (int j = 0;j<this.m;j++)
                    this.matrix[i][j] = arr[i][j];
        }
        else
            this.matrix = null;
    }

    public void fill_element(double elem, int i, int j) {
        this.matrix[i][j] = elem;
    }

    private void initMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        if (n != 0 && m != 0)
            this.matrix = new double[n][m];
        else
            this.matrix = null;
    }

    public String getElement(int i, int j) throws MyException {
        if (i >= n || i < 0 || j >= m || j < 0) {
            throw new MyException("Matrix doesn't exist!");
        }
        return ""+matrix[i][j];
    }

    public String getRow(int i) throws MyException {
        if (i >= n || i < 0) {
            throw new MyException("No such row exists!");
        }
        StringBuilder row = new StringBuilder();
        for (int j = 0;j<m;j++)
            row.append(matrix[i][j]).append(" ");
        return row.toString();
    }

    public String getColumn(int j) throws MyException {
        if (j >= m || j < 0) {
            throw new MyException("No such column exists!");
        }
        StringBuilder column = new StringBuilder();
        for (int i = 0;i<n;i++)
            column.append(matrix[i][j]).append(" ");
        return column.toString();
    }

    public String getSize(){
        return this.n+"x"+this.m;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        Matrix that = (Matrix) obj;
        return n == that.n && m == that.m && Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(n, m);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }


    public void genericMatrix(int n, int m) {
        initMatrix(n, m);
        for (int i = 0;i<n;i++)
            for (int j = 0;j<m;j++)
                this.matrix[i][j] = 20*round_n(0.5-Math.random(), 4);
    }

    public void genericMatrix(int n) {
        genericMatrix(n, n);
    }

    private static double round_n(double num, int n) {
        double power = Math.pow(10, n);
        return Math.round(num * power)/power;
    }

    public void generateRowMatrix(int size) {
        genericMatrix(1, size);
    }

    public void getMatrix() {
        if (this.n != 0 && this.m != 0) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++)
                    System.out.print(round_n(this.matrix[i][j], 3) + " ");
                System.out.println();
            }
        }
        System.out.println();
    }

    private void rows_add(int i, double[][] mtrx, double[][] result) throws MyException {
        boolean flag = true;
        for (int j = 0;j<mtrx.length;j++)
            if (i != j && mtrx[j][i] != 0) {
                for (int d = 0;d<mtrx.length;d++) {
                    mtrx[i][d] += mtrx[j][d];
                    result[i][d] += result[j][d];
                }
                flag = false;
                break;
            }
        if (flag) {
            throw new MyException("Inverse matrix doesn't exist!");
        }
    }

    private void diagonal_not_zero(double[][] mtrx, double[][] result) throws MyException {
        for (int i = 0;i<mtrx.length;i++) {
            if (mtrx[i][i] == 0) {
                rows_add(i, mtrx, result);
            }
        }
    }

    private void rows_subtraction(int i, double[][] mtrx, double[][] result) throws MyException {
        for (int j = 0;j<mtrx.length;j++) {
            if (i != j) {
                double temp1 = mtrx[j][i];
                for (int k = 0;k<mtrx.length;k++) {
                    mtrx[j][k] -= temp1 * mtrx[i][k];
                    result[j][k] -= temp1 * result[i][k];
                    if (result[j][k] > Double.MAX_VALUE)
                        throw new MyException("Inverse matrix doesn't exist!");
                }
            }
        }
    }

    private void gaussianElimination(double[][] mtrx, double[][] result) throws MyException {
        for (int i = 0;i<mtrx.length;i++) {
            double temp = mtrx[i][i];
            for (int j = 0;j<mtrx.length;j++) {
                mtrx[i][j] /= temp;
                result[i][j] /= temp;
            }
            rows_subtraction(i, mtrx, result);
        }
    }


    public void printMatrix(String msg, double[][] mtrx){
        System.out.println(msg+":");
        for (double[] doubles : mtrx) {
            for (int j = 0; j < mtrx.length; j++)
                System.out.print(round_n(doubles[j], 3) + " ");
            System.out.println();
        }
        System.out.println();
    }

    public double[][] multiplyMatrix(Matrix mtrx){
        double[][] result = new double[this.n][mtrx.m];
        if (this.m != mtrx.n) {
            System.out.println("Number of columns of the first matrix != number of rows of the second matrix");
            return result;
        }
        for (int i = 0;i<this.n;i++) {
            for (int j = 0;j<mtrx.m;j++) {
                double sum = 0;
                for (int k = 0;k<this.m;k++)
                    sum += this.matrix[i][k]*mtrx.matrix[k][j];
                result[i][j] = sum;
            }
        }
        return result;
    }

    public void inverseMatrix() throws MyException {
        int n = this.n;
        if (this.n != this.m)
            throw new MyException("Matrix isn't square!");

        Matrix result = new Matrix(n), matrixCopy = new Matrix(this), check = new Matrix(n);
        for (int i = 0;i<n;i++)
            result.matrix[i][i] = 1;
        diagonal_not_zero(matrixCopy.matrix, result.matrix);
        gaussianElimination(matrixCopy.matrix, result.matrix);
        printMatrix("Result after Gaussian Elimination", matrixCopy.matrix);
        printMatrix("Original Matrix", this.matrix);
        printMatrix("Inversion Matrix", result.matrix);
        check.matrix = this.multiplyMatrix(result);
        printMatrix("Check answer", check.matrix);
    }
}


