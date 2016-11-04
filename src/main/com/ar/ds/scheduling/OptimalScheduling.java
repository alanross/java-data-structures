package com.ar.ds.scheduling;

import java.util.List;

public class OptimalScheduling
{
	private int _optimalScore = 0;

	/**
	 * OptimalScheduling(I)
	 * {
	 * While (I ̸= ∅) do
	 * {
	 * Accept the job j from I with the earliest completion date.
	 * Delete j, and any interval which intersects j from I.
	 * }
	 * }
	 *
	 * @param intervals
	 */
	public void schedule( List<Interval> intervals )
	{
		int n = intervals.size();

		if( n == 0 )
		{
			return;
		}

		int minEnd = Integer.MAX_VALUE;
		int index = 0;
		Interval p;

		while( --n > -1 )
		{
			p = intervals.get( n );

			if( minEnd > p.end )
			{
				minEnd = p.end;
				index = intervals.indexOf( p );
			}
		}

		p = intervals.get( index );
		System.out.println( "findOptimalScore: " + p.start + " - " + p.end );

		_optimalScore += p.score;

		n = intervals.size();

		while( --n > -1 )
		{
			p = intervals.get( n );

			if( minEnd >= p.start || minEnd >= p.end )
			{
				//System.out.println( "\t\t" + p.start + " - "+ p.end );
				intervals.remove( p );
			}
		}

		if( intervals.size() > 0 )
		{
			schedule( intervals );
		}
	}
}
