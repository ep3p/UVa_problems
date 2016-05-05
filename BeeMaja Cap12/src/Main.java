import java.util.*;
import java.math.*;
/**
 * Created by fantonio on 19/04/16.
 */
public class Main {

    public static void main(String [] args){
        Scanner kbd  = new Scanner(System.in);
        int entrada;
        while(kbd.hasNext()){
            maja(kbd.nextInt());
            try {
                kbd.nextLine();
            }catch(NoSuchElementException e){}

        }
    }

    public static void maja(int c){
        int ring, elements, first, last,clast, diag,cdiag, up, down,x,y;
        if(c==1){
            x=0;y=0;
        }else{
            int [] rfl  = computeRing(c);
            ring = rfl[0];
            elements = ring*6;
            first = rfl[1];
            last = rfl[2];
            clast = last-elements/2;
            diag = last-elements/6;
            cdiag = diag-elements/2;
            up = last -elements/3;
            down = first+(elements/6)-1;
            //System.out.println("Ring:"+ring+". First:"+first+ ". Last:"+last+ ". Elements: "+elements+". Down: "+down+". Up: "+up+". Diag: "+diag+". Cdiag: "+cdiag+". Last: "+last +". Clast: "+clast);
            //Coordenada X
            if(c==up){x=0;y=-ring;}
            else if(c==down){x=0;y=ring;}
            else if(c==last){x=ring;y=0;}
            else if(c==(up-ring)){x=-ring;y=0;}
            else{
                //Coordenada X
                if(c<=clast && c>=cdiag){
                    x=-ring;
                }else if(c>=diag && c<=last){
                    x=ring;
                }else if (Math.abs(up-c)<Math.abs(down-c)) {
                    x = (c - up);
                }else{
                    x = -(c-down);
                }

                //Coordenada Y
                if(c==last || c==clast){
                    y = 0;
                }
                else if(c<clast && c>=cdiag) {
                    y = (clast-c);
                }else if(c>=diag && c<last){
                    y = -(last-c);
                }
                else if(c>clast && c<last){
                    if(c<up){ y=-(c-clast);
                    }else{y=-(ring);}
                }else{
                    if(c>down){
                        y = ring;
                    }else{
                        y = c-(last-ring*6);
                    }
                }
            }
        }
        System.out.println(x+" "+y);
    }

    public static int computeLast(int ring){
        int last=1;
        for(int i=ring;i>=0;i--){
            last+= 6*i;
        }
        return last;
    }

    public static int[] computeRing(int c){
        int[] sol = new int[3];
        int ring  = 0, prev=1, next=1,i=1;
        while(i<=c){
            if(prev<=c && next>=c){
                sol[0] = ring;
                sol[1] = prev;
                sol[2] = next;
                return sol;
            }
            ring++;
            prev = next+1;
            next = next+6*i;
            i++;
        }
        return sol;
    }
}
