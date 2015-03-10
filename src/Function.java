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
	
	public void addParam() {
		Ident p = new IdVar(Yaka.declaration.nextType, ++nextRank);
		param.add(Yaka.declaration.nextType);
		Yaka.tabIdent.putParam(YakaTokenManager.identRead, p);
	}
	
	public void addFunc() {
		Yaka.tabIdent.putFunc(name, new Func(returnType,(Type[]) param.toArray()));
		Yaka.tabIdent.updateOffset(nextRank);
	}
	
	public void clear() {
		Yaka.function = new Function();
		Yaka.tabIdent.clear();
		Yaka.yvm.fermebloc(nextRank*2);
	}

}
