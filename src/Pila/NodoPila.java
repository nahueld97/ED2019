package Pila;

public class NodoPila <E> implements Position<E>
{
	protected E data;
	protected NodoPila<E> next;
	
	protected NodoPila(E dato)
	{
		data = dato;
		next = null;
	}
	
	public NodoPila()
	{
		data = null;
		next = null;
	}
	
	public NodoPila(E dato,NodoPila<E> sig)
	{
		data = dato;
		next = sig;
	}
	public E element() 
	{
		return data;
	}
	
	public NodoPila<E> getNet()
	{
		return next;
	}
	
	public void setElement(E elem)
	{
		data  = elem;
	}
	
	public void setNext(NodoPila<E> sig)
	{
		next = sig;
	}
}
