package TDACola;

import exceptions.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E>
{
	protected int size;
	protected NodoCola<E> head;
	protected NodoCola<E> tail;
	
	public ColaEnlazada()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public E front() throws EmptyQueueException 
	{
		if (isEmpty())
			throw new EmptyQueueException("Cola Vacia");
		return head.element();
	}

	public void enqueue(E element) 
	{
		NodoCola<E> nuevo = new NodoCola<E> (element);
		if(isEmpty())
		{
			head = nuevo;
			tail = nuevo;
		}
		else
		{
			tail.setNext(nuevo);
			tail = nuevo;
		}
		size++;
	}

	public E dequeue() throws EmptyQueueException 
	{
		if (isEmpty())
			throw new EmptyQueueException("Cola Vacia");
		size--;
		E aux = head.element();
		head = head.getNext();
		return aux;
	}

}
