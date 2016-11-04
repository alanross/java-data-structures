package com.ar.ds.tree.bsp;

import java.util.LinkedList;
import java.util.Queue;


/**
 * A <code>ListNode</code> class is a very basic ListNode with 2 children.
 * <p/>
 * This is basically a plain vanilla node, only it has 2 children, making
 * it ideal for a binary tree.
 * <p/>
 * The data on the node is a <code>pQueue</code> object, (kinda like a list)
 * of stuff... and a partition plane. (a <code>pPlane2D</code> object)
 *
 * @author Particle
 * @version 1.0 12/31/1997
 */
public class BSPTreeNode
{
	/**
	 * the partion plane, (hey, it IS a BSP node...)
	 */
	public BSPPlane2D partition = null;

	/**
	 * a queue of "lines" that are on the partition plane.  This can also
	 * be a "list" (instead of a queue), I just like the queue better, and
	 * besides, that's how I return the "sorted" ones, so, why not use it...
	 * <p/>
	 * although, a simple List approach may be a bit faster...
	 */
	public Queue<BSPLine2D> lines = null;

	public BSPTreeNode front = null;

	public BSPTreeNode back = null;

	public BSPTreeNode()
	{
		partition = new BSPPlane2D();
		lines = new LinkedList<BSPLine2D>();
	}

	public BSPTreeNode getback()
	{
		return back;
	}

	public BSPTreeNode getfront()
	{
		return front;
	}

	public void setback( BSPTreeNode l )
	{
		back = l;
	}

	public void setfront( BSPTreeNode r )
	{
		front = r;
	}

	public String toString()
	{
		return new String( "" + partition + "/" + lines );
	}
}