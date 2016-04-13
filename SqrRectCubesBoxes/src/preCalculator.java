import java.math.*;
/**
 * Created by fantonio on 13/04/16.
 */
public class preCalculator {
    static long  [][] mat  = new long [101][6];
    public static void main (String [] args){
        for(int n=0;n<=100;n++) {
            mat[n][0] = computeSquares(2, n);
            mat[n][1] = computeRectangles2(n)-mat[n][0];
            mat[n][2] = computeSquares(3, n);
            mat[n][3] = computeRectangles3(n)-mat[n][2];
            mat[n][4] = computeSquares(4, n);
            mat[n][5] = computeRectangles4(n)-mat[n][4];
        }
        printMat();
    }

    public static long computeSquares(int dim , int n){
        long sq = 0;
        for(int i=1;i<=n;i++){
            sq += Math.pow(i,dim);
        }
        return sq;
    }

    public static long computeRectangles2(int n){
        long rec = 0;
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++) {
                rec += (n - i + 1) * (n - j + 1);
            }
        }
        return rec;
    }
    public static long computeRectangles3(int n){
        long rec = 0;
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    rec += (n - i + 1) * (n - j + 1) * (n - k + 1);
                }
            }
        }
        return rec;
    }
    public static long computeRectangles4(int n){
        long rec = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    for(int l=1;l<=n;l++){
                        rec += (n - i + 1) * (n - j + 1) * (n - k + 1) * (n - l + 1);
                    }
                }
            }
        }
        return rec;
    }

    public static void printMat(){
        String temp;
        System.out.print("long [][] mat = {");
        for(int i=0;i<mat.length;i++){
            System.out.print("{");
            for(int j=0;j<6;j++){
                System.out.print(mat[i][j]+"L");
                if(j!=5)System.out.print(", ");
            }
            temp = (i==mat.length-1)? "}":"},";
            System.out.println(temp);
        }
        System.out.println("};");
    }
}
