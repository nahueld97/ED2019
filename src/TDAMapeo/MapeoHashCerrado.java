package TDAMapeo;
import TDALista.*;
import TDACola.*;
import exceptions.*;

public class MapeoHashCerrado<K,V> implements Map<K,V>
{
	protected int size;
	protected Entry<K,V>[] arreglo;
	protected final double FactorCarga = 0.5;
	protected final Entry<K,V> disp = new Entrada<K,V>(null,null);

	public MapeoHashCerrado()
	{
		size = 0;
		arreglo = new Entry[29];
	}
	
	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size==0;
	}

	public V get(K key) throws InvalidKeyException 
	{
		checkKey(key);
		V value = null;
		if(!isEmpty())
		{
			int i = findKey(key);
			if(i!=-1)
			{
				value = arreglo[i].getValue();
			}
		}
		return value;
	}

	public V put(K key, V value) throws InvalidKeyException 
	{
		checkKey(key);
		Entry<K,V> nuevo =new Entrada<K,V>(key,value);
		V toRet = null;
		if(arreglo[codigoH(key)] == null)// utilizo esto para ver si nunca se inserto un elemento en esa posicion
		{
			arreglo[codigoH(key)] = nuevo;
			size++;
		}
		else // en el caso que tenga un disponible yo contemplo la posibilidad de que el elemento que me pasaron pueda estar en una posicion mas adelante
		{
			int i = findKey(key);
			if(i!=-1) // en el caso que no la encuentre antes de encontrar un nulo uso -1 como condicion booleana
			{
				toRet = arreglo[i].getValue();
				arreglo[i] = nuevo;
				
			}
			else
			{
				int j = codigoH(key);
				boolean encontre = false;
				while(!encontre) //busco un lugar disponible para colocar el elemento nuevo, el cual no lo encontre antes.
				{
					if(arreglo[j] == disp || arreglo[j] == null) // si encuentro un nulo o un lugar disponible inserto el elemento nuevo en esa posicion.
					{
						arreglo[j] = nuevo;
						size++;
						encontre = true;
					}
					else
						j = (j+1)%arreglo.length; // actualizo j para seguir buscando una posicion.
				}
			}
		}
		if((size/arreglo.length)>=FactorCarga) // en el caso de que la cantidad de elementos sea mayor a la mitad del arreglo se actualiza el tamanio del arreglo
			ReHash();
		return toRet;
	}

	public V remove(K key) throws InvalidKeyException 
	{
		checkKey(key);
		V toRet = null;
		if(!isEmpty()) 
		{
			int i = findKey(key);
			if(i != -1)
			{
				toRet = arreglo[i].getValue();
				arreglo[i] = disp;
				size--;
			}
		}
		return toRet;
	}

	public Iterable<K> keys() 
	{
		DoubleLinkedList<K> lista = new DoubleLinkedList<K>();
		for(Entry<K,V> e:arreglo)
		{
			if(e != null && e != disp)
			{
				lista.addLast(e.getKey());
			}
		}
		return lista;
	}

	public Iterable<V> values() 
	{
		DoubleLinkedList<V> lista = new DoubleLinkedList<V>();
		for(Entry<K,V> e:arreglo)
		{
			if(e != null &&e != disp)
			{
				lista.addLast(e.getValue());
			}
		}
		return lista;
	}

	public Iterable<Entry<K, V>> entries() 
	{
		DoubleLinkedList<Entry<K,V>> lista = new DoubleLinkedList<Entry<K,V>>();
		for(Entry<K,V> e:arreglo)
		{
			if(e != null&&e != disp)
			{
				lista.addLast(e);
			}
		}
		return lista;
	}
	
	private int findKey(K key)
	{
		int k = -1;// variable de corte
		int i = codigoH(key);
		while((k == -1 )&& arreglo[i] != null)
		{
			if(arreglo[i]!=disp)
			{
				if(arreglo[i].getKey().equals(key))
					k = i;// si son iguales modifico la condicion de corte
				else
					i = (i+1)%arreglo.length;
			}
			else
				i = (i+1)%arreglo.length;
		}
		return k;
	}

	private void ReHash()
	{
		ColaEnlazada<Entry<K,V>> cola = new ColaEnlazada<Entry<K,V>> ();
		for(Entry<K,V> e:arreglo)
		{
			if(e != null && e != disp) //encolo los elementos que no son nulos ni disp
			{
				cola.enqueue(e);
			}
		}
		arreglo = new Entry[arreglo.length*2];
		while(!cola.isEmpty())
		{
			try
			{
				Entry<K,V> aux = cola.dequeue();
				boolean disponible = true;
				int i = codigoH(aux.getKey());
				while(disponible)
				{
					if(arreglo[i] == null)
					{
							arreglo[i] = aux;
							disponible = false;
					}
						else
							i = (i+1)%arreglo.length;
				}
			}
			catch (EmptyQueueException h)
			{}
		}
	}
	
	private int codigoH(K key)
	{
		return ((key.hashCode())%(arreglo.length));
	}

	private void checkKey(K k) throws InvalidKeyException
	{
		if (k == null)
			throw new InvalidKeyException("Clave Invalida");
	}
}
