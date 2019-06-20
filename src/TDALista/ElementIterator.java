package TDALista;

import java.util.Iterator;

import exceptions.BoundaryViolationException;
import exceptions.InvalidPositionException;

public class ElementIterator<E> implements Iterator<E> 
{
	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	public ElementIterator(PositionList<E> l)
	{
		lista = l;
		try
		{
			cursor = lista.first();
		}
		catch (EmptyListException e)
		{
			cursor = null;
		}
	}
	
	public boolean hasNext() 
	{
		return cursor != null;
	}
	
	public E next() 
	{
		E aux;
		if(cursor != null)
		{
			try
			{
			aux = cursor.element();
			if (cursor == lista.last())
				cursor = null;
			else
				cursor = lista.next(cursor);
			}
			catch (EmptyListException | BoundaryViolationException| InvalidPositionException e)
			{
				aux = null;
			}
		}
		else
			aux = null;
		return aux;
	}
	
	
}
