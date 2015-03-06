

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	private ArrayDeque<Type> types;
	private TabIdent identifiers;
	
	public Expression(TabIdent id) {
		operators = new ArrayDeque<>();
		types = new ArrayDeque<>();
		identifiers = id;
	}
	
	public boolean typesEmpty(){
		return this.types.isEmpty();
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void finLigne(){
		if(types.peek() != Type.ERROR)
			types.pop();
	}
	
	public void pushOperand(String id){
		if(!this.identifiers.contains(id))
			types.push(Type.ERROR);
		else
			types.push(this.identifiers.getIdent(id).type);
		if(this.identifiers.getIdent(id) instanceof IdVar)
			Yaka.yvm.writeln("iload "+ this.identifiers.getIdent(id).value);
		else
			Yaka.yvm.writeln("icosnt "+ this.identifiers.getIdent(id).value);
	}
	
	public void pushOperand(int val) {
		types.push(Type.INTEGER);
		Yaka.yvm.writeln("iconst "+val);
	}
	
	public void pushOperand(boolean val) {
		types.push(Type.BOOLEAN);
		Yaka.yvm.writeln("iconst " + String.valueOf(val).toUpperCase());
	}
	public boolean testType(Type c){
		Type t = types.pop();
		if(t!=c){
			types.push(Type.ERROR);
			return false;
		}
		types.push(c);
		return true;
	}
	
	public boolean testTypes(Type c){
		Type t2 = types.pop();
		Type t1 = types.pop();
		if (t1!=c || t2!=c){
			types.push(Type.ERROR);
			return false;
		}
		types.push(c);
		return true;
	}
	
	public void iadd() {
		if(testTypes(Type.INTEGER))
			Yaka.yvm.writeln("iadd");
		//Todo : say error on line x
	}
	
	public void imul() {
		if(testTypes(Type.INTEGER))
			Yaka.yvm.writeln("imul");
	}
	
	public void idiv() {
		if(testTypes(Type.INTEGER))
			Yaka.yvm.writeln("idiv");
	}
	
	public void isub() {
		if(testTypes(Type.INTEGER))
			Yaka.yvm.writeln("isub");
	}
	
	public void ineg() {
		if(testType(Type.INTEGER))
			Yaka.yvm.writeln("ineg");
	}
	
	public void iand() {		
		if(testTypes(Type.BOOLEAN))
			Yaka.yvm.writeln("iand");
	}
	
	public void ior() {
		if(testTypes(Type.BOOLEAN))
			Yaka.yvm.writeln("ior");
	}
	
	public void inot() {
		if(testType(Type.BOOLEAN))
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
