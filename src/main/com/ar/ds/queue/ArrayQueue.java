package com.ar.ds.queue;

/**
 * @author Alan Ross
 * @version 0.1
 */
public final class ArrayQueue
{
	private static final int DEFAULT_CAPACITY = 10;

	private Object[] _elements;
	private int _size;
	private int _front;
	private int _back;

	/**
	 * Construct the queue.
	 */
	public ArrayQueue()
	{
		_elements = new Object[ DEFAULT_CAPACITY ];

		empty();
	}

	/**
	 * @private
	 */
	private void growCapacity()
	{
		Object[] newArray = new Object[ _elements.length * 2 ];

		// Copy elements that are logically in the queue
		for( int i = 0; i < _size; i++, _front = increment( _front ) )
		{
			newArray[ i ] = _elements[ _front ];
		}

		_elements = newArray;
		_front = 0;
		_back = _size - 1;
	}

	/**
	 * @private
	 */
	private int increment( int index )
	{
		if( ++index == _elements.length )
		{
			index = 0;
		}

		return index;
	}

	/**
	 * Test if the queue is logically empty. Returns true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return _size == 0;
	}

	/**
	 * Make the queue logically empty.
	 */
	public void empty()
	{
		_size = 0;
		_front = 0;
		_back = -1;
	}

	/**
	 * Return and remove the first inserted item from the queue.
	 */
	public Object dequeue()
	{
		if( isEmpty() )
		{
			throw new UnderflowException( "ArrayQueue dequeue" );
		}

		_size--;

		Object returnValue = _elements[ _front ];

		_front = increment( _front );

		return returnValue;
	}

	/**
	 * Insert a new item into the queue.
	 */
	public void enqueue( Object obj )
	{
		if( _size == _elements.length )
		{
			growCapacity();
		}

		_back = increment( _back );
		_elements[ _back ] = obj;
		_size++;
	}

	public Object getFront()
	{
		if( isEmpty() )
		{
			throw new RuntimeException( "ArrayQueue getFront" );
		}

		return _elements[ _front ];
	}

	@Override
	public String toString()
	{
		return "[ArrayQueue]";
	}

	class UnderflowException extends RuntimeException
	{
		public UnderflowException( String message )
		{
			super( message );
		}
	}
}