package main;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TabIdent {
	private Map<String, Ident> table;
	
	public TabIdent(int size) {
		if (size < 0) throw new IllegalArgumentException("Trying to create a Map of negative size.");
		table = new HashMap<>(size);
	}
	
	public Ident getIdent(String key) {
		if (!contains(key)) throw new NoSuchElementException("Ident undeclared");
		return table.get(key);
	}
	
	public boolean contains(String key) {
		if (key == null) throw new NullPointerException();
		return table.containsKey(key);
	}
	
	public void putIdent(String key, Ident value) {
		if (key == null || value == null) throw new NullPointerException();
		if (contains(key)) throw new IllegalArgumentException("Key already in the table");
		table.put(key,  value);
	}

}
