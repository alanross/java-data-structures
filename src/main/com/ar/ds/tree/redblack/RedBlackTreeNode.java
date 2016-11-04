package com.ar.ds.tree.redblack;

/**
 * @author Alan Ross
 * @version 0.1
 */
class RedBlackTreeNode
{
	// Friendly data; accessible by other package routines
	Comparable data;    // The data in the node
	RedBlackTreeNode left;       // Left child
	RedBlackTreeNode right;      // Right child
	int color;      // Color

	// Constructors
	RedBlackTreeNode( Comparable theElement )
	{
		this( theElement, null, null );
	}

	RedBlackTreeNode( Comparable data, RedBlackTreeNode left, RedBlackTreeNode right )
	{
		this.data = data;
		this.left = left;
		this.right = right;
		this.color = RedBlackTree.BLACK;
	}

	@Override
	public String toString()
	{
		return "[RedBlackTreeNode]";
	}
}