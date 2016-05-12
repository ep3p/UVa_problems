import java.util.*;
/**
 * Created by fantonio on 5/11/16.
 */
public class Main {
    public static void main(String [] args){
        Scanner kbd = new Scanner(System.in);
        while(kbd.hasNext()){
            try {
                isThisRealLifeOrItIsJustFantasy(kbd.nextDouble());
            }catch (NoSuchElementException nse){}
        }
    }

    public static void isThisRealLifeOrItIsJustFantasy(double r){
        double z1,z2,z3;
        double qradio = Math.PI*r*r/4.0;
        double corner = r*r-qradio;
        double ellipse = r*r-2*corner;
        double x, y;
        x = Math.cos(Math.toRadians(15))*r;
        y = Math.sin(Math.toRadians(15))*r;
        double semicirculoCuadrado = Math.toRadians(15)*r*r-x*y;
        z1 = semicirculoCuadrado*4+4*y*y;
        z2 = 2*(ellipse-z1);
        z3 = r*r-(z1+z2);
        System.out.println(String.format("%.3f",z1)+" "+String.format("%.3f",z2)+" "+String.format("%.3f",z3));
    }
}
