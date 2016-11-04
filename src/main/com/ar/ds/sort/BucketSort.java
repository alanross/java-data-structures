package com.ar.ds.sort;

/**
 * Sortieren durch Verteilen auf Buckets (Faecher).
 * 1.) Zaehlen der Haeufigkeiten b[i] einzelner Schluessel i;
 * 2.) Buckets durchlaufen und i-ten Schluessel b[i]-mal ausgeben.
 * <p/>
 * Runtime: O(n) + O(N)
 * bei n(=a.length) zu sortierenden Zeichen aus einem N-elementigen Alphabet
 *
 * @author Alan Ross
 * @version 0.1
 */
public class BucketSort implements ISort
{
	// Alphabet size
	private final int N = 256;

	public BucketSort()
	{
	}

	@Override
	public void sort( int[] array )
	{
		internalSort( array, array.length );
	}

	private void internalSort( int array[], int N )
	{
		if( N <= 0 )
		{
			return;
		}

		int min = array[ 0 ];
		int max = min;

		// Find the minimum and maximum
		for( int i = 1; i < N; i++ )
		{
			if( array[ i ] > max )
			{
				max = array[ i ];
			}
			else if( array[ i ] < min )
			{
				min = array[ i ];
			}
		}

		// Create buckets
		int bucket[] = new int[ max - min + 1 ];

		// "Fill" buckets
		for( int i = 0; i < N; i++ )
		{
			// by counting each datum
			bucket[ array[ i ] - min ]++;
		}

		int i = 0;

		// "Empty" buckets
		for( int b = 0; b < bucket.length; b++ )
		{
			// back into array
			for( int j = 0; j < bucket[ b ]; j++ )
			{
				// by creating one per count
				array[ i++ ] = b + min;
			}
		}
	}

	@Override
	public String toString()
	{
		return "[BucketSort]";
	}
}
