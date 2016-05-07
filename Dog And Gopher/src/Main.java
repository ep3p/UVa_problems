import java.util.*;
/**
 * Created by fantonio on 5/7/16.
 */
public class Main {
    public static void main(String[] args){
        Scanner kbd = new Scanner(System.in);
        String line;
        String [] data;
        Point2D dog, gopher;
        Point2D [] points;
        int npoints;
        while(kbd.hasNext()){
            line = kbd.nextLine();
            data = line.split("\\s+");
            npoints = Integer.parseInt(data[0]);
            points = new Point2D[npoints];
            gopher = new Point2D(Double.parseDouble(data[1]),Double.parseDouble(data[2]));
            dog = new Point2D(Double.parseDouble(data[3]),Double.parseDouble(data[4]));
            for(int i=0;i<npoints;i++){
                points[i] = new Point2D(kbd.nextDouble(),kbd.nextDouble());
                try {
                    kbd.nextLine();
                }catch(NoSuchElementException nse){}
            }
            calcuDog(dog, gopher, points);
            try {
                kbd.nextLine();
            }catch(NoSuchElementException nse){}
        }
    }

    public static void calcuDog(Point2D dog, Point2D gopher, Point2D [] p){

        for(int i=0;i<p.length;i++){
            if(p[i].distanceFrom(dog)>=p[i].distanceFrom(gopher)*2.0){
                System.out.print("The gopher can escape through the hole at (");
                System.out.print(String.format("%.3f,",p[i].getX()));
                System.out.print(String.format("%.3f",p[i].getY()));
                System.out.println(").");
                return;
            }
        }
        System.out.println("The gopher cannot escape.");
    }

    public static class Point2D
            implements Comparable
    {
        final public static double  EPSILON = 1.0e-5;

        final private double  x, y;

        public Point2D( double x, double y )
        {
            this.x = x;
            this.y = y;
        }

        public double getX() { return x; }
        public double getY() { return y; }

        public double distanceFrom( Point2D other )
        {
            double dx = this.x - other.x;
            double dy = this.y - other.y;

            return Math.sqrt( dx*dx + dy*dy );
        }

        public boolean isInferior( Point2D other )
        {
            return this.y < other.y
                    || ( this.y == other.y  &&  this.x < other.x );
            // || ( Math.abs( this.y - other.y ) <= EPSILON  && this.x < other.x );
        }

        public int compareTo( Object o )
        {
            if ( o instanceof Point2D ) {

                Point2D other = (Point2D)o;
                if ( this.x < other.x ) return -1;
                if ( this.x > other.x ) return  1;
                if ( this.y < other.y ) return -1;
                if ( this.y > other.y ) return  1;
                return 0;
            }

            return -1;
        }

        @Override
        public boolean equals( Object o )
        {
            if ( o instanceof Point2D ) {
                Point2D other = (Point2D)o;

                return ( Math.abs( this.x - other.x ) <= EPSILON
                        && Math.abs( this.y - other.y ) <= EPSILON );

            } else {
                return false;
            }
        }

        @Override
        public Object clone()
        {
            Point2D p = new Point2D( this.x, this.y );

            return p;
        }

        @Override
        public String toString()
        {
            return String.format( java.util.Locale.US, "(%.3f,%.3f)", x, y );
        }
    }
}
