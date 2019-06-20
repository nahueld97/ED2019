package TDAArbol;

import TDALista.Position;
import TDALista.PositionList;

public class Tnodo<E> implements Position<E> {
	E element;
	Tnodo<E> parent;
	PositionList<Tnodo<E>> children;
	
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
