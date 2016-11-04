package com.ar.ds.tree.quad;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class QuadTreeNode
{
	double x, y; // x- and y- coordinates
	QuadTreeNode NW, NE, SE, SW; // four subtrees
	Object value; // associated data

	QuadTreeNode( double x, double y, Object value )
	{
		this.x = x;
		this.y = y;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "[QuadTreeNode]";
	}
}
