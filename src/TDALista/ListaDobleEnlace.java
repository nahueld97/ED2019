package TDALista;

import java.util.Iterator;

import exceptions.BoundaryViolationException;
import exceptions.InvalidPositionException;

public class ListaDobleEnlace<E> implements PositionList<E> 
{
	protected int size;
	protected NodoDoble<E> head;
	protected NodoDoble<E> tail;

	public ListaDobleEnlace()
	{
		size = 0;
		head = new NodoDoble<E>();
		tail = new NodoDoble<E>();
		tail.setPrev(head);
		head.setNext(tail);
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
		return head.getNext();
	}

	public Position<E> last() throws EmptyListException 
	{
		if (isEmpty())
			throw new EmptyListException("Lista Vacia");
		return tail.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
	{
		NodoDoble<E> pos = checkPosition(p);
			if (pos == tail.getPrev())
				throw new BoundaryViolationException("No existe siguiente al ultimo");
		return pos.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
	{
		NodoDoble<E> pos = checkPosition(p);
		if (pos == head.getNext())
			throw new BoundaryViolationException("No existe anterior al primero");
		return pos.getPrev();
	}

	public void addFirst(E element) 
	{
		NodoDoble<E> nuevo = new NodoDoble<E> (element,head.getNext(),head);
		nuevo.getNext().setPrev(nuevo);
		head.setNext(nuevo);
		size++;
	}

	public void addLast(E element) 
	{
		NodoDoble<E> nuevo = new NodoDoble<E> (element,tail,tail.getPrev());
		tail.getPrev().setNext(nuevo);
		tail.setPrev(nuevo);
		size++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException 
	{
		NodoDoble<E> pos = checkPosition(p);
		NodoDoble<E> nuevo = new NodoDoble<E> (element ,pos.getNext(),pos);
		pos.getNext().setPrev(nuevo);
		pos.setNext(nuevo);
		
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException 
	{
		NodoDoble<E> pos = checkPosition(p);
		NodoDoble<E> nuevo = new NodoDoble<E>(element, pos,pos.getPrev());
		pos.getPrev().setNext(nuevo);
		pos.setPrev(nuevo);
		size++;
	}

	public E remove(Position<E> p) throws InvalidPositionException 
	{
		NodoDoble<E> pos = checkPosition(p);
		E aux = pos.element();
		pos.getNext().setPrev(pos.getPrev());
		pos.getPrev().setNext(pos.getNext());
		pos.setNext(null);
		pos.setPrev(null);
		size--;
		return aux;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException 
	{
		NodoDoble<E> pos = checkPosition(p);
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
			NodoDoble<E> aux = head.getNext();
			while(aux != tail)
			{
				lista.addLast(aux);
				aux = aux.getNext();
			}
		}
		return lista;
	}
	
	private NodoDoble<E> checkPosition(Position<E> pos) throws InvalidPositionException
	{
		if (pos == null ||  isEmpty())
			throw new InvalidPositionException("Posicion Invalida");
		return (NodoDoble<E>)pos;
	}
}
