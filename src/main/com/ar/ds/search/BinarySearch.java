package com.ar.ds.search;

/**
 * @author Alan Ross
 * @version 0.1
 */
public final class BinarySearch
{
	public BinarySearch()
	{

	}

	public static int search( int[] array, int value )
	{
		return search( array, value, 0, array.length - 1 );
	}

	/**
	 * Worst case performance	O(log n)
	 * Best case performance	O(1)
	 * Average case performance	O(log n)
	 * Worst case space complexity
	 * <p/>
	 * Requires input array to be sorted!
	 */
	public static int search( int[] array, int value, int low, int high )
	{
		if( high < low )
		{
			return -1; // not found
		}

		int mid = low + ( high - low ) / 2;

		if( array[ mid ] > value )
		{
			return search( array, value, low, mid - 1 );
		}
		else if( array[ mid ] < value )
		{
			return search( array, value, mid + 1, high );
		}
		else
		{
			return mid; // found
		}
	}

	@Override
	public String toString()
	{
		return "[BinarySearch]";
	}
}