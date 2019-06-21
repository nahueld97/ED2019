package TDAArbol;

import java.util.Iterator;

import TDALista.ElementIterator;
import TDALista.EmptyListException;
import TDALista.ListaDobleEnlace;
import TDALista.Position;
import TDALista.PositionList;
import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;
import exceptions.InvalidOperationException;
import exceptions.InvalidPositionException;

public class Arbol<E> implements Tree<E> {

	private Tnodo<E> root;
	int size;
	
	public Arbol() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		PositionList<E> toreturn = new ListaDobleEnlace<E>();
		if(!isEmpty()) {
			preorden(root , toreturn);
		}
		return new ElementIterator<E>(toreturn);
	}

	private void preorden(Tnodo<E> pos, PositionList<E> l) {
		l.addLast(pos.element());
		for(Tnodo<E> n : pos.getChildren()) {
			preorden(n,l);
		}
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toreturn = new ListaDobleEnlace<Position<E>>();
		if(!isEmpty()) {
			preordenP(root , toreturn);
		}
		return toreturn;
	}
	
	private void preordenP(Tnodo<E> pos, PositionList<Position<E>> l) {
		l.addLast(pos);
		for(Tnodo<E> n : pos.getChildren()) {
			preordenP(n,l);
		}
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(v);
		E toRet = v.element();
		posicion.setElement(e);
		return toRet;
	}

	private Tnodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if(isEmpty()) throw new InvalidPositionException("Posicion invalida (Arbol vacio)");
		if(v == null) throw new InvalidPositionException("Posicion invalida (nula)");
		try {
		return (Tnodo<E>) v;
		}catch (ClassCastException e) {
			e.printStackTrace();
			throw new InvalidPositionException("Posicion invalida");
		}
	}

	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) throw new EmptyTreeException("no se puede pedir la raiz a un arbol vacio");
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		Tnodo<E> posicion = checkPosition(v);
		if (posicion == root) throw new BoundaryViolationException("padre de la raiz");
		
		return posicion.getParent();
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(v);
		PositionList<Position<E>> toRet = new ListaDobleEnlace<Position<E>>();
		for (Tnodo<E> h : posicion.getChildren()) {
			toRet.addLast(h);
		}
		return toRet;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return !isExternal(v);
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(v);
		return posicion.getChildren().isEmpty();
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(v);

		return posicion == root;
	}

	public void createRoot(E e) throws InvalidOperationException {
		if (!isEmpty()) throw new InvalidOperationException("este arbol ya posee una raiz");
		Tnodo<E> nuevo = new Tnodo<E>(e);
		root = nuevo;
		size++;
	}

	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(p);
		Tnodo<E> nuevo = new Tnodo<E>(e,posicion);
		posicion.getChildren().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(p);
		Tnodo<E> nuevo = new Tnodo<E>(e,posicion);
		posicion.getChildren().addLast(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		Tnodo<E> padre = checkPosition(p);
		Tnodo<E> hermanoD = checkPosition(rb);
		Tnodo<E> nuevo = new Tnodo<E>(e,padre);
		
		Iterator<Position<Tnodo<E>>> hijos = padre.getChildren().positions().iterator();
		Position<Tnodo<E>> cursor = null;
		boolean encontre = false;
		while (hijos.hasNext() && !encontre) {
			cursor = hijos.next();
			if (cursor.element() == hermanoD) {
				encontre=true;
				padre.getChildren().addBefore(cursor, nuevo);
				size++;
			}
		}
		if (!encontre) throw new InvalidPositionException("las posiciones dadas no son padre e hijo");
		
		return nuevo;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		Tnodo<E> padre = checkPosition(p);
		Tnodo<E> hermanoI = checkPosition(lb);
		Tnodo<E> nuevo = new Tnodo<E>(e,padre);
		
		Iterator<Position<Tnodo<E>>> hijos = padre.getChildren().positions().iterator();
		Position<Tnodo<E>> cursor = null;
		boolean encontre = false;
		while (hijos.hasNext() && !encontre) {
			cursor = hijos.next();
			if (cursor.element() == hermanoI) {
				encontre=true;
				padre.getChildren().addAfter(cursor, nuevo);
				size++;
			}
		}
		if (!encontre) throw new InvalidPositionException("las posiciones dadas no son padre e hijo");
		
		return nuevo;
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(p);
		if(isInternal(p)) throw new InvalidPositionException("nodo interno");
		if(!isRoot(p)) {
			Tnodo<E> padre = posicion.getParent();

			Iterator<Position<Tnodo<E>>> hijos = padre.getChildren().positions().iterator();
			Position<Tnodo<E>> cursor = null;
			boolean encontre = false;
			while (hijos.hasNext() && !encontre) {
				cursor = hijos.next();
				if (cursor.element() == posicion) {
					encontre=true;
					padre.getChildren().remove(cursor);
					size--;
				}
			}
			if (!encontre) throw new InvalidPositionException("arbol corrupto");

		}else {
			root=null;
			size--;
		}
		posicion.setParent(null);
		posicion.setElement(null);
	}

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> posicion = checkPosition(p);
		if(isExternal(p)) throw new InvalidPositionException("nodo externo");
		
		if (isRoot(p)) {
			if(posicion.getChildren().size()>1) {
				throw new InvalidPositionException("se intenta borrar la raiz con mas de un hijo");
			}
			try {
				Position<Tnodo<E>> hijo = posicion.getChildren().first();
				root = hijo.element();
				posicion.getChildren().remove(hijo);
				root.setParent(null);
				posicion.setElement(null);
			} catch (EmptyListException e) {
				e.printStackTrace();
				throw new InvalidPositionException("arbol corrupto");
			}
		}else {
			try {
				Position<Tnodo<E>> hijo = posicion.getChildren().first();
				posicion.setElement(hijo.element().element());
				for(Tnodo<E> h: hijo.element().getChildren()) {
					h.setParent(posicion);
					posicion.getChildren().addBefore(hijo, h);
				}
				posicion.getChildren().remove(hijo);
			} catch (EmptyListException e) {
				e.printStackTrace();
				throw new InvalidPositionException("arbol corrupto");
			}
		}
		size--;
	}

	public void removeNode(Position<E> p) throws InvalidPositionException {
		checkPosition(p);
		if(isExternal(p)) {
			removeExternalNode(p);
		}else {
			removeInternalNode(p);
		}
	}

}
