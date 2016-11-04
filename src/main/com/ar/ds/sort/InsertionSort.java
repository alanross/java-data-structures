package com.ar.ds.sort;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class InsertionSort implements ISort
{
	public InsertionSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		int j; // the number of items sorted so far
		int key; // the item to be inserted
		int i;

		// Start with 1 (not 0)
		for( j = 1; j < array.length; j++ )
		{
			key = array[ j ];

			// Smaller values are moving down
			for( i = j - 1; ( i >= 0 ) && ( array[ i ] > key ); i-- )
			{
				array[ i + 1 ] = array[ i ];
			}

			// Put the key in its proper location
			array[ i + 1 ] = key;
		}
	}

	@Override
	public String toString()
	{
		return "[InsertionSort]";
	}
}
