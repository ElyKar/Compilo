

public class IdConst extends Ident {
	
	public IdConst(Type t, int value) {
		super(t);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "CONSTANT\t"+type+"\t"+"VALUE : "+value;
	}
	
	public void write() {
		Yaka.yvm.iconst(value);
	}
}
