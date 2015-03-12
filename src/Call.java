import java.util.ArrayDeque;


public class Call {
	private ArrayDeque<Func> functions = new ArrayDeque<>();
	private ArrayDeque<String> names = new ArrayDeque<>();
	private ArrayDeque<Integer> currentParam = new ArrayDeque<>();
	
	public void init() {
		Func f = Yaka.tabIdent.getFunc(YakaTokenManager.identRead);
		names.push(YakaTokenManager.identRead);
		currentParam.push(0);
		functions.push(f);
		Yaka.yvm.reserveRetour();
	}
	
	public void pushParameter() {
		int param = currentParam.pop();
		Yaka.expression.checkType(functions.peek().getParam()[param]);
		param++;
		currentParam.push(param);
		Yaka.expression.endExpr();
	}
	
	public void call() {
		Yaka.yvm.call(names.pop());
		currentParam.pop();
		Yaka.evaluated = true;
	}
	
	public Type popType() {
		return functions.pop().getReturn();
	}

}
