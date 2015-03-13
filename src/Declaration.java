public class Declaration {
	public String nextIdent;
	public Type nextType;
	public int nextOffset = -2;
	
	public void putConst(int value){
		Ident toPush = new IdConst(Type.INTEGER,value);
		if (Yaka.tabIdent.containsId(nextIdent))
			PrintError.declaredVariable(nextIdent);
		else 
			Yaka.tabIdent.putLocal(nextIdent, toPush);
	}
	
	public void putConst(boolean value){
		Ident toPush = new IdConst(Type.BOOLEAN,((value) ? -1 : 0));
		if (Yaka.tabIdent.containsId(nextIdent))
			PrintError.declaredVariable(nextIdent);
		else 
			Yaka.tabIdent.putLocal(nextIdent, toPush);
	}
	
	public void putConst(String id){
		if(!Yaka.tabIdent.containsId(id))
			PrintError.unknownVariable(id);
		else{
			Ident toPush = Yaka.tabIdent.getIdent(id).clone();
			if (Yaka.tabIdent.containsId(nextIdent))
				PrintError.declaredVariable(nextIdent);
			else 
				Yaka.tabIdent.putLocal(nextIdent, toPush);
		}
	}
	
	public void putVar(){
		if(!Yaka.tabIdent.containsId(nextIdent))
			PrintError.unknownVariable(nextIdent);
		else {
			if (Yaka.tabIdent.getIdent(nextIdent) instanceof IdConst)
				PrintError.affectConstant(nextIdent);
			if (! Yaka.expression.checkType(Yaka.tabIdent.getIdent(nextIdent).getType())) {
				PrintError.affectTypeMis(nextIdent, Yaka.tabIdent.getIdent(nextIdent).getType(), Yaka.expression.peek());
			}
			Yaka.yvm.istore(Yaka.tabIdent.getIdent(nextIdent).getValue());
		}
		Yaka.expression.endExpr();
	}
	
	public void setVar(String id){
		Ident toPush = new IdVar(nextType, nextOffset );
		nextOffset-=2;
		Yaka.tabIdent.putLocal(id, toPush);
	}
	
	@Override
	public String toString() {
		return Yaka.tabIdent.toString();
	}

	public void setNextIdent(String nextIdent) {
		this.nextIdent = nextIdent;
	}

	public void setNextType(Type nextType) {
		if (nextType == Type.ERROR) {
			PrintError.declErrType();
		}
		this.nextType = nextType;
	}
	
	public void setStack() {
		Yaka.yvm.ouvbloc(-nextOffset-2);
	}
	
	public void clear() {
		Yaka.declaration = new Declaration();
	}
	
	public Type getNextType() {
		return nextType;
	}
	
}
