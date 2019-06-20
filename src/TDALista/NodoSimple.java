package TDALista;

public class NodoSimple <E> implements Position<E>
{
	protected E data;
	protected NodoSimple<E> next;
	
	public NodoSimple ()
	{
		data = null;
		next = null;
	}
	
	public NodoSimple(E dato)
	{
		data = dato;
		next = null;
	}
	
	public NodoSimple(E dato, NodoSimple<E> sig)
	{
		data = dato;
		next = sig;
	}
	
	public E element() 
	{
		return data;
	}
	
	public NodoSimple<E> getNext()
	{
		return next;
	}
	
	public void setElement(E element)
	{
		data = element;
	}
	
	public void setNext(NodoSimple<E> sig)
	{
		next = sig;
	}
}
