package TDACola;

import exceptions.EmptyQueueException;

public class ArrayQueue<E> implements Queue<E>
{
	protected int size;
	protected int tail;
	protected int head;
	protected E[] arreglo;
	
	public ArrayQueue()
	{
		size = 0;
		tail = 0;
		head = 0;
		arreglo = (E[]) new Object[100];
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
		return arreglo[head];
	}

	public void enqueue(E element) 
	{
		if(isEmpty())
		{
			arreglo[tail] = element;
			tail = (tail+1) % arreglo.length;
		}
		else
		{
			if(head == tail)
				ReSize();
			arreglo[tail] = element;
			tail = (tail+1) % arreglo.length;
		}
		size++;
	}

	public E dequeue() throws EmptyQueueException 
	{
		if(isEmpty())
			throw new EmptyQueueException("Cola Vacia");
		E aux = arreglo[head];
		arreglo[head] = null;
		size--;
		head = (head +1) % arreglo.length;
		return aux;
	}
	
	private void ReSize()
	{
		E[] aux = (E[]) new Object[size];
		for (int i=0;i<arreglo.length;i++)
		{
			aux[i] = arreglo[head];
			head = (head +1) % arreglo.length;
		}
		arreglo = (E[]) new Object[size*2];
		for (int i=0;i<aux.length;i++)
		{
			arreglo[i] = aux[i];
		}
		head = 0;
		tail = size;
	}
}
