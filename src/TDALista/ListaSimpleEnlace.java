package TDALista;

import java.util.Iterator;

import exceptions.BoundaryViolationException;
import exceptions.InvalidPositionException;

public class ListaSimpleEnlace<E> implements PositionList<E>
{
	protected int size;
	protected NodoSimple<E> head;

	public ListaSimpleEnlace()
	{
		size = 0;
		head = null;
	}
	
	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size==0;
	}

	public Position<E> first() throws EmptyListException 
	{
		if(isEmpty())
			throw new EmptyListException("Lista Vacia");
		return head;
	}

	public Position<E> last() throws EmptyListException 
	{
		if (isEmpty())
			throw new EmptyListException("Lista Vacia");
		NodoSimple<E> aux = head;
		while(aux.getNext() != null)
		{
			aux = aux.getNext();
		}
		return aux;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
	{
		NodoSimple<E> pos = checkPosition(p);
		try 
		{
			if (pos == last())
				throw new BoundaryViolationException("No existe siguiente al ultimo");
		}
		catch (EmptyListException e)
		{
			throw new InvalidPositionException ("Lista  vacia");
		}
		return pos.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
	{
		NodoSimple<E> pos = checkPosition(p);
		if (pos == head)
			throw new BoundaryViolationException("No existe anterior al primero");
		NodoSimple<E> aux = head;
		while(aux.getNext() != pos)
		{
			aux = aux.getNext();
		}
		return aux;
	}

	public void addFirst(E element) 
	{
		NodoSimple<E> nuevo = new NodoSimple<E> (element);
		nuevo.setNext(head);
		size++;
		head = nuevo;
	}

	public void addLast(E element) 
	{
		NodoSimple<E> nuevo = new NodoSimple<E> (element);
		try
		{
			NodoSimple<E> ultimo = (NodoSimple<E>) last();
			ultimo.setNext(nuevo);
		}
		catch (EmptyListException e)
		{
			head = nuevo;
		}
		size++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException 
	{
		NodoSimple<E> pos = checkPosition(p);
		NodoSimple<E> nuevo = new NodoSimple<E> (element ,pos.getNext());
		pos.setNext(nuevo);
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException 
	{
		try
		{
			NodoSimple<E> pos = checkPosition(p);
			NodoSimple<E> nuevo = new NodoSimple<E>(element, pos);
			NodoSimple<E> anterior = (NodoSimple<E>) prev(pos);
			anterior.setNext(nuevo);
		}
		catch (BoundaryViolationException e)
		{
			addFirst(element);
		}
		size++;
	}

	public E remove(Position<E> p) throws InvalidPositionException 
	{
		NodoSimple<E> pos = checkPosition(p);
		E aux = pos.element();
		try
		{
			NodoSimple<E> anterior = (NodoSimple<E>) prev(pos);
			anterior.setNext(pos.getNext());
		}
		catch(BoundaryViolationException e)
		{
			head = pos.getNext();
		}
		pos.setNext(null);
		size--;
		return aux;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException 
	{
		NodoSimple<E> pos = checkPosition(p);
		E aux= pos.element();
		pos.setElement(element);
		return aux;
	}

	public Iterator<E> iterator() 
	{
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() 
	{
		PositionList<Position<E>> lista = new ListaSimpleEnlace<Position<E>>();
		if (!isEmpty())
		{
			NodoSimple<E> aux = head;
			while(aux != null)
			{
				lista.addLast(aux);
				aux = aux.getNext();
			}
		}
		return lista;
	}
	
	private NodoSimple<E> checkPosition(Position<E> pos) throws InvalidPositionException
	{
		if (pos == null ||  isEmpty())
			throw new InvalidPositionException("Posicion Invalida");
		return (NodoSimple<E>)pos;
	}
}
