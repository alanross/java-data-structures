package com.ar.ds.tree.binary;

import java.util.LinkedList;

/**
 * http://en.wikipedia.org/wiki/Binary_search_tree
 * http://cslibrary.stanford.edu/110/BinaryTrees.html
 * http://www.topcoder.com/tc?module=Static&d1=tutorials&d2=graphsDataStrucs2
 * http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/BinaryTree.java
 * <p/>
 * Average		Worst case
 * Space	O(n)		O(n)
 * Search	O(log n)	O(log n)
 * Insert	O(log n)	O(log n)
 * Delete	O(log n)	O(log n)
 * <p/>
 * Usages
 * Manipulate hierarchical data
 * Make information easy to search (see tree traversal)
 * Manipulate sorted lists of data
 * As a workflow for compositing digital images for visual effects
 * Router algorithms
 *
 * @author Alan Ross
 * @version 0.1
 */
public class BinaryTree
{
	public BinaryTree()
	{
	}

	public void insert( BinaryTreeNode node, int value )
	{
		if( value < node.value )
		{
			if( node.left != null )
			{
				insert( node.left, value );
			}
			else
			{
				System.out.println( "\tInserted " + value + " to left of node " + node.value );
				node.left = new BinaryTreeNode( value );
			}
		}
		else if( value > node.value )
		{
			if( node.right != null )
			{
				insert( node.right, value );
			}
			else
			{
				System.out.println( "\tInserted " + value + " to right of node " + node.value );
				node.right = new BinaryTreeNode( value );
			}
		}
		//ignore values that already are present in tree
	}

	/**
	 * Internal method to remove from a subtree.
	 *
	 * @param value the item to remove.
	 * @param node  the node that roots the tree.
	 * @return the new root.
	 */
	public BinaryTreeNode remove( int value, BinaryTreeNode node )
	{
		if( node == null )
		{
			return node;   // Item not found; do nothing
		}
		if( value < node.value )
		{
			node.left = remove( value, node.left );
		}
		else if( value > node.value )
		{
			node.right = remove( value, node.right );
		}
		else if( node.left != null && node.right != null ) // Two children
		{
			node.value = findMin( node.right ).value;
			node.right = remove( node.value, node.right );
		}
		else
		{
			node = ( node.left != null ) ? node.left : node.right;
		}
		return node;
	}

	// Each parent node is traversed before its children is called
	public void preorder( BinaryTreeNode node )
	{
		if( node != null )
		{
			System.out.println( "\tTraversed " + node.value );
			preorder( node.left );
			preorder( node.right );
		}
	}

	//  node's left subtree, then the node itself, and then its right subtree are traversed
	public void inorder( BinaryTreeNode node )
	{
		if( node != null )
		{
			inorder( node.left );
			System.out.println( "\tTraversed " + node.value );
			inorder( node.right );
		}
	}

	//children are traversed before their respective parents are traversed
	public void postorder( BinaryTreeNode node )
	{
		if( node != null )
		{
			postorder( node.left );
			postorder( node.right );
			System.out.println( "\tTraversed " + node.value );
		}
	}

	//print all nodes of level, then all of next..
	public void levelorder( BinaryTreeNode node )
	{
		if( node == null )
		{
			return;
		}

		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		list.add( node );

		while( list.size() > 0 )
		{
			BinaryTreeNode tmp = list.remove();
			System.out.println( "\tTraversed " + tmp.value );

			if( tmp.left != null )
			{
				list.add( tmp.left );
			}
			if( tmp.right != null )
			{
				list.add( tmp.right );
			}
		}
	}

	public int nodeDepth( BinaryTreeNode node, int value, int level )
	{
		if( node == null )
		{
			return 0;
		}

		if( node.value == value )
		{
			return level;
		}

		return
				nodeDepth( node.left, value, level + 1 ) |
						nodeDepth( node.right, value, level + 1 );
	}


	public int getSize( BinaryTreeNode node )
	{
		if( node == null )
		{
			return ( 0 );
		}
		else
		{
			return ( getSize( node.left ) + 1 + getSize( node.right ) );
		}
	}

