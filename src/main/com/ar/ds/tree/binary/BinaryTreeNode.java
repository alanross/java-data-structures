package com.ar.ds.tree.binary;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class BinaryTreeNode
{
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public int value = 0;

	public BinaryTreeNode( int value )
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return Integer.toString( value );
	}
}