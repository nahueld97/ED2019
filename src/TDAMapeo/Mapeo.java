package TDAMapeo;
import exceptions.InvalidKeyException;
import exceptions.InvalidPositionException;

import java.util.*;

import TDALista.*;

public class Mapeo<K,V>  implements Map<K,V>
{
	protected ListaDobleEnlace<Entry<K,V>> lista;
	
	public Mapeo() 
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

	public V get(K key) throws InvalidKeyException 
	{
		if(checkKey(key))
			throw new InvalidKeyException("clave invalida");
		V v = null;
		if(!isEmpty())
		{
			Iterator<Entry<K,V>> it = lista.iterator();
			Entrada<K,V> aux;
			boolean encontre = false;
			while(it.hasNext() && !encontre)
			{
				aux = (Entrada<K,V>)it.next();
				if(aux.getKey() == key)
				{
					encontre = true;
					v = aux.getValue();
				}
			}
		}
		return v;
	}

	public V put(K key, V value) throws InvalidKeyException 
	{
		if (checkKey(key))
			throw new InvalidKeyException("clave invalida");
		V v = null;
			Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
			Position<Entry<K,V>> aux;
			boolean encontre = false;
			while (it.hasNext() && !encontre)
			{
				aux = it.next();
				if (aux.element().getKey() == key)
				{
					v = aux.element().getValue();
					encontre= true;
					try
					{
					lista.set(aux, new Entrada<K,V>(key,value));
					}
					catch(InvalidPositionException e)
					{
						throw new InvalidKeyException("Posicion Invalida");
					}
				}
			}
			if(!encontre)
				lista.addLast(new Entrada<K,V>(key,value));
		return v;
	}

	public V remove(K key) throws InvalidKeyException 
	{
		if (checkKey(key))
			throw new InvalidKeyException("clave invalida");
		V v = null;
		if(!isEmpty())
		{
			Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
			Position<Entry<K,V>> aux;
			boolean encontre = false;
			while (it.hasNext() && !encontre)
			{
				aux = it.next();
				if (aux.element().getKey() == key)
				{
					v = aux.element().getValue();
					encontre= true;
					try
					{
					lista.remove(aux);
					}
					catch(InvalidPositionException e)
					{
						throw new InvalidKeyException("Posicion Invalida");
					}
				}
			}
		}
		return v;
	}

	public Iterable<K> keys() 
	{
		ListaDobleEnlace<K> list = new ListaDobleEnlace<K>();
		Iterator<Entry<K,V>> it = lista.iterator();
		Entry<K,V> aux;
		while (it.hasNext())
		{
			aux = it.next();
			list.addLast(aux.getKey());
		}
		return list;
	}

	public Iterable<V> values() 
	{
		ListaDobleEnlace<V> list = new ListaDobleEnlace<V>();
		Iterator<Entry<K,V>> it = lista.iterator();
		Entry<K,V> aux;
		while (it.hasNext())
		{
			aux = it.next();
			list.addLast(aux.getValue());
		}
		return list;
	}

	public Iterable<Entry<K, V>> entries() 
	{
		return lista;
	}
	
	private boolean checkKey(K k)
	{
		return k == null;
	}
}
