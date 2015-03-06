

public class IdVar extends Ident {
	
	public IdVar(Type t, int offset) {
		super(t);
		this.value = offset;
	}
	
	@Override
	public String toString() {
		return "VARIABLE\t"+type+"\t"+"offset : "+value;
	}

}
