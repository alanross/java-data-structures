package com.ar.ds.tree.avl;

/**
 * Basic node stored in AVL trees
 *
 * @author Alan Ross
 * @version 0.1
 */
class AvlTreeNode
{
	// Friendly data; accessible by other package routines
	Comparable element; // The data in the node
	AvlTreeNode left; // Left child
	AvlTreeNode right; // Right child
	int height; // Height

	AvlTreeNode( Comparable theElement )
	{
		this( theElement, null, null );
	}

	AvlTreeNode( Comparable theElement, AvlTreeNode lt, AvlTreeNode rt )
	{
		element = theElement;
		left = lt;
		right = rt;
		height = 0;
	}

	@Override
	public String toString()
	{
		return "[AvlTreeNode]";
	}
}