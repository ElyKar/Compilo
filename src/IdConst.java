

public class IdConst extends Ident {
	
	public IdConst(Type t, int value) {
		super(t);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "CONSTANT\t"+type+"\t"+"VALUE\t"+value;
	}
	
	public String getMethod(){
		return "iconst " +value;
	}
}
