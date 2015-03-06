

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	private ArrayDeque<Character> types;
	
	public Expression() {
		operators = new ArrayDeque<>();
		types = new ArrayDeque<>();
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void pushOperand(int val) {
		types.push('e');
		Yaka.yvm.writeln("iconst "+val);
	}
	
	public void pushOperand(boolean val) {
		types.push('b');
		Yaka.yvm.writeln("iconst " + String.valueOf(val).toUpperCase());
	}
	
	public void iadd() {
		Yaka.yvm.writeln("iadd");
	}
	
	public void imul() {
		Yaka.yvm.writeln("imul");
	}
	
	public void idiv() {
		Yaka.yvm.writeln("idiv");
	}
	
	public void isub() {
		Yaka.yvm.writeln("isub");
	}
	
	public void ineg() {
		Yaka.yvm.writeln("ineg");
	}
	
	public void operation() {
		char c = operators.pop();
		switch(c) {
		case Constant.PLUS :
			iadd();
			break;
		case Constant.MINUS:
			isub();
			break;
		case Constant.MUL:
			imul();
			break;
		case Constant.DIV:
			idiv();
			break;
		case Constant.NEG:
			ineg();
			break;
		default:
			System.err.println("Unrecognized operator : "+c);
		}
	}

}
