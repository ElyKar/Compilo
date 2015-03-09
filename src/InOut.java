
public class InOut {
	
	public static void write() {
		Yaka.yvm.writeln("ecrireEnt");
	}
	
	public static void write(String str) {
		Yaka.yvm.writeln("ecrireChaine \""+str+"\"");
	}
	
	public static void nextLine() {
		Yaka.yvm.writeln("aLaLigne");
	}
	
	public static void read(int offset) {
		Yaka.yvm.writeln("lireEnt "+offset);
	}

}
