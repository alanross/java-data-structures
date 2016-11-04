package com.ar.ds.atlas;

import com.ar.core.error.ElementDoesNotExistError;
import com.ar.core.error.NullError;
import com.ar.core.log.Context;
import com.ar.core.log.Log;
import com.ar.core.utils.StringUtils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Alan Ross
 * @version 0.1
 */
public abstract class Atlas
{
	private Hashtable<String, IAtlasElement> _dictionary;

	protected Atlas()
	{
		_dictionary = new Hashtable<String, IAtlasElement>();
	}

	protected Boolean addElement( IAtlasElement element )
	{
		String id = element.getId();

		if( StringUtils.isEmpty( id ) )
		{
			Log.warn( Context.DEFAULT, this, "Ignoring. Can not prepare element with empty id." );

			return false;
		}

		id = id.toLowerCase();

		if( hasElement( id ) )
		{
			Log.warn( Context.DEFAULT, this, "Ignoring. Element already exists." );

			return false;
		}

		_dictionary.put( id, element );

		return true;
	}

	protected Boolean removeElement( IAtlasElement element )
	{
		String id = element.getId();

		if( StringUtils.isEmpty( id ) )
		{
			Log.warn( Context.DEFAULT, this, "Ignoring. Can not remove element with empty id." );

			return false;
		}

		id = id.toLowerCase();

		if( !hasElement( id ) )
		{
			Log.warn( Context.DEFAULT, this, "Ignoring. Element does not exists." );

			return false;
		}

		_dictionary.remove( id );

		return true;
	}

	protected IAtlasElement getElement( String id )
	{
		if( StringUtils.isEmpty( id ) )
		{
			throw new NullError( this, "Can not retrieve element with empty id." );
		}

		id = id.toLowerCase();

		if( !hasElement( id ) )
		{
			throw new ElementDoesNotExistError( this );
		}

		return _dictionary.get( id );
	}

	protected boolean hasElement( IAtlasElement element )
	{
		return hasElement( element.getId() );
	}

	protected boolean hasElement( String id )
	{
		return ( _dictionary.get( id.toLowerCase() ) != null );
	}

	public LinkedList<IAtlasElement> getAllElements()
	{
		LinkedList<IAtlasElement> result = new LinkedList<IAtlasElement>();

		Iterator<IAtlasElement> it = _dictionary.values().iterator();

		while( it.hasNext() )
		{
			result.addLast( it.next() );
		}

		return result;
	}

	public String getAllElementsInfo()
	{
		StringBuilder result = new StringBuilder();
		result.append( "[\n" );

		Iterator<IAtlasElement> it = _dictionary.values().iterator();

		while( it.hasNext() )
		{
			result.append( "\t" + it.next() + "\n" );
		}

		result.append( "]" );
		return result.toString();
	}

	public void dispose()
	{
		Iterator<IAtlasElement> it = _dictionary.values().iterator();

		while( it.hasNext() )
		{
			removeElement( it.next() );
		}

		_dictionary = null;
	}

	@Override
	public String toString()
	{
		return "[Atlas]";
	}
}