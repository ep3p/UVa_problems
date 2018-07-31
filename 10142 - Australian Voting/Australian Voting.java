
import java.util.*;

class Main
{
    static Scanner entrada = new Scanner( System.in ); // .useLocale( Locale.US );

    public static void main( String args[] )
    {
        int num_casos = entrada.nextInt();
        entrada.nextLine(); // Paso del final de la primera línea a la segunda
//      entrada.nextLine(); // Leo la línea vacía que hay después del número de casos

        String nom_candidatos[] = new String [ 20 ];
        int votos[][] = new int [ 1000 ][ 20 ];
        int N;
        boolean activos[] = new boolean [ 20 ];

        for( int caso=1; caso <= num_casos; caso++ ) {
            
            String linea;
            do {
                linea = entrada.nextLine().trim();
            } while( entrada.hasNext() && linea.length() == 0 );

            int num_candidatos = Integer.parseInt( linea ); // entrada.nextInt();

            if ( num_candidatos <= 0 ) {
                if ( caso < num_casos ) System.out.println();
                continue;
            }

            for( int i=0; i < num_candidatos; i++ ) {
                nom_candidatos[i] = entrada.nextLine().trim();
            }
            N=0;
            while( entrada.hasNext() ) {
                linea = entrada.nextLine().trim();
                if ( linea.length() == 0 ) break;

                int i=0;
                for( int c=0; c < num_candidatos; c++ ) {
                    votos[N][c]=0;
                    while( i < linea.length() && (linea.charAt(i) < '0' || linea.charAt(i) > '9') ) i++;
                    while( i < linea.length() && (linea.charAt(i) >= '0' && linea.charAt(i) <= '9') ) {
                        votos[N][c] = 10*votos[N][c] + (linea.charAt(i)-'0');
                        i++;
                    }
                    votos[N][c]--;
                }
                N++;
            }

            int ganador = averigua_ganador( votos, activos, N, num_candidatos );

            if ( ganador != -1 ) {
                System.out.println( nom_candidatos[ganador] );
            } else {
                for( int c=0; c < num_candidatos; c++ ) {
                    if ( activos[c] ) System.out.println( nom_candidatos[c] );
                }
            }

            if ( caso < num_casos ) System.out.println();


            /* COMIENZO: Comprobamos que lo hemos leído bien
            System.out.println( "Caso : " + caso );
            System.out.println( num_candidatos + " candidatos." );
            for( int c=0; c < num_candidatos; c++ ) {
                System.out.println( nom_candidatos[c] );
            }
            System.out.println( N + " votos." );
            for( int v=0; v < N; v++ ) {
                for( int c=0; c < num_candidatos; c++ ) System.out.print( " " + votos[v][c] );
                System.out.println();
            }
            * FINAL: Comprobamos que lo hemos leído bien */
        }
    }
    
    static int averigua_ganador( int votos[][], boolean activos[], int N, int C )
    {
        // Aquí ponéis vosotros vuestro código
        // N Número de votos
        // C Número de candidatos

        //Array para contar los votos
        int contVotos[] = new int [C];
        //int p = 0;
        //Al inicio todos están activos
        for (int i=0; i<activos.length;i++){
            activos[i] = true;
        }
        int num_activos = activos.length;
        do{
            for (int i=0; i<C; i++){ //Al comienzo de cada pasada todos tienen 0 votos
                contVotos[i] = 0; 
            }

            for (int j=0; j<N; j++){
                for (int v=0; v<votos[j].length;v++){
                    if(activos[votos[j][v]]){
                        contVotos[votos[j][v]]++;
                        break;
                    }
                }
            }

            int maxV=0, minV=100000,ganador=-1;
            for (int i=0; i<contVotos.length;i++){
                if(activos[i]){
                if(contVotos[i]>maxV){maxV = contVotos[i];}
                if(contVotos[i]<minV){minV = contVotos[i];}
                if(contVotos[i]==maxV){ganador = i;}
                }
            }
            //Hay mayoría
            if (maxV*2>N) return ganador;
            //Todos empatan
            if (maxV==minV) return-1;
            //En otro caso se desactivan los candidatos con el minimo de votos
           
            for (int i=0; i<contVotos.length;i++){
                if(contVotos[i]==minV){activos[i]=false;num_activos--;}
            }
           
        }while(num_activos>0);
        return -1;
    }
}
