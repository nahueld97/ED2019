package Diccionario;

import Mapeo.Entry;
import Mapeo.Entrada;
import Mapeo.InvalidKeyException;
import Lista.*;
import java.util.*;

public class Diccionario<K,V> implements Dictionary<K,V> 
{
	protected ListaDobleEnlace<Entry<K,V>> lista;
	
	public Diccionario()
	{
		lista = new ListaDobleEnlace<Entry<K,V>>();
	}

	public int size() 
	{
		return lista.size();
	}

	public boolean isEmpty() 
	{
		return lista.isEmpty();
	}

	public Entry<K, V> find(K key) throws InvalidKeyException 
	{
		checkKey(key);
		Entry<K,V> aux = null ;
		Entry<K,V> toReturn = null;
		Iterator<Entry<K,V>> it = lista.iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre)
		{
			aux = it.next();
			if(aux.getKey().equals(key))
			{
				encontre = true;
				toReturn = aux;
			}
		}
		return toReturn;
	}

	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException 
	{
		checkKey(key);
		Entry<K,V> aux = null;
		Iterator<Entry<K,V>> it = lista.iterator();
		ListaDobleEnlace<Entry<K,V>> list = new ListaDobleEnlace<Entry<K,V>>();
		while(it.hasNext())
		{
			aux = it.next();
			if(aux.getKey().equals(key))
			{
				list.addLast(aux);
			}
		}
		return list;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException 
	{
		checkKey(key);
		Entry<K,V> nuevo = new Entrada<K,V>(key,value);
		lista.addLast(nuevo);
		return nuevo;
	}

	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException 
	{
		checkEntry(e);
		Position<Entry<K,V>> aux = null;
		Entry<K,V> toReturn =null;
		Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre)
		{
			aux = it.next();
			if(aux.element().equals(e))
			{
				encontre = true;
				toReturn = aux.element();
				try
				{
					lista.remove(aux);
				}
				catch(InvalidPositionException h)
				{
					throw new InvalidEntryException("Entrada Invalida");
				}
			}
		}
		return toReturn;
		
	}

	public Iterable<Entry<K, V>> entries() 
	{
		return lista;
	}
	
	private void checkKey(K k) throws InvalidKeyException
	{
		if (k == null) 
			throw new InvalidKeyException("clave invalida");
	}
	
	private void checkEntry(Entry<K,V> e) throws InvalidEntryException
	{
		if(e == null)
			throw new InvalidEntryException("Entrada Invalida");
		try
		{
			checkKey(e.getKey());
		}
		catch(InvalidKeyException h)
		{
			throw new InvalidEntryException("Entrada Invalida");
		}
		Entry<K,V> aux = null;
		Iterator<Entry<K,V>> it = lista.iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre)
		{
			aux = it.next();
			if(aux.equals(e))
			{
				encontre = true;
			}
		}
		if(!encontre)
			throw new InvalidEntryException("Entrada Invalida");
	}
}
