import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by fantonio on 3/29/16.
 */
public class Main {
    public static void main (String [] args){
        Scanner kbd = new Scanner(System.in);

        int testCases = kbd.nextInt();
        //Leemos línea en blanco
        kbd.nextLine();

        for(int i=1;i<=testCases;i++){
            //Leemos el número de personas a cruzar
            int numPers = kbd.nextInt();kbd.nextLine();
            int [] velocidades = new int [numPers];
            //La velocidad a la que pueden cruzar
            for(int j=0;j<numPers;j++){
                velocidades[j]=kbd.nextInt();
                if(j!=numPers-1){kbd.nextLine();}
            }
            puentes(velocidades);
            if(i!=testCases){
                System.out.println();
                //Leemos línea en blanco entre testcases
                kbd.nextLine();
            }
        }
    }

    public static void puentes(int [] vel){
        int sinCruzar = vel.length;
        int ida = 0, tiempoTotal = 0;
        int [][] cruces = new int [2000][2];
        int a,b,c,d;
        Arrays.sort(vel);
        a = vel[0];
        while(sinCruzar>0) {
            if (sinCruzar==1) {
                cruces[ida][0]=a;cruces[ida++][1]=-1;
                sinCruzar-=1;
                tiempoTotal+=a;
            }else if (sinCruzar == 2) {
                b = vel[1];
                cruces[ida][0]=a;cruces[ida++][1]=b;
                sinCruzar-=2;
                tiempoTotal+=b;
            } else if (sinCruzar == 3) {
                b = vel[1];
                c = vel[2];
                cruces[ida][0]=a;cruces[ida++][1]=b;
                cruces[ida][0]=a;cruces[ida++][1]=-1;
                cruces[ida][0]=a;cruces[ida++][1]=c;
                sinCruzar-=3;
                tiempoTotal+=c+a+b;
            } else {
                b = vel[1];
                c = vel[sinCruzar-2];
                d = vel[sinCruzar-1];
                if (a+3*b+d<=2*a+b+c+d){
                    cruces[ida][0] = a;cruces[ida++][1] = b;
                    cruces[ida][0] = a; cruces[ida++][1] = -1;
                    cruces[ida][0] = c; cruces[ida++][1] = d;
                    cruces[ida][0] = b; cruces[ida++][1] = -1;
                    tiempoTotal+= a+2*b+d;
                }else{
                    cruces[ida][0] = a;cruces[ida++][1] = d;
                    cruces[ida][0] = a; cruces[ida++][1] = -1;
                    cruces[ida][0] = a; cruces[ida++][1] = c;
                    cruces[ida][0] = a; cruces[ida++][1] = -1;
                    tiempoTotal+= d+c+2*a;
                }
                sinCruzar-=2;
            }
        }
        System.out.println(tiempoTotal);
        for(int i=0; i<ida;i++){
            System.out.print(cruces[i][0]);
            if(cruces[i][1]!=-1){
                System.out.println(" "+cruces[i][1]);
            }else{System.out.println();}
        }
    }
}
