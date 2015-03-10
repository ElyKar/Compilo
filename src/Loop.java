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
		}
		Yaka.expression.finLigne();
	}
	
	public void goTo() {
		Yaka.yvm.goTo("FAIRE"+loops.peek());
	}

}
