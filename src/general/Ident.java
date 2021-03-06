package general;



public abstract class Ident {
	protected Type type;
	protected int value;
	
	public Ident(Type t) {
		type = t;
	}
	
	public int getValue() {
		return value;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public abstract void write();
	
	public abstract Ident clone();
}
