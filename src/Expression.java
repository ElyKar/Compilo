

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	private ArrayDeque<Integer> operands;
	YVM yvm;
	
	public Expression() {
		operators = new ArrayDeque<>();
		operands = new ArrayDeque<>();
	}
	
	public void setWriter(YVM y) {
		yvm = y;
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void pushOperand(int val) {
		operands.push(val);
		yvm.writeln("iconst "+val);
	}
	
	public void iadd() {
		operands.push(operands.pop()+operands.pop());
		yvm.writeln("iadd");
	}
	
	public void imul() {
		operands.push(operands.pop()*operands.pop());
		yvm.writeln("imul");
	}
	
	public void idiv() {
		int a = operands.pop();
		operands.push(operands.pop()/a);
		yvm.writeln("idiv");
	}
	
	public void isub() {
		int a = operands.pop();
		operands.push(operands.pop()-a);
		yvm.writeln("isub");
	}
	
	public void ineg() {
		operands.push(-operands.pop());
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
	
	public int getValue() {
		return operands.pop();
	}

}
