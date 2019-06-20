package Cola;

public class NodoCola<E> implements Position<E>
{
	protected E data;
	protected NodoCola<E> next;
	
	public NodoCola()
	{
		data = null;
		next = null;
	}
	
	public NodoCola (E dato)
	{
		data = dato;
		next = null;
	}
	
	public NodoCola (E dato, NodoCola<E> sig)
	{
		data = dato;
		next = sig;
	}
	
	public E element() 
	{
		return data;
	}
	
	public NodoCola<E> getNext()
	{
		return next;
	}
	
	public void setElement(E elem)
	{
		data  = elem;
	}
	
	public void setNext(NodoCola<E> sig)
	{
		next = sig;
	}
}
