import java.util.Scanner;

class Main {
       static String[] computeShells(String[] a,  String [] b){
        int i=0, j=0;
        boolean fallo = false;
        String sol[] = new String[0];
        while(!fallo){
            if (i==a.length-1){
                if (a[i].equals(b[j])) {
                    j++;
                }
                sol = new String[b.length-j];
                for(int k=0;k<sol.length;k++){
                    sol[k] = b[j];
                    j++;
                }
                fallo = true;
            }else {
                if (a[i].equals(b[j])) {
                    i++;
                    j++;
                } else {
                    i++;
                }
            }
        }
        return sol;
    }

     public static  void main(String [] args){
        Scanner kbd = new Scanner(System.in);
        int cases = kbd.nextInt(); kbd.nextLine();
        int c;
        for(int i=0;i<cases; i++){
            c = kbd.nextInt();kbd.nextLine();
            String origen [] = new String[c];
            String destino [] = new String[c];
            for(int j=c-1;j>=0;j--){
                origen[j] = kbd.nextLine().trim();
            }
            for(int j=c-1;j>=0;j--){
                destino[j] = kbd.nextLine().trim();
            }
            String solution [] = computeShells(origen,destino);
            for(int j=0; j<solution.length;j++){
                System.out.println(solution[j]);
            }
            System.out.println();

        }
    }

}
