import java.util.List;
import java.util.LinkedList;


public class Function {
	private int nextRank;
	private int nbVar;
	private Type returnType;
	private String name;
	private List<Type> param = new LinkedList<>();
	
	public void setReturn() {
		returnType = Yaka.declaration.nextType;
	}
	
	public void setName() {
		name = YakaTokenManager.identRead;
		Yaka.yvm.label(name);
	}
	
	public void setVar() {
		Yaka.yvm.ouvbloc(nbVar*2);
	}
	
	public void incVar() {
		nbVar++;
	}
	
	public void ireturn() {
		Yaka.yvm.ireturn(nextRank*2+4);
		if (!Yaka.expression.checkType(returnType)) {
			System.out.println("Error on line "+Yaka.line+" : expecting return type "+returnType+", received "+Yaka.expression.types.peek());
		}
		Yaka.expression.endExpr();
	}
	
	public void addParam() {
		Ident p = new IdVar(Yaka.declaration.nextType, ++nextRank);
		param.add(Yaka.declaration.nextType);
		if (Yaka.tabIdent.containsId(YakaTokenManager.identRead)) {
			System.out.println("Error on line "+Yaka.line+" : Parameter "+YakaTokenManager.identRead+" already there");
		}
		Yaka.tabIdent.putParam(YakaTokenManager.identRead, p);
	}
	
	public void addFunc() {
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
