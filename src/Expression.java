

import java.util.ArrayDeque;

public class Expression {
	private ArrayDeque<Character> operators;
	public ArrayDeque<Type> types;
	
	public Expression() {
		operators = new ArrayDeque<>();
		types = new ArrayDeque<>();
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
	
	public void writeInt() {
		Yaka.yvm.writeInt();
		finLigne();
	}
	
	public void pushOperand(String id){
		if(!Yaka.tabIdent.contains(id)) {
			System.out.println("Error on line "+Yaka.line+" : Unknown variable : "+id);
			types.push(Type.ERROR);
		}
		else
			types.push(Yaka.tabIdent.getIdent(id).type);
		Yaka.tabIdent.getIdent(id).write();
		}
	
	public void pushOperand(int val) {
		types.push(Type.INTEGER);
		Yaka.yvm.iconst(val);
	}
	
	public void pushOperand(boolean val) {
		types.push(Type.BOOLEAN);
		Yaka.yvm.iconst((val) ? -1 : 0);
	}
	
	public boolean updateType(Type c, Type r){
		Type t = types.pop();
		if(t != c){
			System.out.println("Error on line "+Yaka.line+" : Expecting "+c+" : received "+t);
			types.push(Type.ERROR);
			return false;
		}
		types.push(r);
		return true;
	}
	
	public boolean updateTypes(Type c, Type r){
		Type t2 = types.pop();
		Type t1 = types.pop();
		if (t1!=c || t2!=c) {
			types.push(Type.ERROR);
			System.out.println("Error on line "+Yaka.line+" : Expecting "+c+","+c+" : received "+t1+","+t2);
			return false;
		}
		types.push(r);
		return true;
	}
	
	public boolean updateTypes(Type c) {
		return updateTypes(c, c);
	}
	
	public boolean updateType(Type c) {
		return updateType(c, c);
	}
	
	public void iadd() {
		if(updateTypes(Type.INTEGER))
			Yaka.yvm.iadd();
	}
	
	public void imul() {
		if(updateTypes(Type.INTEGER))
			Yaka.yvm.imul();
	}
	
	public void idiv() {
		if(updateTypes(Type.INTEGER))
			Yaka.yvm.idiv();
	}
	
	public void isub() {
		if(updateTypes(Type.INTEGER))
			Yaka.yvm.isub();
	}
	
	public void ineg() {
		if(updateType(Type.INTEGER))
			Yaka.yvm.ineg();
	}
	
	public void iand() {		
		if(updateTypes(Type.BOOLEAN))
			Yaka.yvm.iand();
	}
	
	public void ior() {
		if(updateTypes(Type.BOOLEAN))
			Yaka.yvm.ior();
	}
	
	public void inot() {
		if(updateType(Type.BOOLEAN))
			Yaka.yvm.inot();
	}
	
	public void iegal() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.iegal();
	}
	
	public void idiff() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.idiff();
	}
	
	public void iinf() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.iinf();
	}
	
	public void iinfegal() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.iinfegal();
	}
	
	public void isup() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.isup();
	}
	
	public void isupegal() {
		if(updateTypes(Type.INTEGER, Type.BOOLEAN))
			Yaka.yvm.isupegal();
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
		case Constant.EQU:
			iegal();
			break;
		case Constant.DIF:
			idiff();
			break;
		case Constant.INF:
			iinf();
			break;
		case Constant.EINF:
			iinfegal();
			break;
		case Constant.SUP:
			isup();
			break;
		case Constant.ESUP:
			isupegal();
			break;
		default:
			System.err.println("Unrecognized operator : "+c);
		}
	}

}
