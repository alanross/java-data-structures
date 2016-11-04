package com.ar.ds.tree.splay;

/**
 * Basic node stored in unbalanced binary search trees
 * Note that this class is not accessible outside
 * of package DataStructures
 *
 * @author Alan Ross
 * @version 0.1
 */
class SplayTreeNode
{
	// Friendly data; accessible by other package routines
	Comparable element;      // The data in the node
	SplayTreeNode left;         // Left child
	SplayTreeNode right;        // Right child

	// Constructors
	SplayTreeNode( Comparable theElement )
	{
		this( theElement, null, null );
	}

	SplayTreeNode( Comparable theElement, SplayTreeNode lt, SplayTreeNode rt )
	{
		element = theElement;
		left = lt;
		right = rt;
	}

	@Override
	public String toString()
	{
		return "[SplayTreeNode]";
	}
}