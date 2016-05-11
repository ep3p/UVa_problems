import javax.sound.sampled.Line;
import java.util.*;
/**
 * Created by fantonio on 5/9/16.
 */
public class Main {
    public static void main(String [] args){
        Scanner kbd = new Scanner(System.in);
        while(kbd.hasNext()){
            computeRadio(kbd.nextDouble(),kbd.nextDouble(),kbd.nextDouble());
            try{
                kbd.nextLine();
            }catch(NoSuchElementException nse){}
        }
    }

    public static void computeRadio(double a, double b, double c){
        double x,y, alpha, theta,calpha,ctheta,incX,incY,dista,distb,distc;
        Point2D incenter;
        Point2D A = new Point2D(0.0,0.0);
        Point2D C = new Point2D(b,0.0);
        //Teorema del coseno para sacar el ángulo en el punto A(alpha)
        alpha = Math.acos((a*a-b*b-c*c)/(-2*b*c));
        calpha = Math.PI/2 - alpha;
        //Teorema del coseno para sacar el ángulo en el punto C(theta)
        theta = Math.acos((c*c-b*b-a*a)/(-2*b*a));
        ctheta = Math.PI/2 -theta;
        //Calculamos coordenadas del punto B
        y = c*Math.cos(calpha);
        x = c*Math.cos(alpha);
        Point2D B = new Point2D(x,y);
        if(!Triangle.collinear(A,B,C)){
            System.out.println("The radius of the round table is: 0.000");
            return;
        }
        //Calculamos el incentro
        incX = (a*A.getX()+b*B.getX()+c*C.getX())/(a+b+c);
        incY = (a*A.getY()+b*B.getY()+c*C.getY())/(a+b+c);
        incenter = new Point2D(incX, incY);
        //Calculamos la distancia más corta a las lineas descritas por cada par de puntos del triángulo
        Segment2D ab = new Segment2D(A,B);
        Segment2D ac = new Segment2D(A,C);
        Segment2D bc = new Segment2D(B,C);
        dista = incenter.distanceFrom(bc.closestPoint(incenter));
        distb = incenter.distanceFrom(ac.closestPoint(incenter));
        distc = incenter.distanceFrom(ab.closestPoint(incenter));
        System.out.println("The radius of the round table is: "+String.format("%.3f",Math.min(Math.min(dista,distb),distc)));
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


    public static class Segment2D
    {
        private Point2D p1, p2;
        private Line2D  line;

        public Segment2D( Point2D p1, Point2D p2 )
        {
            this.p1 = p1;
            this.p2 = p2;

            this.line = new Line2D( p1, p2 );
        }

        public Line2D getLine() { return line; }

        public double length() { return p1.distanceFrom( p2 ); }

        public boolean isVertical()
        {
            return Math.abs( p1.getX() - p2.getX() ) <= Point2D.EPSILON;
        }
        public boolean isHorizontal()
        {
            return Math.abs( p1.getY() - p2.getY() ) <= Point2D.EPSILON;
        }

        public boolean isParallelTo( Segment2D other )
        {
            return this.line.isParallelTo( other.line );
        }

        public boolean inSameLine( Segment2D other )
        {
            return this.line.isSameLineAs( other.line );
        }

        public boolean isInsideRectangle( Point2D p )
        {
            return Math.min( p1.getX(), p2.getX() ) <= p.getX() && p.getX() <= Math.max( p1.getX(), p2.getX() )
                    && Math.min( p1.getY(), p2.getY() ) <= p.getY() && p.getY() <= Math.max( p1.getY(), p2.getY() );
        }

        public Point2D intersection( Segment2D other )
        {
            if ( this.line.isSameLineAs( other.line ) ) {

                if (  this.isInsideRectangle( other.p1 ) ) return other.p1;
                if (  this.isInsideRectangle( other.p2 ) ) return other.p2;
                if ( other.isInsideRectangle(  this.p1 ) ) return  this.p1;
                if ( other.isInsideRectangle(  this.p2 ) ) return  this.p2;

                return null;
            }

            if ( this.line.isParallelTo( other.line ) ) return null;

            Point2D p = this.line.intersection( other.line );

            return ( p != null && this.isInsideRectangle( p ) && other.isInsideRectangle( p ) ) ? p : null;
        }

        /**
         * @return Ángulo más pequeño entre dos rectas medido en radianes. Puede ser negativo.
         */
        public double angle( Segment2D other )
        {
            return this.line.angle( other.line );
        }

        public Point2D closestPoint( Point2D p )
        {
            Point2D q = this.line.closestPoint( p );

            if ( q != null && isInsideRectangle( q ) ) {
                return q;
            } else if ( p1.distanceFrom( p ) < p2.distanceFrom( p ) ) {
                return p1;
            } else {
                return p2;
            }
        }


        // For the problem stated and solved in the book "Programming Challenges"
        public double distanceOfSuperman( Circle circles[] )
        {
            double dist = this.length();

            for( Circle c : circles ) dist += c.incrementByChord( this );

            return dist;
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

    public class Circle
    {
        private Point2D centre;
        private double  radius;

        public Circle( Point2D c, double r )
        {
            centre = c;
            radius = r;
        }

        public double getRadius() { return radius; }
        public double getDiameter() { return 2 * radius; }
        public double getCircumference() { return 2 * Math.PI * radius; }
        public double getArea() { return Math.PI * radius * radius; }


        public boolean separated( Circle other )
        {
            return this.centre.distanceFrom( other.centre ) > this.radius+other.radius;
        }
        public boolean intersect( Circle other )
        {
            return this.centre.distanceFrom( other.centre ) < this.radius+other.radius  &&  !this.contained( other );
        }
        public boolean contained( Circle other )
        {
            return this.centre.distanceFrom( other.centre )+Math.min( this.radius, other.radius ) <= Math.max( this.radius, other.radius );
        }

        public Line2D [] getTangents( Point2D p )
        {
            // Distance from the centre of this circle to the given point.
            double d = centre.distanceFrom( p );

            // If the point is inside the circle no tangent lines can exists
            if ( d < radius-Point2D.EPSILON ) return null;

            // If the point is just in the circle, then there is one tangent line
            if ( Math.abs( radius - d ) <= Point2D.EPSILON ) {
                Line2D perpendicular = new Line2D( this.centre, p );
                Line2D tangent[] = new Line2D [ 1 ];
                tangent[0] = new Line2D( p, -1.0/perpendicular.getSlope() );

                return tangent;
            }

            // Radius of the imaginary circle centered at 'p'.
            double x = Math.sqrt( d*d - radius*radius );

            // We obtain the intersection points between this circle and the imaginary one.
            Point2D [] intersectionPoints = getIntersectionPoints( new Circle( p, x ) );

            // We obtain the tangent lines from the intersection points.
            Line2D [] tangentLines = new Line2D [2];
            tangentLines[0] = new Line2D( p, intersectionPoints[0] );
            tangentLines[1] = new Line2D( p, intersectionPoints[1] );

            return tangentLines;
        }

        public Point2D [] getIntersectionPoints( Circle other )
        {
            // Consultar: http://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)

            if ( this.intersect( other ) && !this.contained( other ) ) {

                double r1 = this.radius;
                double x1 = this.centre.getX();
                double y1 = this.centre.getY();
                double r2 = other.radius;
                double x2 = other.centre.getX();
                double y2 = other.centre.getY();

                Line2D line = new Line2D( 2*(x2-x1),
                        2*(y2-y1),
                        -(r1*r1 - x1*x1 - y1*y1 - r2*r2 + x2*x2 + y2*y2) );

                return this.getIntersectionPoints( line );
            }
            return null;
        }

        public Point2D [] getIntersectionPoints( Line2D line )
        {
            // Consultar: http://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)

            // The line is defined as Ax+By+C=0, but for this formula we need ax + by = c
            double a =  line.getA();
            double b =  line.getB();
            double c = -line.getC();

            double den  = a*a + b*b;
            double sqrt = Math.sqrt( this.radius*this.radius * den - c*c );

            Point2D [] points = new Point2D [2];

            points[0] = new Point2D( (a*c + sqrt) / den, (b*c - sqrt) / den );
            points[1] = new Point2D( (a*c - sqrt) / den, (b*c + sqrt) / den );

            return points;
        }

        // For the problem stated and solved in the book "Programming Challenges"
        public double incrementByChord( Segment2D segment )
        {
            Point2D closestPoint = segment.getLine().closestPoint( this.centre );
            double d = this.centre.distanceFrom( closestPoint );

            if ( d >= 0 && d < this.radius  &&  segment.isInsideRectangle( closestPoint )  ) {
                double angle = Math.acos( d / this.radius );

                double segmentLength = 2.0 * Math.sqrt( radius*radius - d*d );
                double chordLength = 2.0 * angle * this.radius;

                return chordLength - segmentLength;
            } else {
                return 0.0;
            }
        }
    }

    public static class Triangle
    {
        private Point2D A, B, C;
        private Segment2D a, b, c;
        private double  alpha, beta, gamma;

        public Triangle( Point2D p1, Point2D p2, Point2D p3 )
        {
            A = p1;
            B = p2;
            C = p3;

            // Nos aseguramos que A es el punto más a la izquierda y más bajo de los tres.
            if ( A.compareTo( B ) > 0 ) { Point2D temp = A; A = B; B = temp; }
            if ( B.compareTo( C ) > 0 ) { Point2D temp = B; B = C; C = temp; }
            if ( A.compareTo( B ) > 0 ) { Point2D temp = A; A = B; B = temp; }

            if ( ! counterClockWise( A, B, C ) ) { Point2D temp = B; B = C; C = temp; }

            a = new Segment2D( B, C );
            b = new Segment2D( C, A );
            c = new Segment2D( A, B );

            alpha = Math.abs( b.angle(c) );
            beta = Math.abs( c.angle(a) );
            gamma = Math.PI - alpha - beta;
        }

        public Segment2D getSegmentA() { return a; }
        public Segment2D getSegmentB() { return b; }
        public Segment2D getSegmentC() { return c; }
        public double getAngleA() { return alpha; }
        public double getAngleB() { return beta; }
        public double getAngleC() { return gamma; }
        public Point2D getA() { return A; }
        public Point2D getB() { return B; }
        public Point2D getC() { return C; }

        public double area()
        {
            return Math.abs( signedArea( A, B, C ) );
        }
        public boolean pointInside( Point2D p )
        {
            return ! ( clockWise( A, B, p ) || clockWise( B, C, p ) || clockWise( C, A, p ) );
        }

        public static boolean pointInside( Point2D A, Point2D B, Point2D C, Point2D p )
        {
            return ! ( clockWise( A, B, p ) || clockWise( B, C, p ) || clockWise( C, A, p ) );
        }

        private static double signedArea( Point2D p1, Point2D p2, Point2D p3 )
        {
    /*
                 | ax ay 1 |
        2*A(T) = | bx by 1 | = (ax*by - ay*bx) + (-ax*cy + ay*cx) + 8bx*cy - cx*by)
                 | cx cy 1 |
    */
            return ( ( p1.getX()*p2.getY() - p1.getY()*p2.getX()
                    - p1.getX()*p3.getY() + p1.getY()*p3.getX()  // Esto está bien
                    + p2.getX()*p3.getY() - p2.getX()*p3.getY() ) / 2.0 );
        }

        public static boolean counterClockWise( Point2D p1, Point2D p2, Point2D p3 )
        {
            return ( signedArea( p1, p2, p3 ) > Point2D.EPSILON );
        }
        public static boolean clockWise( Point2D p1, Point2D p2, Point2D p3 )
        {
            return ( signedArea( p1, p2, p3 ) < -Point2D.EPSILON );
        }
        public static boolean collinear( Point2D p1, Point2D p2, Point2D p3 )
        {
            return ( Math.abs( signedArea( p1, p2, p3 ) ) <= Point2D.EPSILON );
        }
    }







}