	public int maxDepth( BinaryTreeNode node )
	{
		if( node == null )
		{
			return ( 0 );
		}
		else
		{
			int lDepth = maxDepth( node.left );
			int rDepth = maxDepth( node.right );

			// use the larger + 1
			return ( Math.max( lDepth, rDepth ) + 1 );
		}
	}

	// just follow left most node to bottom, return its value
	public BinaryTreeNode findMin( BinaryTreeNode node )
	{
		if( node == null )
		{
			return null;
		}
		else if( node.left == null )
		{
			return node;
		}
		return findMin( node.left );
	}

	public BinaryTreeNode findMax( BinaryTreeNode node )
	{
		if( node != null )
		{
			while( node.right != null )
			{
				node = node.right;
			}
		}

		return node;
	}

	/*
	 * Given a binary tree, return true if a node with the target data is found
	 * in the tree. Recurs down the tree, chooses the left or right branch by
	 * comparing the target to each node.
	 */
	public boolean find( BinaryTreeNode node, int value )
	{
		// 1. Base case == empty tree
		// in that case, the target is not found so return false
		if( node == null )
		{
			return true;
		}
		else
		{
			// 2. see if found here
			if( value == node.value )
			{
				return true;
			}
			else
			{
				// 3. otherwise recur down the correct subtree
				if( value < node.value )
				{
					return ( find( node.left, value ) );
				}
				else
				{
					return ( find( node.right, value ) );
				}
			}
		}
	}

	public void mirror( BinaryTreeNode node )
	{
		if( node != null )
		{
			// do the sub-trees
			mirror( node.left );
			mirror( node.right );

			// swap the left/right pointers
			BinaryTreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}

	/**
	 * Assume a TreeNode containing integers. LinkedList used as the queue for
	 * storing children on the same level.
	 * <p/>
	 * It differs from the depth-first search in that it uses a queue to perform the search,
	 * so the order in which the nodes are visited is quite different. It has the extremely
	 * useful property that if all of the edges in a graph are unweighted (or the same weight)
	 * then the first time a node is visited is the shortest path to that node from the source node
	 * <p/>
	 * http://www.topcoder.com/tc?module=Static&d1=tutorials&d2=graphsDataStrucs2
	 */
	public boolean breadthFirstSearch( BinaryTreeNode root, int target )
	{
		if( root == null )
		{
			return false;
		}

		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		BinaryTreeNode tmp;
		list.add( root );

		while( list.size() > 0 )
		{
			tmp = list.remove();

			if( tmp.value == target )
			{
				return true;
			}
			if( tmp.left != null )
			{
				list.add( tmp.left );
			}
			if( tmp.right != null )
			{
				list.add( tmp.right );
			}
		}

		return false;
	}

	/**
	 * The depth first search is well geared towards problems where we want to find
	 * any solution to the problem (not necessarily the shortest path),
	 * or to visit all of the nodes in the graph
	 * http://www.topcoder.com/tc?module=Static&d1=tutorials&d2=graphsDataStrucs2
	 */
	public boolean depthFirstSearch( BinaryTreeNode node, int target )
	{
		if( node == null )
		{
			return false;
		}
		if( node.value == target )
		{
			return true;
		}
		return depthFirstSearch( node.left, target ) || depthFirstSearch( node.right, target );
	}

	public boolean binarySearch( BinaryTreeNode root, BinaryTreeNode target )
	{
		BinaryTreeNode current = root;

		while( current != null )
		{
			if( current.value > target.value )
			{
				current = current.left;
			}
			else if( current.value < target.value )
			{
				current = current.right;
			}
			else
			{
				return true;
			}
		}

		return false;
	}

	public BinaryTreeNode lowestCommonAncestor( BinaryTreeNode root, BinaryTreeNode one, BinaryTreeNode two )
	{
		// Check if one and two are in the root tree.
		while( root != null )
		{
			if( root.value < one.value && root.value < two.value )
			{
				root = root.right;
			}
			else if( root.value > one.value && root.value > two.value )
			{
				root = root.left;
			}
			else
			{
				return root;
			}
		}

		return null;
	}

	@Override
	public String toString()
	{
		return "[BinaryTree]";
	}
}