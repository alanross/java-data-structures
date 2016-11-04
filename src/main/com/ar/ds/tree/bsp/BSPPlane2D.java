package com.ar.ds.tree.bsp;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A <code>pPlane2D</code> class is a very basic 2D Plane.
 * <p/>
 * It allows users to set and get values of a 2D Plane, do some weird stuff with
 * the plane, like split lines with it... etc...
 *
 * @author Particle
 * @version 1.0 12/31/1997
 */
public class BSPPlane2D
{
	/**
	 * the Epsilon value to make this plane "thick"
	 */
	public final static double EPSILON = 0.001;

	/**
	 * The statics to differentiate between front/back/spanning "evaluations" of
	 * a point...
	 */
	public final static int SPANNING = 0;
	public final static int IN_FRONT = 1;
	public final static int IN_BACK = 2;
	public final static int COINCIDENT = 3;

	/**
	 * The 2 element array, with first standing for the A, second standing for
	 * B, and third standing for C in the: <code>Ax + By - C = 0</code> line
	 * equation.
	 */
	public double[] v = null;

	/**
	 * Constructs a new pPlane2D object
	 */
	public BSPPlane2D()
	{
		v = new double[ 3 ];
	}

	/**
	 * Constructs a new pPlane2D object given a line.
	 * <p/>
	 * let Y1 stand for first y, let Y2 stand for second y, and so on... let FX
	 * stand for starting point X, and FY stand for starting point Y, and so
	 * on... than the equation of a line would be:
	 * <p/>
	 * A = -DY; B = DX; C = DY * FX - DX * FY;
	 *
	 * @param l the line of which to make the pPlane2D.
	 */
	public BSPPlane2D( BSPLine2D l )
	{
		v = new double[ 3 ];
		double dx = ( l.getEnd().getX() - l.getStart().getX() );
		double dy = ( l.getEnd().getY() - l.getStart().getY() );
		A( -dy );
		B( dx );
		C( dy * l.getStart().getX() - dx * l.getStart().getY() );
	}

	/**
	 * Constructs a pPlane2D object out of a pPlane2D object, (basically a copy
	 * constructor)
	 *
	 * @param l the plane to "copy"
	 */
	public BSPPlane2D( BSPPlane2D l )
	{
		v = new double[ 3 ];
		A( l.A() );
		B( l.B() );
		C( l.C() );
	}

	/**
	 * Sets A in the line equation: Ax + By - C
	 */
	public void A( double a )
	{
		v[ 0 ] = a;
	}

	/**
	 * Sets B in the line equation: Ax + By - C
	 */
	public void B( double b )
	{
		v[ 1 ] = b;
	}

	/**
	 * Sets C in the line equation: Ax + By - C
	 */
	public void C( double c )
	{
		v[ 2 ] = c;
	}

	/**
	 * Gets A in the line equation: Ax + By - C
	 */
	public double A()
	{
		return v[ 0 ];
	}

	/**
	 * Gets B in the line equation: Ax + By - C
	 */
	public double B()
	{
		return v[ 1 ];
	}

	/**
	 * Gets C in the line equation: Ax + By - C
	 */
	public double C()
	{
		return v[ 2 ];
	}

	/**
	 * Function to evaluate whether a point (Point) is in "front" or "behind"
	 * the plane. also makes sure the plane is "thick"
	 *
	 * @param p the point to evaluate
	 * @return a condition, one of the statics like IN_FRONT, etc...
	 */
	public int eval( Point2D.Double p )
	{
		double c = A() * p.getX() + B() * p.getY() + C();
		if( c > BSPPlane2D.EPSILON )
		{
			return BSPPlane2D.IN_FRONT;
		}
		else if( c < ( -BSPPlane2D.EPSILON ) )
		{
			return BSPPlane2D.IN_BACK;
		}
		else
		{
			return BSPPlane2D.SPANNING;
		}
	}

