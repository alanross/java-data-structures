package com.ar.ds.tree.redblack;

/**
 * http://en.wikipedia.org/wiki/Red-black_tree
 * http://eternallyconfuzzled.com/tuts/datastructures/jsw_tut_rbtree.aspx
 * http://gauss.ececs.uc.edu/RedBlack/redblack.html
 * http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/
 * <p/>
 * rb tree is enhancement to the binary search tree.
 * <p/>
 * Average		Worst case
 * Space	O(n)		O(n)
 * Search	O(log n)	O(log n)
 * Insert	O(log n)	O(log n)
 * Delete	O(log n)	O(log n)
 * <p/>
 * CONSTRUCTION: with a negative infinity sentinel
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
public class RedBlackTree
{
	private RedBlackTreeNode header;
	private static RedBlackTreeNode nullNode;

	static
	// Static initializer for nullNode
	{
		nullNode = new RedBlackTreeNode( null );
		nullNode.left = nullNode.right = nullNode;
	}

	static final int BLACK = 1; // Black must be 1
	static final int RED = 0;

	// Used in insert routine and its helpers
	private static RedBlackTreeNode current;
	private static RedBlackTreeNode parent;
	private static RedBlackTreeNode grand;
	private static RedBlackTreeNode great;

	/**
	 * Construct the tree.
	 *
	 * @param negInf a value less than or equal to all others.
	 */
	public RedBlackTree( Comparable negInf )
	{
		header = new RedBlackTreeNode( negInf );
		header.left = header.right = nullNode;
	}

	/**
	 * Insert into the tree. Does nothing if item already present.
	 *
	 * @param item the item to insert.
	 */
	public void insert( Comparable item )
	{
		current = parent = grand = header;
		nullNode.data = item;

		while( current.data.compareTo( item ) != 0 )
		{
			great = grand;
			grand = parent;
			parent = current;
			current = item.compareTo( current.data ) < 0 ? current.left : current.right;

			// Check if two red children; fix if so
			if( current.left.color == RED && current.right.color == RED )
			{
				handleReorient( item );
			}
		}

		// Insertion fails if already present
		if( current != nullNode )
		{
			return;
		}
		current = new RedBlackTreeNode( item, nullNode, nullNode );

		// Attach to parent
		if( item.compareTo( parent.data ) < 0 )
		{
			parent.left = current;
		}
		else
		{
			parent.right = current;
		}
		handleReorient( item );
	}

	/**
	 * Remove from the tree. Not implemented in this version.
	 */
	public void remove( Comparable x )
	{
		System.out.println( "Remove is not implemented" );
	}

	/**
	 * Find the smallest item the tree.
	 */
	public Comparable findMin()
	{
		if( isEmpty() )
		{
			return null;
		}

		RedBlackTreeNode itr = header.right;

		while( itr.left != nullNode )
		{
			itr = itr.left;
		}

		return itr.data;
	}

	/**
	 * Find the largest item in the tree.
	 */
	public Comparable findMax()
	{
		if( isEmpty() )
		{
			return null;
		}

		RedBlackTreeNode itr = header.right;

		while( itr.right != nullNode )
		{
			itr = itr.right;
		}

		return itr.data;
	}

	/**
	 * Find an item in the tree.
	 */
	public Comparable find( Comparable x )
	{
		nullNode.data = x;
		current = header.right;

		for(; ; )
		{
			if( x.compareTo( current.data ) < 0 )
			{
				current = current.left;
			}
			else if( x.compareTo( current.data ) > 0 )
			{
				current = current.right;
			}
			else if( current != nullNode )
			{
				return current.data;
			}
			else
			{
				return null;
			}
		}
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty()
	{
		header.right = nullNode;
	}

	/**
	 * Test if the tree is logically empty.
	 */
	public boolean isEmpty()
	{
		return header.right == nullNode;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree()
	{
		if( isEmpty() )
		{
			System.out.println( "Empty tree" );
		}
		else
		{
			printTree( header.right );
		}
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 *
	 * @param t the node that roots the tree.
	 */
	private void printTree( RedBlackTreeNode t )
	{
		if( t != nullNode )
		{
			printTree( t.left );
			System.out.println( t.data );
			printTree( t.right );
		}
	}

	/**
	 * Internal routine that is called during an insertion if a node has two red
	 * children. Performs flip and rotations.
	 */
	private void handleReorient( Comparable item )
	{
		// Do the color flip
		current.color = RED;
		current.left.color = BLACK;
		current.right.color = BLACK;

		if( parent.color == RED ) // Have to rotate
		{
			grand.color = RED;
			if( ( item.compareTo( grand.data ) < 0 ) != ( item.compareTo( parent.data ) < 0 ) )
			{
				parent = rotate( item, grand ); // Start dbl rotate
			}
			current = rotate( item, great );
			current.color = BLACK;
		}
		header.right.color = BLACK; // Make root black
	}

	/**
	 * Internal routine that performs a single or double rotation. Because the
	 * result is attached to the parent, there are four cases. Called by
	 * handleReorient.
	 */
	private RedBlackTreeNode rotate( Comparable item, RedBlackTreeNode parent )
	{
		if( item.compareTo( parent.data ) < 0 )
		{
			return parent.left = item.compareTo( parent.left.data ) < 0 ? rotateWithLeftChild( parent.left ) : // LL
					rotateWithRightChild( parent.left ); // LR
		}
		else
		{
			return parent.right = item.compareTo( parent.right.data ) < 0 ? rotateWithLeftChild( parent.right ) : // RL
					rotateWithRightChild( parent.right ); // RR
		}
	}

	/**
	 * Rotate binary tree node with left child.
	 */
	static RedBlackTreeNode rotateWithLeftChild( RedBlackTreeNode k2 )
	{
		RedBlackTreeNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 */
	static RedBlackTreeNode rotateWithRightChild( RedBlackTreeNode k1 )
	{
		RedBlackTreeNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}
}