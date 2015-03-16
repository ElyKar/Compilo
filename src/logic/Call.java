package logic;
import general.*;
import yaka.*;
import generation.PrintError;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;


public class Call {
	private ArrayDeque<Func> functions = new ArrayDeque<>();
	private ArrayDeque<String> names = new ArrayDeque<>();
	private ArrayDeque<Integer> currentParam = new ArrayDeque<>();
	
	public void init() {
		Func f = Yaka.tabIdent.getFunc(YakaTokenManager.identRead);
		if (f == null) {
			PrintError.unknownVariable(YakaTokenManager.identRead);
		} else {
			currentParam.push(0);
			functions.push(f);
		}
		names.push(YakaTokenManager.identRead);
		Yaka.yvm.reserveRetour();
	}
	
	public void pushParameter() {
		try {
			int param = currentParam.pop();
			if (!Yaka.expression.checkType(functions.peek().getParam()[param])) {
				PrintError.unTypeMis(functions.peek().getParam()[param], Yaka.expression.peek());
			}
			param++;
			currentParam.push(param);
		} catch (NoSuchElementException e) {}
		Yaka.expression.endExpr();
	}
	
	public void call() {
		Yaka.yvm.call(names.pop());
		try {
			currentParam.pop();
		} catch (NoSuchElementException e) {}
		Yaka.evaluated = true;
	}
	
	public Type popType() {
		Type t;
		try {
			t = functions.pop().getReturn();
		} catch (NoSuchElementException e) {
			t = Type.ERROR;
		}
		return t;
	}

}
