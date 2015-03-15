package logic;
import general.Type;
import generation.PrintError;
import yaka.Yaka;

import java.util.ArrayDeque;


public class Loop {
	private int nbLoop;
	private ArrayDeque<Integer> loops = new ArrayDeque<>();
	
	public void setWhile() {
		Yaka.yvm.label("FAIRE"+ ++nbLoop);
		loops.push(nbLoop);
	}
	
	public void setDone() {
		Yaka.yvm.label("FAIT"+loops.pop());
	}
	
	public void iffalse() {
		if(Yaka.expression.checkType(Type.BOOLEAN)) {
			Yaka.yvm.iffalse("FAIT"+loops.peek());
		} else {
			PrintError.unTypeMis(Type.BOOLEAN, Yaka.expression.peek());
		}
		Yaka.expression.endExpr();
	}
	
	public void goTo() {
		Yaka.yvm.goTo("FAIRE"+loops.peek());
	}

}
