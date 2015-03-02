package main;

import main.Enumeration.*;

public class IdVar extends Ident {
	private int offset;
	
	public IdVar(Type t, int offset) {
		super(t);
		this.offset = offset;
	}
	
	@Override
	public String toString() {
		return "VARIABLE\t"+type+"\t"+"offset : "+offset;
	}

}
