package linear;
import linear.algebra.GaussianElimination;

class EquationSolver{

    public static void main(String [] args){                                                  
        try{
            double [][] matrix = run(args);    
            GaussianElimination ge = new GaussianElimination(rows(args), cols(args), matrix);
            ge.print();
            ge.rowEchelonForm();
            ge.print();
            ge.backSubstitution();        
            ge.print();
        }catch(IllegalArgumentException exc){
            System.out.println("Hiba!:");
            System.out.println(exc.getMessage());
        }            
    }

    public static double[] stringsToDoubles(String input){        
        String [] szetszedett = input.split(",");
        double [] doubleArr = new double[input.length()];
        int n = szetszedett.length;
        for(int i = 0; i < n; i++){
            doubleArr[i] = Double.parseDouble(szetszedett[i]);
        }
        return doubleArr;
    }
    public static int cols(String args[]){        
        String [] details = args[0].strip().split(",");        
        return details.length;
    }
    public static int rows(String args []){
        return args.length;
    }
    public static double [][] run(String [] args){
        double result [][] = new double[rows(args)][cols(args)];
        /* 
                0    2,1,-1,8 
                1   -3,-1,2,-11 
                2   -2,1,2,-3
         */
        for(int i = 0; i< args.length; i++){
            result[i] = stringsToDoubles(args[i]);
        }                        
        return result;
    }
}
