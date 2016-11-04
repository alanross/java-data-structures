package com.ar.ds.sort;

/**
 * Iteratives Sortieren mit Heapsort
 * Entnimm einem Heap so lange das kleinste Element, bis er leer ist.
 * Die entnommenen Elemente werden im selben Array gespeichert.
 *
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Heapsort#Java_2
 *
 * @author Alan Ross
 * @version 0.1
 */
public class HeapSort implements ISort
{
	public HeapSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		generateMaxHeap( array );

		// sort
		for( int i = array.length - 1; i > 0; i-- )
		{
			swap( array, i, 0 );
			burry( array, 0, i );
		}
	}

	/**
	 * Create MaxHeap Tree in Array
	 */
	private void generateMaxHeap( int[] array )
	{
		// start in the middle, go backwards
		for( int i = ( array.length / 2 ) - 1; i >= 0; i-- )
		{
			burry( array, i, array.length );
		}
	}

	private void burry( int[] array, int i, int n )
	{
		while( i <= ( n / 2 ) - 1 )
		{
			int kindIndex = ( ( i + 1 ) * 2 ) - 1; // berechnet den Index des linken kind

			//bestimme ob ein rechtes Kind existiert
			if( kindIndex + 1 <= n - 1 )
			{
				//rechtes kind existiert
				if( array[ kindIndex ] < array[ kindIndex + 1 ] )
				{
					kindIndex++; // wenn rechtes kind größer ist nimm das
				}
			}

			//teste ob element sinken muss
			if( array[ i ] < array[ kindIndex ] )
			{
				swap( array, i, kindIndex ); //element versenken
				i = kindIndex; // wiederhole den vorgang mit der neuen position
			}
			else
			{
				break;
			}
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
		return "[HeapSort]";
	}
}
