

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
	
	public void putConst(int value){
		Ident toPush = new IdConst(Type.INTEGER,value);
		this.putIdent(this.nextAffectation, toPush);
	}
	
	public void putConst(boolean value){
		Ident toPush = new IdConst(Type.BOOLEAN,((value) ? -1 : 0));
		this.putIdent(this.nextAffectation, toPush);
	}
	
	public void putConst(String id){
		if(!this.contains(id))
			// Semantic exception
			;
		else{
			Ident toPush = this.getIdent(id);
			this.putIdent(this.nextAffectation, toPush);
		}
	}
	
	public void setVar(String id){
		Ident toPush = new IdVar(this.nextType, nextOffset );
		nextOffset-=2;
		this.putIdent(id, toPush);
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Map.Entry<String, Ident> m : table.entrySet()) {
			res.append(m.getKey()+"\t : "+m.getValue()+"\t\n");
		}
		return res.toString();
	}

	public void setNextAffectation(String nextAffectation) {
		this.nextAffectation = nextAffectation;
	}

	public void setNextType(Type nextType) {
		this.nextType = nextType;
	}
}
