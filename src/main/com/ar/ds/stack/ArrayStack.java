package com.ar.ds.stack;

/**
 * @author Alan Ross
 * @version 0.1
 */
public final class ArrayStack
{
	private static final int DEFAULT_CAPACITY = 10;

	private Object[] _elements;
	private int _top;

	/**
	 * Construct the stack.
	 */
	public ArrayStack()
	{
		_elements = new Object[ DEFAULT_CAPACITY ];

		_top = -1;
	}

	/**
	 * @private
	 */
	private void growCapacity()
	{
		Object[] newArray = new Object[ _elements.length * 2 ];

		for( int i = 0; i < _elements.length; i++ )
		{
			newArray[ i ] = _elements[ i ];
		}

		_elements = newArray;
	}

	/**
	 * Test if the stack is logically empty. Returns true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return _top == -1;
	}

	/**
	 * Make the stack logically empty.
	 */
	public void empty()
	{
		_top = -1;
	}

	/**
	 * Get the most recently inserted item in the stack. Does not alter the stack.
	 */
	public Object top()
	{
		if( isEmpty() )
		{
			throw new UnderflowException( "ArrayStack top" );
		}

		return _elements[ _top ];
	}

	/**
	 * Return and remove the most recently inserted item from the stack.
	 */
	public Object pop()
	{
		if( isEmpty() )
		{
			throw new UnderflowException( "ArrayStack pop" );
		}

		return _elements[ _top-- ];
	}

	/**
	 * Insert a new item into the stack.
	 */
	public void push( Object obj )
	{
		if( _top + 1 == _elements.length )
		{
			growCapacity();
		}

		_elements[ ++_top ] = obj;
	}

	@Override
	public String toString()
	{
		return "[ArrayStack]";
	}

	class UnderflowException extends RuntimeException
	{
		public UnderflowException( String message )
		{
			super( message );
		}
	}
}