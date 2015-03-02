package main;

public class IdConst extends Ident {
	
	@Override
	public String toString() {
		return "CONSTANT\t"+type+"\t"+"VALUE : "+val;
	}
}
