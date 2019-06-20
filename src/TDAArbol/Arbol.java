package TDAArbol;

import java.util.Iterator;

import TDALista.ElementIterator;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

}
