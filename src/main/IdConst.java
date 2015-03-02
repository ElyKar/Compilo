package main;

import main.Enumeration.Type;

public class IdConst extends Ident {
	private int value;
	
	public IdConst(Type t, int value) {
		super(t);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "CONSTANT\t"+type+"\t"+"VALUE : "+value;
	}
}
