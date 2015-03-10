

import java.util.HashMap;
import java.util.Map;

public class TabIdent {
	private Map<String, Func> global;
	private Map<String, Ident> local;
	private Map<String, Ident> param;
	
	public TabIdent(int size) {
		global = new HashMap<>(size);
		local = new HashMap<>(size);
		param = new HashMap<>(size);
	}
	
	public void clear() {
		local.clear();
		param.clear();
	}
	
	public TabIdent() {
		local = new HashMap<>();
		global = new HashMap<>();
		param = new HashMap<>();
	}
	
	public Ident getIdent(String key) {
		return (local.get(key) == null) ? param.get(key) : local.get(key);
	}
	
	public Func getFunc(String key) {
		return global.get(key);
	}
	
	public void updateOffset(int nbParam) {
		for (Ident i : param.values()) {
			i.setValue(4+nbParam*2-(i.getValue()*2));
		}
	}
	
	public boolean containsId(String key) {
		return local.containsKey(key) || param.containsKey(key);
	}
	
	public boolean constainsFunc(String key) {
		return global.containsKey(key);
	}
	
	public void putLocal(String key, Ident value) {
		local.put(key,  value);
	}
	
	public void putParam(String key, Ident value) {
		param.put(key, value);
	}
	
	public void putFunc(String key, Func value) {
		global.put(key, value);
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("global : {\n");
		for (Map.Entry<String, Func> m : global.entrySet()) {
			res.append("\t"+m.getKey()+" = FUNCTION resultat : "+m.getValue().getReturn()+" les parametres : "+m.getValue().getParam()+"\n");
		}
		res.append("}\n");
		res.append("local : {\n");
		for (Map.Entry<String, Ident> m : local.entrySet()) {
			res.append("\t"+m.getKey()+" = VARIABLE\t: "+m.getValue()+"\n");
		}
		for (Map.Entry<String, Ident> m : param.entrySet()) {
			res.append("\t"+m.getKey()+" = PARAMETER\t: "+m.getValue()+"\n");
		}
		res.append("}\n");
		return res.toString();
	}

}
