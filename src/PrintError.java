
public class PrintError {
	
	public static void binTypeMis(Type exp1, Type exp2, Type rec1, Type rec2) {
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Expected "+exp1+","+exp2+" ; Received "+rec1+","+rec2);
	}
	
	public static void unTypeMis(Type exp, Type rec) {
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Expected "+exp+" ; Received "+rec);
	}
	
	public static void unknownVariable(String var) {
		System.out.println("Error on line "+Yaka.line+" : Unknown Variable \""+var+"\"");
	}
	
	public static void affectConstant(String id) {
		System.out.println("Error on line "+Yaka.line+" : Cannot affect a value to a declared constant \""+id+"\"");
	}
	
	public static void typeMis(Type exp1, Type exp2, Type rec) {
		System.out.println("Error on line "+Yaka.line+" : Type mismatch");
		System.out.println("Excepted "+exp1+" or "+exp2+" ; Received "+rec);
	}

}
