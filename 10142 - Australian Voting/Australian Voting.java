import java.util.*;

class Main
{
    static Scanner input = new Scanner(System.in); 

    public static void main(String[] args)
    {
        int candidates_n;
        String candidates_list[];

        int votes_n, order_n;
        int votes[][] = new int [1000][20];
        boolean pos_president[];
        boolean winners[];
        int counter[];
        int pointers[];
        int maxim = 0, minim;

        int cases_n = input.nextInt(); input.nextLine();
        String line;
        for(int c=0; c < cases_n; c++)
        {
            do
                line = input.nextLine().trim();
            while(input.hasNext() && line.length() == 0);

            candidates_n = Integer.parseInt(line);
            candidates_list = new String [candidates_n];
            for(int i=0; i < candidates_n; i++)
                candidates_list[i] = input.nextLine().trim();
            votes_n = 0;
            do{
                line = input.nextLine().trim();
                if(line.length() == 0)
                    break;
                
                String[] integerStrings = line.split(" ");
                order_n = 0;
                for(String gap : integerStrings)
                    if(gap.length() > 0)
                    {
                        votes[votes_n][order_n] = Integer.parseInt(gap) - 1;
                        order_n++;
                    }
                votes_n++;
            }while(input.hasNext());

            pos_president = new boolean[candidates_n]; Arrays.fill(pos_president, true);
            winners = new boolean[candidates_n];
            pointers = new int [votes_n];
            
            while(true)
            {
                counter = new int[candidates_n];
                for(int i=0; i < votes_n; i++)
                {   
                    while(!pos_president[votes[i][pointers[i]]])
                        pointers[i]++;
                    maxim = ++counter[votes[i][pointers[i]]];
                    if(maxim <<1 > votes_n)
                    {   
                        winners[votes[i][pointers[i]]] = true;
                        break;
                    }
                }
                
                minim = maxim;
                for(int i=0; i < candidates_n; i++)
                    if(pos_president[i])
                    {
                        maxim = Math.max(maxim, counter[i]);
                        minim = Math.min(minim, counter[i]);
                    }
                if(minim == maxim)
                {   
                    winners = pos_president;
                    break;
                }
                else
                {
                    for(int i=0; i < candidates_n; i++)
                        if(pos_president[i] && minim == counter[i])
                            pos_president[i] = false;
                }

            }

            for(int i=0; i < candidates_n; i++)
                if (winners[i])
                    System.out.println(candidates_list[i]);
            if (c < cases_n-1)
                System.out.println();
        }
    }
}
