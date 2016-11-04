package com.ar.ds.sort;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class SelectionSort implements ISort
{
	public SelectionSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		// 2 Indices, Position, Minimum
		int i, j, pos, min;

		for( i = 0; i < array.length - 1; i++ )
		{
			pos = i;                               		// Index des bisher kleinsten
			min = array[ i ];                            // Wert des bisher kleinsten
			for( j = i + 1; j < array.length; j++ )      // durchlaufe Rest des Array
			{
				if( array[ j ] < min )					 // falls kleineres gefunden,
				{
					pos = j;                       		// merke Position des kleinsten
					min = array[ j ];                   // merke Wert des kleinsten
				}
			}

			array[ pos ] = array[ i ];                  // speichere bisher kleinstes um
			array[ i ] = min;                          // neues kleinstes nach vorne
		}
	}

	@Override
	public String toString()
	{
		return "[SelectionSort]";
	}
}
