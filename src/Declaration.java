public class Declaration {
	private String nextIdent;
	private Type nextType;
	public int nextOffset = -2;
	
	public void putConst(int value){
		Ident toPush = new IdConst(Type.INTEGER,value);
		Yaka.tabIdent.putIdent(this.nextIdent, toPush);
	}
	
	public void putConst(boolean value){
		Ident toPush = new IdConst(Type.BOOLEAN,((value) ? -1 : 0));
		Yaka.tabIdent.putIdent(this.nextIdent, toPush);
	}
	
	public void putConst(String id){
		if(!Yaka.tabIdent.contains(id))
			System.out.println("Error on line "+Yaka.line+" : Unknown identifier : "+id);
		else{
			Ident toPush = Yaka.tabIdent.getIdent(id);
			Yaka.tabIdent.putIdent(this.nextIdent, toPush);
		}
	}
	
	public void putVar(){
		if(!Yaka.tabIdent.contains(this.nextIdent))
			System.out.println("Error on line "+Yaka.line+" : Unknown identifier : "+nextIdent);
		else
			Yaka.yvm.istore(Yaka.tabIdent.getIdent(this.nextIdent).getValue());
		Yaka.expression.finLigne();
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

	public void setNextIdent(String nextIdent) {
		this.nextIdent = nextIdent;
	}

	public void setNextType(Type nextType) {
		this.nextType = nextType;
	}
	
	public void setStack() {
		if (nextOffset != -2) {
			Yaka.yvm.ouvrePrinc(-nextOffset-2);
		}
	}
	
	
}
