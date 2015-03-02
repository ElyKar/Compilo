package main;

public class IdVar extends Ident {
	
	@Override
	public String toString() {
		return "VARIABLE\t"+type+"\t"+"offset : "+val;
	}

}
