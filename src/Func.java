
public class Func {
	private Type ret;
	private Type[] param;
	
	public Func(Type r, Type[] p) {
		ret = r;
		param = p;
	}
	
	public Type getReturn() {
		return ret;
	}
	
	public Type[] getParam() {
		return param;
	}

}
