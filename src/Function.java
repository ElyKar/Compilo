import java.util.List;
import java.util.LinkedList;


public class Function {
	private int nextRank;
	private int nbVar;
	private Type returnType;
	private String name;
	private List<Type> param = new LinkedList<>();
	
	public void setReturn() {
		returnType = Yaka.declaration.getNextType();
		if (returnType == Type.ERROR) {
			PrintError.declErrType();
		}
	}
	
	public void setName() {
		name = YakaTokenManager.identRead;
		Yaka.yvm.label(name);
	}
	
	public void ireturn() {
		Yaka.yvm.ireturn(nextRank*2+4);
		if (!Yaka.expression.checkType(returnType)) {
			PrintError.unTypeMis(returnType, Yaka.expression.peek());
		}
		Yaka.expression.endExpr();
	}
	
	public void addParam() {
		Ident p = new IdVar(Yaka.declaration.nextType, ++nextRank);
		param.add(Yaka.declaration.nextType);
		if (Yaka.tabIdent.containsId(YakaTokenManager.identRead)) {
			PrintError.declaredVariable(YakaTokenManager.identRead);
		}
		Yaka.tabIdent.putParam(YakaTokenManager.identRead, p);
	}
	
	public void addFunc() {
		if (Yaka.tabIdent.containsFunc(name)) {
			PrintError.declaredVariable(name);
		}
		Yaka.tabIdent.putFunc(name, new Func(returnType,(Type[]) param.toArray(new Type[param.size()])));
		Yaka.tabIdent.updateOffset(nextRank);
	}
	
	public void clear() {
		Yaka.function = new Function();
		Yaka.tabIdent.clear();
		Yaka.declaration.clear();
		Yaka.yvm.fermebloc(nextRank*2);
	}

}
