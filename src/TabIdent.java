

import java.util.HashMap;
import java.util.Map;

public class TabIdent {
	private Map<String, Ident> table;
	private String nextAffectation;
	private Type nextType;
	private int nextOffset = -2;
	
	public TabIdent(int size) {
		table = new HashMap<>(size);
	}
	
	public TabIdent() {
		table = new HashMap<>();
	}
	
	public Ident getIdent(String key) {
		return table.get(key);
	}
	
	public boolean contains(String key) {
		return table.containsKey(key);
	}
	
	public void putIdent(String key, Ident value) {
		table.put(key,  value);
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Map.Entry<String, Ident> m : table.entrySet()) {
			res.append(m.getKey()+"\t : "+m.getValue()+"\t\n");
		}
		return res.toString();
	}

}
