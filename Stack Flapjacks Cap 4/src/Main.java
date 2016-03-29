import java.util.Scanner;

/**
 * Created by fantonio on 22/02/16.
 * Receives a stack of pancackes in reverse order and outputs the mandatory flips to get it sorted
 */
class Main {

    public static void main(String [] args){
        Scanner entrada  = new Scanner(System.in);
        String [] splitted;
        int [] pancakes;
        String line;
        while(entrada.hasNext()){
            line = entrada.nextLine();
            splitted = line.split("\\s+");
            pancakes = new int[splitted.length];
            for(int i=0; i<splitted.length;i++){
                pancakes [i] = Integer.parseInt(splitted[i]);
                if(i==splitted.length-1)System.out.print(splitted[i]);
                else{System.out.print(splitted[i]+" ");}
            }
            System.out.println();
            System.out.println(sortPancackes(pancakes));
        }
    }

    static String sortPancackes (int [] pancakes){
        String sol = "";
        int k;
        int len = pancakes.length;
        for (int i=len-1;i>0;i--) {
            k=i;
            for(int j=i-1;j>=0;j--){
                if (pancakes[j]>pancakes[k])k=j;
            }
            if(k!=i) {
                if(k!=0){
                    flap(0, k, pancakes);
                    sol+=(len-k)+" ";
                }
                flap(0, i, pancakes);
                sol+=(len-i)+" ";
            }
        }
        sol+="0";
        return sol;
    }

    static int [] flap (int i, int j, int [] pancakes){
        int aux;
        while (i < j) {
            aux = pancakes[i];
            pancakes[i] = pancakes[j];
            pancakes[j] = aux;
            i++;
            j--;
        }
        return pancakes;
    }

    static int valid (int [] pancakes){
        //printCakes(pancakes);
        for(int i=0;i<pancakes.length-1;i++){
            if(pancakes[i]>pancakes[i+1])return i;
        }
        return -1;
    }

    static void printCakes(int [] pancakes){
        System.out.print("Pancakes: ");
        for(int i=0;i<pancakes.length;i++){
            System.out.print(pancakes[i]+ " ");
        }
        System.out.println();
    }
}
