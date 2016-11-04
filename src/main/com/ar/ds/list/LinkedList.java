package com.ar.ds.list;

/**
 * @author Alan Ross
 * @version 0.1
 */
public final class LinkedList
{
	public static ListNode reverse( ListNode node )
	{
		ListNode prev, curr;
		prev = null;
		curr = node;

		while( curr != null )
		{
			node = node.next;
			curr.next = prev;
			prev = curr;
			curr = node;
		}

		return ( prev );
	}

	public static ListNode reverseRecursive( ListNode curr, ListNode prev )
	{
		if( curr == null )
		{
			return prev;
		}

		ListNode temp = curr.next;
		curr.next = prev;

		return reverseRecursive( temp, curr );
	}

	public static boolean hasCycle( ListNode node )
	{
		ListNode fast = node;
		ListNode slow = node;

		while( true )
		{
			if( fast == null || fast.next == null )
			{
				return false;
			}
			else if( fast == slow || fast.next == slow )
			{
				return true;
			}
			else
			{
				fast = fast.next.next;
				slow = slow.next;
			}
		}
	}

	public static ListNode search( ListNode node, String data )
	{
		ListNode n = node;

		while( n != null )
		{
			if( n.data.equals( data ) )
			{
				return n;
			}

			n = n.next;
		}

		return null;
	}

	public static String join( ListNode node, String separator )
	{
		StringBuilder result = new StringBuilder();

		ListNode n = node;

		while( n != null )
		{
			result.append( n.data );

			if( n.next != null )
			{
				result.append( separator );
			}

			n = n.next;
		}

		return result.toString();
	}

	public LinkedList()
	{
	}

	@Override
	public String toString()
	{
		return "[LinkedList]";
	}
}