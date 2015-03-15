package logic;

import yaka.Yaka;
import yaka.YakaTokenManager;
import general.Constant;
import general.IdConst;
import general.Ident;
import general.Type;
import generation.PrintError;

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
	
	public Type peek() {
		return types.peek();
	}
	
	public void pushOperator(char op) {
		operators.push(op);
	}
	
	public void endExpr(){
		if(types.peek() != Type.ERROR)
			types.pop();
	}
	
	public void writeVal() {
		if (types.peek() == Type.INTEGER) {
			Yaka.yvm.writeInt();
		} else if (types.peek() == Type.BOOLEAN) {
			Yaka.yvm.writeBool();
		} else {
			PrintError.typeMis(Type.INTEGER, Type.BOOLEAN, Type.ERROR);
		}
		endExpr();
	}
	
	public void read(String id) {
		Ident i = Yaka.tabIdent.getIdent(id);
		if (i == null)
			PrintError.unknownVariable(id);
		else {
			if (i.getType() != Type.INTEGER)
				PrintError.unTypeMis(Type.INTEGER, i.getType());
			if (i.getClass() == new IdConst(null,0).getClass())
				PrintError.affectConstant(id);
		}	
		Yaka.yvm.read(i.getValue());
	}
	
	public void pushOperand(){
		if (Yaka.evaluated) {
			types.push(Yaka.call.popType());
			Yaka.evaluated = false;
		} else {
			String id = YakaTokenManager.identRead;
			if(!Yaka.tabIdent.containsId(id)) {
				PrintError.unknownVariable(id);
				types.push(Type.ERROR);
			} else {
				types.push(Yaka.tabIdent.getIdent(id).getType());
				Yaka.tabIdent.getIdent(id).write();
			}
		}
	}
	
	public void pushOperand(int val) {
		types.push(Type.INTEGER);
		Yaka.yvm.iconst(val);
	}
	
	public void pushOperand(boolean val) {
		types.push(Type.BOOLEAN);
		Yaka.yvm.iconst((val) ? -1 : 0);
	}
	
	public boolean checkType(Type c){
		return (types.peek() == c);
	}
	
	public boolean checkTypes(Type c){
		Type t1 = types.pop();
		Type t2 = types.peek();
		types.push(t1);
		return (t1==c && t2==c);
	}
	
	public void iadd() {
		binOperation(Type.INTEGER, Type.INTEGER);
		Yaka.yvm.iadd();
	}
	
	public void imul() {
		binOperation(Type.INTEGER, Type.INTEGER);
		Yaka.yvm.imul();
	}
	
	public void idiv() {
		binOperation(Type.INTEGER, Type.INTEGER);
		Yaka.yvm.idiv();
	}
	
	public void isub() {
		binOperation(Type.INTEGER, Type.INTEGER);
		Yaka.yvm.isub();
	}
	
	public void ineg() {
		unOperation(Type.INTEGER, Type.INTEGER);
		Yaka.yvm.ineg();
	}
	
	public void iand() {		
		binOperation(Type.BOOLEAN, Type.BOOLEAN);
		Yaka.yvm.iand();
	}
	
	public void ior() {
		binOperation(Type.BOOLEAN, Type.BOOLEAN);
		Yaka.yvm.ior();
	}
	
	public void inot() {
		unOperation(Type.BOOLEAN, Type.BOOLEAN);
		Yaka.yvm.inot();
	}
	
	public void iegal() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.iegal();
	}
	
	public void idiff() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.idiff();
	}
	
	public void iinf() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.iinf();
	}
	
	public void iinfegal() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.iinfegal();
	}
	
	public void isup() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.isup();
	}
	
	public void isupegal() {
		binOperation(Type.INTEGER, Type.BOOLEAN);
		Yaka.yvm.isupegal();
	}
	
	private void binOperation(Type exp, Type ret) {
		if (checkTypes(exp)) {
			types.pop();
			types.pop();
			types.push(ret);
		} else {
			Type t1 = types.pop();
			Type t2 = types.pop();
			types.push(Type.ERROR);
			PrintError.binTypeMis(exp, exp, t1, t2);
		}
	}
	
	private void unOperation(Type exp, Type ret) {
		if (checkType(exp)) {
			types.pop();
			types.push(ret);
		} else {
			PrintError.unTypeMis(exp, types.pop());
			types.push(Type.ERROR);
		}
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
