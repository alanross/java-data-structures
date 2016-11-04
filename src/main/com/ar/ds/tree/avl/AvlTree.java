package com.ar.ds.tree.avl;

/**
 * http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/
 * <p/>
 * http://en.wikipedia.org/wiki/AVL_tree
 * <p/>
 * a self-balancing binary search tree
 * <p/>
 * Average		Worst case
 * Space	O(n)		O(n)
 * Search	O(log n)	O(log n)
 * Insert	O(log n)	O(log n)
 * Delete	O(log n)	O(log n)
 * <p/>
 * void insert( x ) --> Insert x
 * void remove( x ) --> Remove x (unimplemented)
 * Comparable find( x ) --> Return item that matches x
 * Comparable findMin( ) --> Return smallest item
 * Comparable findMax( ) --> Return largest item
 * boolean isEmpty( ) --> Return true if empty; else false
 * void empty( ) --> Remove all items
 * void printTree( ) --> Print tree in sorted order
 *
 * @author Alan Ross
 * @version 0.1
 */
public class AvlTree
{
	private AvlTreeNode _root;

	public AvlTree()
	{
		_root = null;
	}

	public void insert( Comparable x )
	{
		_root = insert( x, _root );
	}

	public void remove( Comparable x )
	{
		System.out.println( "Sorry, remove unimplemented" );
	}

	public Comparable findMin()
	{
		return elementAt( findMin( _root ) );
	}

	public Comparable findMax()
	{
		return elementAt( findMax( _root ) );
	}

	public Comparable find( Comparable x )
	{
		return elementAt( find( x, _root ) );
	}

	public void makeEmpty()
	{
		_root = null;
	}

	public boolean isEmpty()
	{
		return _root == null;
	}

	public void printTree()
	{
		if( isEmpty() )
		{
			System.out.println( "Empty tree" );
		}
		else
		{
			printTree( _root );
		}
	}

	private Comparable elementAt( AvlTreeNode t )
	{
		return t == null ? null : t.element;
	}

	private AvlTreeNode insert( Comparable x, AvlTreeNode t )
	{
		if( t == null )
		{
			t = new AvlTreeNode( x, null, null );
		}
		else if( x.compareTo( t.element ) < 0 )
		{
			t.left = insert( x, t.left );
			if( height( t.left ) - height( t.right ) == 2 )
			{
				if( x.compareTo( t.left.element ) < 0 )
				{
					t = rotateWithLeftChild( t );
				}
				else
				{
					t = doubleWithLeftChild( t );
				}
			}
		}
		else if( x.compareTo( t.element ) > 0 )
		{
			t.right = insert( x, t.right );
			if( height( t.right ) - height( t.left ) == 2 )
			{
				if( x.compareTo( t.right.element ) > 0 )
				{
					t = rotateWithRightChild( t );
				}
				else
				{
					t = doubleWithRightChild( t );
				}
			}
		}
		else
		{
			; // Duplicate; do nothing
		}
		t.height = max( height( t.left ), height( t.right ) ) + 1;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 *
	 * @param t the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private AvlTreeNode findMin( AvlTreeNode t )
	{
		if( t == null )
		{
			return t;
		}

		while( t.left != null )
		{
			t = t.left;
		}
		return t;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 *
	 * @param t the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private AvlTreeNode findMax( AvlTreeNode t )
	{
		if( t == null )
		{
			return t;
		}

		while( t.right != null )
		{
			t = t.right;
		}
		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 *
	 * @param x is item to search for.
	 * @param t the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private AvlTreeNode find( Comparable x, AvlTreeNode t )
	{
		while( t != null )
		{
			if( x.compareTo( t.element ) < 0 )
			{
				t = t.left;
			}
			else if( x.compareTo( t.element ) > 0 )
			{
				t = t.right;
			}
			else
			{
				return t; // Match
			}
		}

		return null; // No match
	}

	private void printTree( AvlTreeNode t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	/**
	 * Return the height of node t, or -1, if null.
	 */
	private static int height( AvlTreeNode t )
	{
		return t == null ? -1 : t.height;
	}

	/**
	 * Return maximum of lhs and rhs.
	 */
	private static int max( int lhs, int rhs )
	{
		return lhs > rhs ? lhs : rhs;
	}

	/**
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new _root.
	 */
	private static AvlTreeNode rotateWithLeftChild( AvlTreeNode k2 )
	{
		AvlTreeNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = max( height( k1.left ), k2.height ) + 1;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation for case 4. Update heights, then return new _root.
	 */
	private static AvlTreeNode rotateWithRightChild( AvlTreeNode k1 )
	{
		AvlTreeNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child. For AVL trees, this is a double
	 * rotation for case 2. Update heights, then return new _root.
	 */
	private static AvlTreeNode doubleWithLeftChild( AvlTreeNode k3 )
	{
		k3.left = rotateWithRightChild( k3.left );
		return rotateWithLeftChild( k3 );
	}

	/**
	 * Double rotate binary tree node: first right child with its left child;
	 * then node k1 with new right child. For AVL trees, this is a double
	 * rotation for case 3. Update heights, then return new _root.
	 */
	private static AvlTreeNode doubleWithRightChild( AvlTreeNode k1 )
	{
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}
}