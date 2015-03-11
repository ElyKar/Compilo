import java.util.ArrayDeque;


public class Call {
	private ArrayDeque<Func> functions = new ArrayDeque<>();
	private ArrayDeque<String> names = new ArrayDeque<>();
	private int currentParam;
	
	public void init() {
		Func f = Yaka.tabIdent.getFunc(YakaTokenManager.identRead);
		names.push(YakaTokenManager.identRead);
		functions.push(f);
		Yaka.yvm.reserveRetour();
	}
	
	public void pushParameter() {
		Yaka.expression.checkType(functions.peek().getParam()[currentParam++]);
		Yaka.expression.finLigne();
	}
	
	public void call() {
		Yaka.yvm.call(names.pop());
		Yaka.evaluated = true;
	}
	
	public Type popType() {
		return functions.pop().getReturn();
	}

}
