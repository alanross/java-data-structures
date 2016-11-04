package com.ar.ds.sets;

/**
 * @author Alan Ross
 * @version 0.1
 */
public class Set
{
	/**
	 * If there are n objects than all 2^n subsets can be determined by
	 * iterating from 0 to 2*n and in each iteration by selecting the
	 * elements at the position having set bit in the counter. Like for given ex
	 * 111--{1,2,3}
	 * 110--{2,3}
	 * 101--{1,3}
	 * 100--{3}
	 * 011--{2,3}
	 * 010--{2}
	 * 001--{1}
	 * 000--{}
	 */
	public static String getAllSubsets( String[] source )
	{
		int n = source.length;
		int currentSubset = ( int ) Math.pow( 2.0, ( double ) n ) - 1; //2^n -1 to remove empty set
		int tmp;
		StringBuilder result = new StringBuilder(  );

		while( currentSubset > 0 )
		{
			result.append( "(" );

			tmp = currentSubset;

			for( int i = 0; i < n; ++i )
			{
				if( ( tmp & 1 ) != 0 )
				{
					result.append( source[ i ] );
				}

				tmp >>= 1;
			}

			result.append( ") " );

			currentSubset--;
		}

		return result.toString();
	}

	@Override
	public String toString()
	{
		return "[Set]";
	}
}