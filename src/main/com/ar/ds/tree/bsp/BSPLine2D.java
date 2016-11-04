package com.ar.ds.tree.bsp;

import java.awt.geom.Point2D;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class BSPLine2D
{
	/**
	 * A 2 element array of <code>Point2D.Double</code> objects, representing
	 * the 'start' and 'end' of the line.
	 *
	 * @see Point2D.Double
	 */
	public Point2D.Double[] v = null;

	/**
	 * Constructs a <code>BSPLine2D</code> object.
	 *
	 * @see Point2D.Double
	 */
	public BSPLine2D()
	{
		v = new Point2D.Double[ 2 ];
		setStart( new Point2D.Double() );
		setEnd( new Point2D.Double() );
	}

	/**
	 * Constructs a <code>BSPLine2D</code> object from a BSPLine2D object,
	 * (basically a copy constructor).
	 *
	 * @param l the pLine2d of which to make a copy of.
	 * @see Point2D.Double
	 */
	public BSPLine2D( BSPLine2D l )
	{
		v = new Point2D.Double[ 2 ];
		setStart( new Point2D.Double( l.getStart().getX(), l.getStart().getY() ) );
		setEnd( new Point2D.Double( l.getEnd().getX(), l.getEnd().getY() ) );
	}

	/**
	 * Constructs a <code>BSPLine2D</code> object from 2 Point2D.Double objects.
	 * (the start <code>Point2D.Double</code> and an end
	 * <code>Point2D.Double</code>
	 *
	 * @param a the start of the line
	 * @param b the end of the line
	 */
	public BSPLine2D( Point2D.Double a, Point2D.Double b )
	{
		v = new Point2D.Double[ 2 ];
		setStart( new Point2D.Double( a.getX(), a.getY() ) );
		setEnd( new Point2D.Double( b.getX(), b.getY() ) );
	}

	/**
	 * Gets the starting point of the line
	 *
	 * @return the starting point.
	 */
	public Point2D.Double getStart()
	{
		return v[ 0 ];
	}

	/**
	 * Gets the endint point of the line
	 *
	 * @return the ending point.
	 */
	public Point2D.Double getEnd()
	{
		return v[ 1 ];
	}

	/**
	 * Sets the start of the line
	 *
	 * @param s the starting point.
	 */
	public void setStart( Point2D s )
	{
		v[ 0 ] = new Point2D.Double( s.getX(), s.getY() );
	}

	/**
	 * Sets the ending point of the line
	 *
	 * @param e the ending point of the line
	 */
	public void setEnd( Point2D.Double e )
	{
		v[ 1 ] = new Point2D.Double( e.getX(), e.getY() );
	}

	/**
	 * Gets the "plane" which this line is part of. Basically gets those "plane"
	 * methods for determining whether some point (or line) is in front of
	 * behind it, and an ability to split lines.
	 *
	 * @return the plane for that line
	 */
	public BSPPlane2D getPlane()
	{
		return new BSPPlane2D( this );
	}

	public String toString()
	{
		return new String( "BSPLine2D(" + getStart() + "," + getEnd() + ");\n" );
	}
}
