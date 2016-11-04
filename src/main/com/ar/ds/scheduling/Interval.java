package com.ar.ds.scheduling;

public final class Interval
{
	public int start;
	public int end;
	public int score;

	public Interval( int start, int end, int score )
	{
		this.start = start;
		this.end = end;
		this.score = score;
	}
}