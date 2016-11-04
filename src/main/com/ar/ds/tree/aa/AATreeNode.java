package com.ar.ds.tree.aa;

/**
 * Basic node stored in AA trees.
 *
 * @author Alan Ross
 * @version 0.1
 */
class AATreeNode
{
	Comparable data;
	AATreeNode left;
	AATreeNode right;
	int level; // Level

	AATreeNode( Comparable data )
	{
		this( data, null, null );
	}

	AATreeNode( Comparable data, AATreeNode left, AATreeNode right )
	{
		this.data = data;
		this.left = left;
		this.right = right;
		this.level = 1;
	}

	@Override
	public String toString()
	{
		return "[AATreeNode]";
	}
}