package com.ar.ds.tree.quad;

import java.awt.*;

/**
 * http://algs4.cs.princeton.edu/92search/QuadTree.java.html
 * <p/>
 * Use for
 * Spatial indexing
 * Efficient collision detection in two dimensions
 * View frustum culling of terrain data
 *
 * @author Alan Ross
 * @version 0.1
 */
public class QuadTree
{
	private QuadTreeNode root;


	/**
	 * Insert (x, y) into appropriate quadrant
	 */
	public void insert( double x, double y, Object value )
	{
		root = insert( root, x, y, value );
	}

	private QuadTreeNode insert( QuadTreeNode h, double x, double y, Object value )
	{
		if( h == null )
		{
			return new QuadTreeNode( x, y, value );
		}
		else if( x < h.x && y < h.y )
		{
			h.SW = insert( h.SW, x, y, value );
		}
		else if( x < h.x && y >= h.y )
		{
			h.NW = insert( h.NW, x, y, value );
		}
		else if( x >= h.x && y < h.y )
		{
			h.SE = insert( h.SE, x, y, value );
		}
		else if( x >= h.x && y >= h.y )
		{
			h.NE = insert( h.NE, x, y, value );
		}
		return h;
	}

	/**
	 * Range search.
	 */
	public void query2D( Rectangle rect )
	{
		query2D( root, rect );
	}

	private void query2D( QuadTreeNode h, Rectangle rect )
	{
		if( h == null )
		{
			return;
		}
		int xmin = rect.x;
		int ymin = rect.y;
		int xmax = rect.x + rect.width;
		int ymax = rect.y + rect.height;

		if( rect.contains( h.x, h.y ) )
		{
			System.out.println( "    (" + h.x + ", " + h.y + ") " + h.value );
		}
		if( xmin < h.x && ymin < h.y )
		{
			query2D( h.SW, rect );
		}
		if( xmin < h.x && ymax >= h.y )
		{
			query2D( h.NW, rect );
		}
		if( xmax >= h.x && ymin <= h.y )
		{
			query2D( h.SE, rect );
		}
		if( xmax >= h.x && ymax >= h.y )
		{
			query2D( h.NE, rect );
		}
	}

	@Override
	public String toString()
	{
		return "[QuadTree]";
	}
}