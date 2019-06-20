package TDAPila;

import exceptions.EmptyStackException;

public class PilaArreglo<E> implements Stack<E>
{
	
	protected int size;
	protected E[] pila;
	
	public PilaArreglo()
	{
		size = 0;
		pila = (E[]) new Object[100];
	}

	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public E top() throws EmptyStackException 
	{
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia");
		return pila[size-1];
	}

	public void push(E element) 
	{
		if (size == pila.length)
		{
			resize();
		}
		pila[size]=element;
		size++;
	}

	public E pop() throws EmptyStackException 
	{
		if(isEmpty())
			throw new EmptyStackException("Pila Vacia");
		size--;
		E aux= pila[size];
		pila[size] = null;
		return aux;
	}
	private void resize()
	{
		E[] aux = (E[]) new Object[pila.length];
		for(int i =0;i<pila.length; i++)
		{
			aux[i] = pila[i];
		}
		pila = (E[]) new Object[size*2];
		for(int i = 0;i<aux.length;i++)
		{
			pila[i] = aux[i];
		}
	}

}
