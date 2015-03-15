package general;

import yaka.Yaka;

public class IdVar extends Ident {
	
	public IdVar(Type t, int offset) {
		super(t);
		this.value = offset;
	}
	
	@Override
	public String toString() {
		return "VARIABLE\t"+type+"\t"+"offset = "+value;
	}

	public void write() {
		Yaka.yvm.iload(value);
	}
	
	public Ident clone() {
		return new IdVar(type, value);
	}
}
