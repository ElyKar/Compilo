public class Declaration {
	private String nextAffectation;
	private Type nextType;
	private int nextOffset = -2;
	
	public void putConst(int value){
		Ident toPush = new IdConst(Type.INTEGER,value);
		Yaka.tabIdent.putIdent(this.nextAffectation, toPush);
	}
	
	public void putConst(boolean value){
		Ident toPush = new IdConst(Type.BOOLEAN,((value) ? -1 : 0));
		Yaka.tabIdent.putIdent(this.nextAffectation, toPush);
	}
	
	public void putConst(String id){
		if(!Yaka.tabIdent.contains(id))
			// Semantic exception
			;
		else{
			Ident toPush = Yaka.tabIdent.getIdent(id);
			Yaka.tabIdent.putIdent(this.nextAffectation, toPush);
		}
	}
	
	public void setVar(String id){
		Ident toPush = new IdVar(this.nextType, nextOffset );
		nextOffset-=2;
		Yaka.tabIdent.putIdent(id, toPush);
	}
	
	@Override
	public String toString() {
		return Yaka.tabIdent.toString();
	}

	public void setNextAffectation(String nextAffectation) {
		this.nextAffectation = nextAffectation;
	}

	public void setNextType(Type nextType) {
		this.nextType = nextType;
	}
	
	public void setStack() {
		if (nextOffset != -2) {
			Yaka.yvm.writeln("ouvrePrinc "+(-nextOffset-2));
		}
	}
	
	
}
