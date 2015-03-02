package main;

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	private ArrayDeque<Integer> operands;
	
	public Expression() {
		operators = new ArrayDeque<>();
		operands = new ArrayDeque<>();
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void pushOperand(int val) {
		operands.push(val);
	}
	
	public void iadd() {
		operands.push(operands.pop()+operands.pop());
	}
	
	public void imul() {
		operands.push(operands.pop()*operands.pop());
	}
	
	public void idiv() {
		int a = operands.pop();
		operands.push(operands.pop()/a);
	}
	
	public void isub() {
		int a = operands.pop();
		operands.push(operands.pop()-a);;
	}
	
	public void ineg() {
		operands.push(-operands.pop());
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
		return operators.pop();
	}

}
