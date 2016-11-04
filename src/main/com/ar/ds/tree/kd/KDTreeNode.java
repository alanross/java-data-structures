package com.ar.ds.tree.kd;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class KDTreeNode
{
	Comparable data[];
	KDTreeNode left;
	KDTreeNode right;

	KDTreeNode( Comparable item[] )
	{
		data = new Comparable[ 2 ];
		data[ 0 ] = item[ 0 ];
		data[ 1 ] = item[ 1 ];
		left = right = null;
	}

	@Override
	public String toString()
	{
		return "[KDTreeNode]";
	}

}
