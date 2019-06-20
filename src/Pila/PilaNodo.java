package Pila;

public class PilaNodo<E> implements Stack<E>
{
	protected int size;
	protected NodoPila<E> top;
	
	public PilaNodo()
	{
		size = 0;
		top = null;
	}
	
	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size==0;
	}

	public E top() throws EmptyStackException 
	{
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia");
		return top.element();
	}

	public void push(E element) 
	{
		if (isEmpty())
			top = new NodoPila<E>(element);
		else
		{
			NodoPila<E> nuevo = new NodoPila<E> (element,top);
			top = nuevo;
		}
		size++;
	}

	public E pop() throws EmptyStackException 
	{
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia");
		E aux = top.element();
		top = top.getNet();
		size--;
		return aux;
	}

}
