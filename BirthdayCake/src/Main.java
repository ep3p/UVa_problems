import java.util.*;
/**
 * Created by fantonio on 5/11/16.
 */
public class Main {
    public static void main (String [] args){
        Scanner kbd = new Scanner(System.in);
        int n=-1;
        Point2D [] points;
        while(n!=0){
            n = kbd.nextInt();if(n==0)break;
            kbd.nextLine();
            points = new Point2D[n*2];
            for(int i=0;i<2*n;i++){
                points[i]  = new Point2D(kbd.nextDouble(),kbd.nextDouble());
                kbd.nextLine();
            }
            computeLine(n, points);
        }
    }

    public static void computeLine(int n, Point2D [] p){
        Line2D guess;
        Point2D temp;
        double val = 0;
        int right=0, left=0;
        int i;
        for(i=-500;i<=500;i++){
            for(int j=0;j<=500;j++){
                guess = new Line2D(i,j,0);
                for(int k=0;k<2*n;k++) {
                    temp = p[k];
                    val = i * temp.getX() + j * temp.getY();
                    if (val > 0) {
                        left++;
                    } else if (val < 0) {
                        right++;
                    } else {
                        break;
                    }
                }
                if(right==n && right==left){System.out.println(i+" "+j);return;}
                right =0;left =0;
         }
        }
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


    public static class Line2D
    {
        /** Coefficient for <em>x</em> */
        private double A;
        /** Coefficient for <em>y</em> */
        private double B;
        /** Constant term */
        private double C;

        public Line2D( double A, double B, double C )
        {
            this.A = A;
            this.B = B;
            this.C = C;
        }
        public Line2D( Point2D p1, Point2D p2 )
        {
            if ( p1.getX() == p2.getX() ) {
                // Vertical line
                A = 1.0;
                B = 0.0;
                C = -p1.getX();
            } else {
                A = -( p1.getY() - p2.getY() ) / ( p1.getX() - p2.getX() );
                B = 1.0;
                C = -( A * p1.getX() ) - ( B * p1.getY() );
            }
        }

        public Line2D( Point2D p, double m )
        {
            A = -m;
            B = 1.0;
            C = -( A * p.getX() ) - ( B * p.getY() );
        }

        public boolean isVertical()
        {
            return Math.abs(B) <= Point2D.EPSILON;
        }
        public boolean isHorizontal()
        {
            return Math.abs(A) <= Point2D.EPSILON;
        }

        public double getA() { return A; }
        public double getB() { return B; }
        public double getC() { return C; }

        public double getSlope() { return isVertical() ? Double.POSITIVE_INFINITY : -A/B; }

        public double f( double x )
        {
            return ( isVertical() ) ? 0.0 : ( -A*x + C ) / B;
        }

        public boolean isParallelTo( Line2D other )
        {
            return ( Math.abs( this.A - other.A ) <= Point2D.EPSILON
                    && Math.abs( this.B - other.B ) <= Point2D.EPSILON );
        }

        public boolean isSameLineAs( Line2D other )
        {
            return isParallelTo( other ) && Math.abs( this.C - other.C ) <= Point2D.EPSILON;
        }

        public Point2D intersection( Line2D other )
        {
            if ( this.isParallelTo( other ) ) return null;

            double x = (other.B * this.C - this.B * other.C) / (other.A * this.B - this.A * other.B);
            double y = this.isVertical() ? other.f(x) : this.f(x);

            return new Point2D( x, y );
        }

        /**
         * @return Ángulo más pequeño entre dos rectas medido en radianes. Puede ser negativo.
         */
        public double angle( Line2D other )
        {
            return Math.atan2( this.A*other.B - other.A*this.B,
                    this.A*other.A + this.B*other.B );
        }

        public Point2D closestPoint( Point2D p )
        {
            if ( this.isVertical() ) {
                return new Point2D( -C, p.getY() );
            } else if ( this.isHorizontal() ) {
                return new Point2D( p.getX(), -C );
            } else {
                return this.intersection( new Line2D( p, this.B/this.A ) );
            }
        }
    }

}