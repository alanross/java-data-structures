package com.ar.ds.list;

/**
 * @author Alan Ross
 * @version 0.1
 */
public final class ListNode
{
	public ListNode next;
	public String data = new String();

	public ListNode()
	{
	}

	@Override
	public String toString()
	{
		return "[ListNode data:" + data + "]";
	}
}