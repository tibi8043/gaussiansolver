package linear.algebra;

public class GaussianElimination {

    private int cols;
    private int rows;
    private double[][] matrix = new double[rows][cols];

    public GaussianElimination(int rows,int cols, double[][] matrix) {
        this.cols = cols;
        this.rows = rows;
        this.matrix = matrix;
    }

    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }    
    public double[][] getMatrix() {
        return this.matrix;
    }        
    public void setMatrix(double [][] newMatrix) {
        checkMatrixDimensions(newMatrix);
    }
    private int argMax(int row, int col){
        int maxRow = row;
        for(int i = row+1; i < rows; i++){
            if(Math.abs(matrix[i][col]) > Math.abs(matrix[maxRow][col])){
                maxRow = i;
            }
        }
        return maxRow;
    }
    private void swapRows(int row1, int row2){
        double [] tmp;
        tmp = matrix[row1];            
        matrix[row1] = matrix[row2];
        matrix[row2] = tmp;
        
    }
    private void multiplyAndAddRow(int addRow, int mulRow, int colIndex ){      
        double f = matrix[addRow][colIndex] / matrix[mulRow][colIndex];
        matrix[addRow][colIndex] = 0;
        for(int i = colIndex + 1; i < cols; i++){
            matrix[addRow][i] = matrix[addRow][i] -matrix[mulRow][i] * f;
        }
    }
    private void multiplyRow(int rowIndex, int colIndex){
        double mul = matrix[rowIndex][colIndex];
        for(int i = 0; i < cols; i++){
            matrix[rowIndex][i] /= mul;
        }
    }
    public void rowEchelonForm(){
        int h = 0;
        int k = 0; 
        while (h < rows && k < cols){            
            int i_max = argMax(h,k);
            if(matrix[i_max][k] == 0){
                k++;
            }else{
                swapRows(h,i_max);

                for(int i = h + 1; i<rows; i++){
                    multiplyAndAddRow(i,h,k);
                }

                multiplyRow(h,k);
                h++;
                k++;
            }
                                                    
        }
    }
    private void checkMatrixDimensions(double[][] newMatrix){        
        if (newMatrix.length != matrix.length ) {
            throw new IllegalArgumentException("Az egyenletrendszer nem megfelelő!");
        }else{
            this.matrix = newMatrix;
        }
    }

    public void backSubstitution(){
           for(int i = rows -1; i >= 0; i--) {            
            if(matrix[i][i] != 0){
                for(int j = i - 1; j >= 0; j--){
                    multiplyAndAddRow(j,i,i);
                }
            }else{                                  
                throw new IllegalArgumentException("Az egyenletrendszernek nincs megoldása");                                                        
            }
        }
    }
    
    public void print(){   
        String betuk = "";

        for(char c = 'a'; c <= 'z'; c++){
            betuk+=c;
        }    
        
        System.out.print("");
        for(int i = 0; i < rows; i++){            
            for(int j = 0; j < cols-1; ++j){
                if(matrix[i][j] >= 0){
                    System.out.print("+"+matrix[i][j]+""+betuk.charAt((j-cols+1)+26));
                }else{
                    System.out.print(matrix[i][j]+""+betuk.charAt((j-cols+1)+26));
                }                
            }
            System.out.print("="+matrix[i][cols-1]);
            System.out.println("");
        }
        System.out.print("\n");        
    }
}
