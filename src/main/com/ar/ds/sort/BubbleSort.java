package com.ar.ds.sort;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class BubbleSort implements ISort
{
	public BubbleSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		boolean unsorted = true;

		while( unsorted )
		{
			unsorted = false;

			for( int i = 0; i < array.length - 1; ++i )
			{
				if( array[ i ] > array[ i + 1 ] )
				{
					int tmp = array[ i ];
					array[ i ] = array[ i + 1 ];
					array[ i + 1 ] = tmp;

					unsorted = true;
				}
			}
		}
	}

	@Override
	public String toString()
	{
		return "[BubbleSort]";
	}
} 
