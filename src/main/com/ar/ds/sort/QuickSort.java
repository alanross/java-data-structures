package com.ar.ds.sort;


/**
 * Quicksort:
 * A divide and conquer algorithm which relies on a partition operation
 * ( average Θ(n log(n)), worst case Θ(n2) )
 *
 * 1. Pick an element, called a pivot, from the list.
 * 2. Reorder the list so that all elements with values less than the pivot come before the pivot,
 * while all elements with values greater than the pivot come after it (equal values can go either way).
 * After this partitioning, the pivot is in its final position. This is called the partition operation.
 * 3. Recursively sort the sub-list of lesser elements and the sub-list of greater elements.
 *
 * Pro:
 * - Low memory requirement
 * - Typically faster than merge sort when the data is stored in memory
 * - More efficient at handling slow-to-access sequential media.
 * - Quicksort is space constant where Mergesort depends on the structure you're sorting.
 *
 * Cons:
 * - Θ(n2) in worst case
 * - Consistently poor choices of pivots can result in drastically slower O(n²) performance,
 * but if at each step we choose the median as the pivot then it works in O(n log n).
 *
 * http://en.wikipedia.org/wiki/Quicksort
 *
 * @author Alan Ross
 * @version 0.1
 */
public class QuickSort implements ISort
{
	public QuickSort()
	{

	}

	@Override
	public void sort( int[] array )
	{
		quicksort( array, 0, array.length - 1 );
	}

	private void quicksort( int[] array, int left, int right )
	{
		int i = left;
		int j = right;

		// Get the pivot element from the middle of the list
		int pivot = array[ left + ( right - left ) / 2 ];

		// Divide into two lists
		while( i <= j )
		{
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while( array[ i ] < pivot )
			{
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while( array[ j ] > pivot )
			{
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if( i <= j )
			{
				swap( array, i, j );
				i++;
				j--;
			}
		}

		if( left < j )
		{
			quicksort( array, left, j );
		}
		if( i < right )
		{
			quicksort( array, i, right );
		}
	}

	private void quicksortWithPartition( int[] array, int left, int right )
	{
		if( right > left ) // subarray of 0 or 1 elements already sorted
		{
			int pivotIndex = right / 2;

			// The partitioning part: select a pivotIndex in the range left ≤
			// pivotIndex ≤ right

			int pivotValue = array[ pivotIndex ];

			swap( array, pivotIndex, right ); // Move pivot to end

			int newPivotIndex = left;

			for( int i = left; i < right; ++i )
			{
				if( array[ i ] <= pivotValue )
				{
					swap( array, i, newPivotIndex );
					newPivotIndex++;
				}
			}

			swap( array, newPivotIndex, right ); // Move pivot to its final place

			// element at pivotNewIndex is now at its final position
			pivotIndex = newPivotIndex;

			// recursively sort elements on the left of pivotNewIndex
			quicksortWithPartition( array, left, pivotIndex - 1 );

			// recursively sort elements on the right of pivotNewIndex
			quicksortWithPartition( array, pivotIndex + 1, right );
		}
	}

	private void swap( int[] array, int i, int j )
	{
		int tmp = array[ i ];
		array[ i ] = array[ j ];
		array[ j ] = tmp;
	}


	@Override
	public String toString()
	{
		return "[QuickSort]";
	}
}
