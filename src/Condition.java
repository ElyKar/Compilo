import java.util.ArrayDeque;


public class Condition {
	private int nbCond;
	private ArrayDeque<Integer> conditions = new ArrayDeque<>();
	
	public void setIf() {
		conditions.push(++nbCond);
	}
	
	public void iffalse() {
		if(Yaka.expression.checkType(Type.BOOLEAN)) {
			Yaka.yvm.iffalse("SINON"+conditions.peek());
		}
		Yaka.expression.finLigne();
	}
	
	public void then() {
		Yaka.yvm.goTo("FSI"+conditions.peek());
	}
	
	public void setElse() {
		Yaka.yvm.etiq("SINON"+conditions.peek());
	}
	
	public void setFi() {
		Yaka.yvm.etiq("FSI"+conditions.pop());
	}

}
