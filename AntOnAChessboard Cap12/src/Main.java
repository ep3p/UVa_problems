import java.util.Scanner;
import java.math.*;
/**
 * Created by fantonio on 4/16/16.
 */
public class Main {

    public static void main(String [] args){
        Scanner kbd = new Scanner(System.in);
        int input = kbd.nextInt();
        while(input!=0){
            chessboard(input);
            kbd.nextLine();
            input = kbd.nextInt();
        }
        kbd.close();
    }

    /*
        Cuadrados perfectos(1,4,9,16,25), la diagonal describe una función polinómica.
    */
    public static void chessboard(int step){
        int row=-1, column=-1,sq,diagonal;
        sq = (int)Math.ceil(Math.sqrt((double)step));
        //Valor de la diagonal del cuadrado
        diagonal = sq*sq-sq+1;
        //Caso cuadrados perfectos pares
        if(sq*sq==step & step%2==0){row=1;column=(int)sq;}
        //Caso cuadrados perfectos impares
        else if(sq*sq==step & step%2==1){row=(int)sq;column=1;}
        //Caso par
        else{
            //System.out.println(diagonal);
            //La diagonal exacta
            if(step==diagonal){row=sq;column=sq;}
            //Por encima de la diagonal
            else if(sq%2==0){
                if(step>diagonal){
                    row=sq-Math.abs(step-diagonal);column=sq;
                }else{
                    row=sq;column=sq-Math.abs(step-diagonal);
                }
            }
            //Por debajo de la diagonal
            else {
                if (step < diagonal) {
                    row = sq - Math.abs(step - diagonal);
                    column = sq;
                } else {
                    row = sq;
                    column = sq - Math.abs(step - diagonal);
                }
            }
        }

        System.out.println(column+" "+row);
    }
}
