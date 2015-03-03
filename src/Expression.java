

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	YVM yvm;
	
	public Expression() {
		operators = new ArrayDeque<>();
	}
	
	public void setWriter(YVM y) {
		yvm = y;
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void pushOperand(int val) {
		yvm.writeln("iconst "+val);
	}
	
	public void iadd() {
		yvm.writeln("iadd");
	}
	
	public void imul() {
		yvm.writeln("imul");
	}
	
	public void idiv() {
		yvm.writeln("idiv");
	}
	
	public void isub() {
		yvm.writeln("isub");
	}
	
	public void ineg() {
		yvm.writeln("ineg");
	}
	
	public void operation() {
		char c = operators.pop();
		switch(c) {
		case '+' :
			iadd();
			break;
		case '-':
			isub();
			break;
		case '*':
			imul();
			break;
		case '/':
			idiv();
			break;
		case '_':
			ineg();
			break;
		default:
			System.err.println("Unrecognized operator : "+c);
		}
	}

}
