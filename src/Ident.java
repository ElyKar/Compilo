

public abstract class Ident {
	protected Type type;
	protected int value;
	
	public Ident(Type t) {
		type = t;
	}
	
	public int getValue() {
		return value;
	}
	
	abstract void write();
}
