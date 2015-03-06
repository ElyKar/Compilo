

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
		types.push(Constant.T_ENTIER);
		Yaka.yvm.writeln("iconst "+val);
	}
	
	public void pushOperand(boolean val) {
		types.push(Constant.T_BOOLEAN);
		Yaka.yvm.writeln("iconst " + String.valueOf(val).toUpperCase());
	}
	public boolean testType(char c){
		char t = types.pop();
		if(t!=c){
			types.push(Constant.T_ERROR);
			return false;
		}
		types.push(c);
		return true;
	}
	
	public boolean testTypes(char c){
		char t2 = types.pop();
		char t1 = types.pop();
		if (t1!=c || t2!=c){
			types.push(Constant.T_ERROR);
			return false;
		}
		types.push(c);
		return true;
	}
	
	public void iadd() {
		if(testTypes(Constant.T_ENTIER))
			Yaka.yvm.writeln("iadd");
		//Todo : say error on line x
	}
	
	public void imul() {
		if(testTypes(Constant.T_ENTIER))
			Yaka.yvm.writeln("imul");
	}
	
	public void idiv() {
		if(testTypes(Constant.T_ENTIER))
			Yaka.yvm.writeln("idiv");
	}
	
	public void isub() {
		if(testTypes(Constant.T_ENTIER))
			Yaka.yvm.writeln("isub");
	}
	
	public void ineg() {
		if(testType(Constant.T_ENTIER))
			Yaka.yvm.writeln("ineg");
	}
	
	public void iand() {		
		if(testTypes(Constant.T_BOOLEAN))
			Yaka.yvm.writeln("iand");
	}
	
	public void ior() {
		if(testTypes(Constant.T_BOOLEAN))
			Yaka.yvm.writeln("ior");
	}
	
	public void inot() {
		if(testType(Constant.T_BOOLEAN))
			Yaka.yvm.writeln("inot");
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
		case Constant.AND:
			iand();
			break;
		case Constant.OR:
			ior();
			break;
		case Constant.NOT:
			inot();
			break;
		default:
			System.err.println("Unrecognized operator : "+c);
		}
	}

}
