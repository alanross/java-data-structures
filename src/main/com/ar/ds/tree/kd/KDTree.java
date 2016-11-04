package com.ar.ds.tree.kd;

/**
 * http://users.cis.fiu.edu/~weiss/dsaajava/code/Miscellaneous/
 * http://en.wikipedia.org/wiki/Kd-tree
 * <p/>
 * kd-tree (short for k-dimensional tree) is a space-partitioning data structure
 * for organizing points in a k-dimensional space. kd-trees are a useful data
 * structure for several applications, such as searches involving a multidimensional
 * search key (e.g. range searches and nearest neighbor searches).
 * Quick illustration of a two-dimensional tree.
 *
 * @author Alan Ross
 * @version 0.1
 */
public class KDTree
{
	private KDTreeNode _root;

	public KDTree()
	{
		_root = null;
	}

	public void insert( Comparable[] x )
	{
		_root = insert( x, _root, 0 );
	}

	private KDTreeNode insert( Comparable[] x, KDTreeNode t, int level )
	{
		if( t == null )
		{
			t = new KDTreeNode( x );
		}
		else if( x[ level ].compareTo( t.data[ level ] ) < 0 )
		{
			t.left = insert( x, t.left, 1 - level );
		}
		else
		{
			t.right = insert( x, t.right, 1 - level );
		}
		return t;
	}

	/**
	 * Print items satisfying low[ 0 ] <= x[ 0 ] <= high[ 0 ] and low[ 1 ] <= x[
	 * 1 ] <= high[ 1 ].
	 */
	public void printRange( Comparable[] low, Comparable[] high )
	{
		printRange( low, high, _root, 0 );
	}

	private void printRange( Comparable[] low, Comparable[] high, KDTreeNode t, int level )
	{
		if( t != null )
		{
			if( low[ 0 ].compareTo( t.data[ 0 ] ) <= 0 && low[ 1 ].compareTo( t.data[ 1 ] ) <= 0
					&& high[ 0 ].compareTo( t.data[ 0 ] ) >= 0 && high[ 1 ].compareTo( t.data[ 1 ] ) >= 0 )
			{
				System.out.println( "(" + t.data[ 0 ] + "," + t.data[ 1 ] + ")" );
			}

			if( low[ level ].compareTo( t.data[ level ] ) <= 0 )
			{
				printRange( low, high, t.left, 1 - level );
			}
			if( high[ level ].compareTo( t.data[ level ] ) >= 0 )
			{
				printRange( low, high, t.right, 1 - level );
			}
		}
	}
}