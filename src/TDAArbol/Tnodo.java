package TDAArbol;

import TDALista.ListaDobleEnlace;
import TDALista.Position;
import TDALista.PositionList;

public class Tnodo<E> implements Position<E> {
	E element;
	Tnodo<E> parent;
	PositionList<Tnodo<E>> children;

	public Tnodo(E element, Tnodo<E> parent) {
		this.element = element;
		this.parent = parent;
		this.children = new ListaDobleEnlace<Tnodo<E>>();
	}
	
	public Tnodo() {
		this(null,null);
	}
	
	public Tnodo(E element){
		this(element,null);
	}
	
	public E element() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public Tnodo<E> getParent(){
		return parent;
	}
	
	public void setParent(Tnodo<E> parent) {
		this.parent = parent;
	}
	
	public PositionList<Tnodo<E>> getChildren(){
		return children;
	}
}
