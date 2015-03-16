package generation;
import general.Type;
import yaka.Yaka;


public class PrintError {
	
	public static boolean flag = false;
	
	public static void binTypeMis(Type exp1, Type exp2, Type rec1, Type rec2) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Expected "+exp1+","+exp2+" ; Received "+rec1+","+rec2);
	}
	
	public static void unTypeMis(Type exp, Type rec) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Expected "+exp+" ; Received "+rec);
	}
	
	public static void unknownVariable(String var) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Unknown Ident \""+var+"\"");
	}
	
	public static void declaredVariable(String var) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Ident already declared \""+var+"\"");
	}
	
	public static void affectConstant(String id) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Cannot affect a value to a declared constant \""+id+"\"");
	}
	
	public static void typeMis(Type exp1, Type exp2, Type rec) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Expected "+exp1+" or "+exp2+" ; Received "+rec);
	}
	
	public static void affectTypeMis(String id, Type exp, Type rec) {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Received Type "+exp+" : Variable \""+id+"\" is of Type "+rec);
	}
	
	public static void declErrType() {
		flag = true;
		System.out.println("Error on line "+Yaka.line+" : Invalid Type");
		System.out.println("Cannot declare a Type ERROR");
	}

}
