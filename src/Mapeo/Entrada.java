package Mapeo;

public class Entrada<K,V> implements Entry<K,V>
{
	protected K key;
	protected V value;
	
	public Entrada() 
	{
		key = null;
		value = null;
	}
	
	public Entrada(K k, V v)
	{
		key = k;
		value = v;
	}

	public void setKey(K k)
	{
		key = k;
	}
	
	public void setValue(V v)
	{
		value = v;
	}
	
	public K getKey() 
	{
		return key;
	}

	public V getValue() 
	{
		return value;
	}

}
