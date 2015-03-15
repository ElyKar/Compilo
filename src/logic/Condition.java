package logic;
import general.Type;
import yaka.Yaka;
import generation.PrintError;

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
		} else {
			PrintError.unTypeMis(Type.BOOLEAN, Yaka.expression.peek());
		}
		Yaka.expression.endExpr();
	}
	
	public void then() {
		Yaka.yvm.goTo("FSI"+conditions.peek());
	}
	
	public void setElse() {
		Yaka.yvm.label("SINON"+conditions.peek());
	}
	
	public void setFi() {
		Yaka.yvm.label("FSI"+conditions.pop());
	}

}
