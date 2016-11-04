package com.ar.ds.sort;

/**
 * Merge Sort:
 * is an ( best, average, worst Θ(n log(n) ) ) comparison-based sorting algorithm.
 * Most implementations produce a stable sort, meaning that the implementation preserves
 * the input order of equal elements in the sorted output. It is a divide and conquer algorithm.
 *
 * 1. If the list is of length 0 or 1, then it is already sorted. Otherwise:
 * 2. Divide the unsorted list into two sublists of about half the size.
 * 3. Sort each sublist recursively by re-applying the merge sort.
 * 4. Merge the two sublists back into one sorted list.
 *
 * Pro:
 * - When the data set is huge and is stored on external devices such as a hard drive, merge sort is faster
 * - Minimizes expensive reads of the external drive
 * - Lends itself well to parallel computing
 * - Often best choice for sorting linked list (which then requires only Θ(1) extra space)
 * - Makes fewer comparisons than quicksort
 * - when it's extremely important that your algorithm run faster than O(n^2)
 * - when you need either a stable sort (elements that compare equal are not rearranged)
 * - using sequential (rather than random-access) "memory"
 * ( data as it is received from a network connection, or sorting data structures which don't allow efficient random access like linked lists )
 * Cons:
 *
 * http://en.wikipedia.org/wiki/Merge_sort
 * http://en.literateprograms.org/Merge_sort_(Java)
 *
 * @author Alan Ross
 * @version 0.1
 */
public final class MergeSort implements ISort
{
	public MergeSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		internalSort( array, new int[ array.length ], 0, array.length - 1 );
	}

	private void internalSort( int[] array, int[] tmpArray, int left, int right )
	{
		if( left < right )
		{
			int center = ( left + right ) / 2;

			internalSort( array, tmpArray, left, center );
			internalSort( array, tmpArray, center + 1, right );

			int leftPos = left;
			int rightPos = center + 1;
			int rightEnd = right;

			int leftEnd = rightPos - 1;
			int tmpPos = leftPos;
			int numElements = rightEnd - leftPos + 1;

			// Main merge loop
			while( leftPos <= leftEnd && rightPos <= rightEnd )
			{
				if( array[ leftPos ] <= array[ rightPos ] )
				{
					tmpArray[ tmpPos++ ] = array[ leftPos++ ];
				}
				else
				{
					tmpArray[ tmpPos++ ] = array[ rightPos++ ];
				}
			}

			while( leftPos <= leftEnd )
			// Copy rest of first half
			{
				tmpArray[ tmpPos++ ] = array[ leftPos++ ];
			}

			while( rightPos <= rightEnd )
			// Copy rest of right half
			{
				tmpArray[ tmpPos++ ] = array[ rightPos++ ];
			}

			// Copy tmpArray back
			for( int i = 0; i < numElements; i++, rightEnd-- )
			{
				array[ rightEnd ] = tmpArray[ rightEnd ];
			}
		}
	}

	@Override
	public String toString()
	{
		return "[MergeSort]";
	}
}