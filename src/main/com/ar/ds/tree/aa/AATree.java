package com.ar.ds.tree.aa;

/**
 * http://en.wikipedia.org/wiki/AA_tree
 * <p/>
 * - Self-balancing binary search trees
 * - a form of balanced tree used for storing and retrieving ordered data efficiently.
 * - a variation of the red-black tree
 * - Unlike red-black trees, red nodes on an AA tree can only be added as a right subchild.
 * In other words, no red node can be a left sub-child.
 * This simplifies the maintenance operations.
 * red-black tree maintenance algorithms need to consider seven different shapes to properly balance the tree, aa only two
 * <p/>
 * - Typically, AA trees are implemented with levels instead of colors, unlike red-black trees.
 * <p/>
 * void insert( x ) --> Insert x
 * void remove( x ) --> Remove x
 * Comparable find( x ) --> Return item that matches x
 * Comparable findMin( ) --> Return smallest item
 * Comparable findMax( ) --> Return largest item
 * boolean isEmpty( ) --> Return true if empty; else false
 * void empty( ) --> Remove all items
 * void printTree( ) --> Print tree in sorted order
 *
 * @author Mark Allen Weiss
 */
public class AATree
{
	/**
	 * Construct the tree.
	 */
	public AATree()
	{
		root = nullNode;
	}

	/**
	 * Insert into the tree. Does nothing if x is already present.
	 *
	 * @param x the item to insert.
	 */
	public void insert( Comparable x )
	{
		root = insert( x, root );
	}

	/**
	 * Remove from the tree. Does nothing if x is not found.
	 *
	 * @param x the item to remove.
	 */
	public void remove( Comparable x )
	{
		deletedNode = nullNode;
		root = remove( x, root );
	}

	/**
	 * Find the smallest item in the tree.
	 *
	 * @return the smallest item or null if empty.
	 */
	public Comparable findMin()
	{
		if( isEmpty() )
		{
			return null;
		}

		AATreeNode ptr = root;

		while( ptr.left != nullNode )
		{
			ptr = ptr.left;
		}

		return ptr.data;
	}

	/**
	 * Find the largest item in the tree.
	 *
	 * @return the largest item or null if empty.
	 */
	public Comparable findMax()
	{
		if( isEmpty() )
		{
			return null;
		}

		AATreeNode ptr = root;

		while( ptr.right != nullNode )
		{
			ptr = ptr.right;
		}

		return ptr.data;
	}

	/**
	 * Find an item in the tree.
	 *
	 * @param x the item to search for.
	 * @return the matching item of null if not found.
	 */
	public Comparable find( Comparable x )
	{
		AATreeNode current = root;
		nullNode.data = x;

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
		root = nullNode;
	}

	/**
	 * Test if the tree is logically empty.
	 *
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return root == nullNode;
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
			printTree( root );
		}
	}

	/**
	 * Internal method to insert into a subtree.
	 *
	 * @param x the item to insert.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	private AATreeNode insert( Comparable x, AATreeNode t )
	{
		if( t == nullNode )
		{
			t = new AATreeNode( x, nullNode, nullNode );
		}
		else if( x.compareTo( t.data ) < 0 )
		{
			t.left = insert( x, t.left );
		}
		else if( x.compareTo( t.data ) > 0 )
		{
			t.right = insert( x, t.right );
		}
		else
		{
			return t;
		}

		t = skew( t );
		t = split( t );
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 *
	 * @param x the item to remove.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	private AATreeNode remove( Comparable x, AATreeNode t )
	{
		if( t != nullNode )
		{
			// Step 1: Search down the tree and set lastNode and deletedNode
			lastNode = t;
			if( x.compareTo( t.data ) < 0 )
			{
				t.left = remove( x, t.left );
			}
			else
			{
				deletedNode = t;
				t.right = remove( x, t.right );
			}

			// Step 2: If at the bottom of the tree and
			// x is present, we remove it
			if( t == lastNode )
			{
				if( deletedNode == nullNode || x.compareTo( deletedNode.data ) != 0 )
				{
					return t; // Item not found; do nothing
				}
				deletedNode.data = t.data;
				t = t.right;
			}

			// Step 3: Otherwise, we are not at the bottom; rebalance
			else if( t.left.level < t.level - 1 || t.right.level < t.level - 1 )
			{
				if( t.right.level > --t.level )
				{
					t.right.level = t.level;
				}
				t = skew( t );
				t.right = skew( t.right );
				t.right.right = skew( t.right.right );
				t = split( t );
				t.right = split( t.right );
			}
		}
		return t;
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 *
	 * @param t the node that roots the tree.
	 */
	private void printTree( AATreeNode t )
	{
		if( t != t.left )
		{
			printTree( t.left );
			System.out.println( t.data.toString() );
			printTree( t.right );
		}
	}

	/**
	 * Skew primitive for AA-trees.
	 *
	 * @param t the node that roots the tree.
	 * @return the new root after the rotation.
	 */
	private AATreeNode skew( AATreeNode t )
	{
		if( t.left.level == t.level )
		{
			t = rotateWithLeftChild( t );
		}
		return t;
	}

	/**
	 * Split primitive for AA-trees.
	 *
	 * @param t the node that roots the tree.
	 * @return the new root after the rotation.
	 */
	private AATreeNode split( AATreeNode t )
	{
		if( t.right.right.level == t.level )
		{
			t = rotateWithRightChild( t );
			t.level++;
		}
		return t;
	}

	/**
	 * Rotate binary tree node with left child.
	 */
	static AATreeNode rotateWithLeftChild( AATreeNode k2 )
	{
		AATreeNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 */
	static AATreeNode rotateWithRightChild( AATreeNode k1 )
	{
		AATreeNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}

	private AATreeNode root;
	private static AATreeNode nullNode;

	static
	// static initializer for nullNode
	{
		nullNode = new AATreeNode( null );
		nullNode.left = nullNode.right = nullNode;
		nullNode.level = 0;
	}

	private static AATreeNode deletedNode;
	private static AATreeNode lastNode;

}