package com.ar.ds.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OptimalWeightedScheduling implements Comparator<Interval>
{
	/**
	 * 1. Let the requests be sorted according to finish time, i.e., i < j implies fi ≤ fj
	 * 2. Define p(j) to be the largest i (less than j) such that job i and job j are not in conflict
	 * @param intervals
	 */
	/**
	 * 1. Sort jobs in ascending order of starting time.
	 * 2. For1≤i≤n,findthesmallestj>isuchthatjobjdoesn’toverlapwithjobi.
	 * 3. For i = n down to 1, compute value(i) using Equation (3.1.1) and store it in memory.
	 * 4. The weight of the optimum schedule is value(1).
	 * <p/>
	 * runs O(n log n).
	 */
	public void schedule( List<Interval> intervals )
	{
		//Sort intervals so 'end' value is ascending 
		Collections.sort( intervals, this );

		List<Interval> optimalIntervals = new ArrayList<Interval>();

		Interval interval;
		int n = intervals.size();

		//remember results
		boolean[] memoizeIntervals = new boolean[ n ];
		int[] memoizeScore = new int[ n ];
		memoizeScore[ 0 ] = 0;

		// find intervals that have highest score and fit 
		for( int i = 1; i < n; ++i )
		{
			interval = intervals.get( i );
			int score = interval.score;
			int npi = getIndexOfNextPossibleInterval( intervals, i );

			memoizeScore[ i ] = getMax( score + memoizeScore[ npi ], memoizeScore[ i - 1 ] );
			//true if interval is to be kept for optimal score
			memoizeIntervals[ i ] = ( score + memoizeScore[ npi ] > memoizeScore[ i - 1 ] );
		}

		//Add scores to accumulated score
		int optimalScore = 0;

		while( --n > -1 )
		{
			if( memoizeIntervals[ n ] )
			{
				interval = intervals.get( n );
				optimalScore += interval.score;
				optimalIntervals.add( interval );
			}
		}

		System.out.println( optimalScore );
	}

	/**
	 * Return the larger of both values.
	 */
	private int getMax( int val1, int val2 )
	{
		return ( val1 > val2 ) ? val1 : val2;
	}

	/**
	 * Returns largest possible index of interval that would
	 * fit in front of given interval index without conflict.
	 */
	private int getIndexOfNextPossibleInterval( List<Interval> intervals, int intervalIndex )
	{
		int diff = Integer.MAX_VALUE;
		int result = 0;
		int n = intervals.size();

		Interval i = intervals.get( intervalIndex );
		Interval j = null;

		for( int index = 0; index < n; ++index )
		{
			j = intervals.get( index );

			if( j.end < i.start )
			{
				if( diff > ( i.start - j.end ) )
				{
					diff = i.start - j.end;
					result = index;
				}
			}
		}

		return result;
	}

	@Override
	public int compare( Interval o1, Interval o2 )
	{
		if( o1.end < o2.end )
		{
			return -1;
		}
		if( o1.end > o2.end )
		{
			return 1;
		}
		return 0;
	}
}
