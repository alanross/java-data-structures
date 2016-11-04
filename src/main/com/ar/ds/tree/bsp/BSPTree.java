package com.ar.ds.tree.bsp;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://en.wikipedia.org/wiki/BSP_tree
 * http://www.theparticle.com/javabsp.html
 * ftp://ftp.sgi.com/other/bspfaq/faq/bspfaq.html
 * <p/>
 * BSP trees are extremely versatile, because they are powerful sorting and classification structures.
 * <p/>
 * Binary space partitioning is a generic process of recursively dividing a scene
 * into two until the partitioning satisfies one or more requirements.
 * <p/>
 * The downside is the requirement for a time consuming pre-processing of the scene,
 * which makes it difficult and inefficient to directly implement moving objects into a BSP tree
 * <p/>
 * Used for
 * Hidden Surface Removal,
 *
 * @author Particle
 * @version 1.0 12/31/1997
 */

public class BSPTree
{

	public BSPTreeNode root;

	/**
	 * the list of sorted lines, it's here, to speed things up, and eliminate
	 * it's passing as a parameter to the sorting function
	 */
	Queue<BSPLine2D> list;

	/**
	 * the current eye position, it's passed to the sorting function, which
	 * later sets this variable, so that the recursive method won't have to pass
	 * it as a parameter when it's sorting...
	 */
	Point2D.Double eye;

	/**
	 * Constructs a <code>pBSPTree</code> object, and initialized root to
	 * <code>null</code>
	 */
	public BSPTree()
	{
		root = null;
	}

	/**
	 * a 'friendly' function for the user to construct a BSP Tree.
	 * <p/>
	 * The Queue parameter should have ALL the lines which are to be inserted,
	 * if something is on the tree previous to that, it will be overwritten by
	 * this new insertion...
	 *
	 * @param lines a Queue of lines to insert into the BSP Tree.
	 * @see Queue
	 */
	public void buildBSPTree( Queue<BSPLine2D> lines )
	{
		root = new BSPTreeNode();
		internalBuildBSPTree( root, lines );
	}

	/**
	 * a function to get the sorted "line" list.
	 *
	 * @param eye the pPoint2D object representing the eye.
	 * @return a Queue object of the sorted lines in relation to the eye.
	 */
	public Queue<BSPLine2D> getSortedLines( Point2D.Double eye )
	{
		list = new LinkedList<BSPLine2D>();
		this.eye = new Point2D.Double( eye.getX(), eye.getY() );
		internalGetSortedLines( root );
		return list;
	}

	/**
	 * a recursive (& private) function to build a bsp tree. Users should be
	 * calling a "friendly" function below...
	 *
	 * @param tree  the current node of the tree.
	 * @param lines a Queue of lines to examine.
	 */
	private void internalBuildBSPTree( BSPTreeNode tree, Queue<BSPLine2D> lines )
	{
		BSPLine2D current_line = ( BSPLine2D ) lines.remove();

		tree.partition = current_line.getPlane();
		tree.lines.add( current_line );
		Queue<BSPLine2D> frontList = new LinkedList<BSPLine2D>();
		Queue<BSPLine2D> backList = new LinkedList<BSPLine2D>();
		BSPLine2D line = null;
		while( !lines.isEmpty() )
		{
			line = ( BSPLine2D ) lines.remove();
			int result = tree.partition.eval( line );

			if( result == BSPPlane2D.IN_FRONT )
			{ /* in front */
				frontList.add( line );
			}
			else if( result == BSPPlane2D.IN_BACK )
			{ /* in back */
				backList.add( line );
			}
			else if( result == BSPPlane2D.SPANNING )
			{ /* spanning */
				BSPLine2D[] split_line = null;
				split_line = tree.partition.split( line );
				if( split_line != null )
				{
					frontList.add( split_line[ 0 ] );
					backList.add( split_line[ 1 ] );
				}
				else
				{
					/* error here, (TODO: throw some Exception) */
				}
			}
			else if( result == BSPPlane2D.COINCIDENT )
			{
				tree.lines.add( line );
			}
		}

		if( !frontList.isEmpty() )
		{
			tree.front = new BSPTreeNode();
			internalBuildBSPTree( tree.front, frontList );
		}
		if( !backList.isEmpty() )
		{
			tree.back = new BSPTreeNode();
			internalBuildBSPTree( tree.back, backList );
		}
	}

	/**
	 * a function to get the sorted lines in relation to the eye, the resulting sorted
	 * list is stored in list
	 *
	 * @param tree the current pBSPNode in recursion.
	 */
	private void internalGetSortedLines( BSPTreeNode tree )
	{
		if( tree == null )
		{
			return; /* check for end */
		}

		int result = tree.partition.eval( eye );
		if( result == BSPPlane2D.IN_FRONT ) // if eye in front of location
		{
			internalGetSortedLines( tree.back );

			Iterator<BSPLine2D> it = tree.lines.iterator();
			while( it.hasNext() )
			{
				list.add( it.next() );
			}

			internalGetSortedLines( tree.front );
		}
		else if( result == BSPPlane2D.IN_BACK ) // eye behind location
		{
			internalGetSortedLines( tree.front );

			Iterator<BSPLine2D> it = tree.lines.iterator();
			while( it.hasNext() )
			{
				list.add( it.next() );
			}

			internalGetSortedLines( tree.back );
		}
		else // the eye is on the partition plane
		{
			internalGetSortedLines( tree.front );
			internalGetSortedLines( tree.back );
		}
	}


	/**
	 * a method used by the "toString" to get the list of lines in an intrav order.
	 *
	 * @param p the current node in recursion
	 */
	public void intrav( BSPTreeNode p )
	{
		if( p == null )
		{
			return;
		}
		intrav( p.getback() );

		Iterator<BSPLine2D> it = p.lines.iterator();
		while( it.hasNext() )
		{
			list.add( it.next() );
		}

		intrav( p.getfront() );
	}

	/**
	 * a standard <code>toString</code> method, returns the whole tree in a
	 * <code>String</code> object.
	 *
	 * @return the whole tree in a String object.
	 */
	public String toString()
	{
		list = new LinkedList<BSPLine2D>();
		String s = new String();
		if( root == null )
		{
			return null;
		}
		intrav( root );
		while( !list.isEmpty() )
		{
			s += list.remove().toString();
		}
		return ( s += "\n" );
	}
}