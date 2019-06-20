package Lista;

public class NodoDoble <E> implements Position<E>
{
	protected E data;
	protected NodoDoble<E> next;
	protected NodoDoble<E> prev;
	
	public NodoDoble ()
	{
		data = null;
		next = null;
		prev = null;
	}
	
	public NodoDoble(E dato)
	{
		data = dato;
		next = null;
		prev = null;
	}
	
	public NodoDoble(E dato, NodoDoble<E> sig, NodoDoble<E> ant)
	{
		data = dato;
		next = sig;
		prev = ant;
	}
	
	public E element() 
	{
		return data;
	}
	
	public NodoDoble<E> getPrev()
	{
		return prev;
	}
	
	public NodoDoble<E> getNext()
	{
		return next;
	}
	
	public void setElement(E element)
	{
		data = element;
	}
	
	public void setPrev(NodoDoble<E> ant)
	{
		prev = ant;
	}
	
	public void setNext(NodoDoble<E> sig)
	{
		next = sig;
	}
}