	/**
	 * the function to evaluate a line in terms of this plane
	 *
	 * @param l the line to evaluate
	 * @return one of the "statics" like IN_FRONT, etc...
	 */
	public int eval( BSPLine2D l )
	{
		int a = eval( l.getStart() );
		int b = eval( l.getEnd() );
		if( a == BSPPlane2D.SPANNING )
		{
			if( b == BSPPlane2D.SPANNING )
			{
				return BSPPlane2D.COINCIDENT;
			}
			else
			{
				return b;
			}
		}
		if( b == BSPPlane2D.SPANNING )
		{
			if( a == BSPPlane2D.SPANNING )
			{
				return BSPPlane2D.COINCIDENT;
			}
			else
			{
				return a;
			}
		}
		if( ( a == BSPPlane2D.IN_FRONT ) && ( b == BSPPlane2D.IN_BACK ) )
		{
			return SPANNING;
		}
		if( ( a == BSPPlane2D.IN_BACK ) && ( b == BSPPlane2D.IN_FRONT ) )
		{
			return SPANNING;
		}
		return a; /* all on 1 side */
	}

	/**
	 * function to split a line with this plane.
	 * <p/>
	 * the "front" piece of the split becomes the first element in the returning
	 * array, the "back" piece of the split, becomes the second.
	 * <p/>
	 * there is an assumption, that "positive eval()" is front, and negative
	 * eval() is in back.
	 *
	 * @param l the line to split.
	 * @return a 2 element array, with front and back pieces of the line
	 * @see BSPLine2D
	 * @see Point
	 */
	public BSPLine2D[] split( BSPLine2D l )
	{
		BSPPlane2D p = new BSPPlane2D( l );
		BSPLine2D[] q = new BSPLine2D[ 2 ];
		double cross_x = 0, cross_y = 0;
		double divider = A() * p.B() - B() * p.A();
		if( divider == 0 )
		{ /* should never happen */
			if( p.A() == 0 )
			{
				cross_x = l.getStart().getX();
			}
			if( p.B() == 0 )
			{
				cross_y = l.getStart().getY();
			}
			if( A() == 0 )
			{
				cross_y = -B();
			}
			if( B() == 0 )
			{
				cross_x = C();
			}
		}
		else
		{
			cross_x = ( -C() * p.B() + B() * p.C() ) / divider;
			cross_y = ( -A() * p.C() + C() * p.A() ) / divider;
		}
		int p1 = eval( l.getStart() );
		int p2 = eval( l.getEnd() );
		q[ 0 ] = new BSPLine2D( l );
		q[ 1 ] = new BSPLine2D( l );
		if( ( p1 == BSPPlane2D.IN_BACK ) && ( p2 == BSPPlane2D.IN_FRONT ) )
		{
			q[ 0 ].setStart( new Point2D.Double( cross_x, cross_y ) );
			q[ 0 ].setEnd( new Point2D.Double( l.getEnd().getX(), l.getEnd().getY() ) );
			q[ 1 ].setStart( new Point2D.Double( l.getStart().getX(), l.getStart().getY() ) );
			q[ 1 ].setEnd( new Point2D.Double( cross_x, cross_y ) );
		}
		else if( ( p1 == BSPPlane2D.IN_FRONT ) && ( p2 == BSPPlane2D.IN_BACK ) )
		{
			q[ 0 ].setStart( new Point2D.Double( l.getStart().getX(), l.getStart().getY() ) );
			q[ 0 ].setEnd( new Point2D.Double( cross_x, cross_y ) );
			q[ 1 ].setStart( new Point2D.Double( cross_x, cross_y ) );
			q[ 1 ].setEnd( new Point2D.Double( l.getEnd().getX(), l.getEnd().getY() ) );
		}
		else
		{
			return null; /* trying to split a spanning line */
		}
		return q;
	}

	/**
	 * the standard <code>toString</code> method
	 */
	public String toString()
	{
		return new String( "pPlane2D(" + A() + "," + B() + "," + C() + ");\n" );
	}
}