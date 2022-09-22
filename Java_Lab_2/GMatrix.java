import java.util.Arrays;
import java.util.Objects;

class GMatrix<Type> {
    Type[][] matrix;
    int n, m;

    public GMatrix(){
        initMatrix(0 , 0);
    }

    public GMatrix(int n, int m){
        initMatrix(n, m);
    }

    public GMatrix(int n){
        initMatrix(n, n);
    }

    public GMatrix(GMatrix <Type> mtrx){
        fill(mtrx.matrix);
    }

    public void initMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.matrix = (Type[][]) new Object[n][m];

    }

    public void fill(Type[][] arr){
        if (arr != null) {
            initMatrix(arr.length, arr[0].length);
            for (int i = 0;i<this.n;i++)
                for (int j = 0;j<this.m;j++)
                    this.matrix[i][j] = arr[i][j];
        }
        else
            this.matrix = null;
    }

    public String getElement(int i, int j){
        return ""+matrix[i][j];
    }

    public String getRow(int i){
        StringBuilder row = new StringBuilder();
        for (int j = 0;j<m;j++)
            row.append(matrix[i][j]).append(" ");
        return row.toString();
    }

    public String getColumn(int j){
        StringBuilder column = new StringBuilder();
        for (int i = 0;i<n;i++)
            column.append(matrix[i][j]).append(" ");
        return column.toString();
    }

    public void getMatrix() {
        if (this.n != 0 && this.m != 0) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++)
                    System.out.print(this.matrix[i][j] + " ");
                System.out.println();
            }
        }
        System.out.println();
    }

    public String getSize(){
        return n+"x"+m;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        GMatrix<Type> that = (GMatrix<Type>) obj;
        return n == that.n && m == that.m && Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(n, m);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }
    public void fill_element(Type elem, int i, int j) {
        this.matrix[i][j] = elem;
    }
}