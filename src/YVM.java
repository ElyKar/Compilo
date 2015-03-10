

import java.io.FileOutputStream;
import java.io.IOException;

public class YVM {
	protected FileOutputStream file;
	
	public YVM() {}
	
	public YVM(String fileName) throws IOException {
		file = new FileOutputStream(fileName+".yvm");
	}
	
	protected void write(String str) {
		try {
			file.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeln(String str) {
		write(str+"\n");
	}
	
	public void entete() {
		writeln("entete");
	}
	
	public void ouvrePrinc(int offset) {
		writeln("ouvrePrinc "+offset);
	}
	
	public void queue() {
		writeln("queue");
	}
	
	public void iconst(int c) {
		writeln("iconst "+c);
	}
	
	public void iload(int offset) {
		writeln("iload "+offset);
	}
	
	public void istore(int offset) {
		writeln("istore "+offset);
	}
	
	public void iadd() {
		writeln("iadd");
	}
	
	public void isub() {
		writeln("isub ");
	}
	
	public void imul() {
		writeln("imul");
	}
	
	public void idiv() {
		writeln("idiv");
	}
	
	public void iand() {
		writeln("iand");
	}
	
	public void ior() {
		writeln("ior");
	}
	
	public void ineg() {
		writeln("ineg");
	}
	
	public void inot() {
		writeln("inot");
	}
	
	public void iegal() {
		writeln("iegal");
	}
	
	public void idiff() {
		writeln("idiff");
	}
	
	public void iinf() {
		writeln("iinf");
	}
	
	public void iinfegal() {
		writeln("iinfegal");
	}
	
	public void isup() {
		writeln("isup");
	}
	
	public void isupegal() {
		writeln("isupegal");
	}
	
	public void writeInt() {
		writeln("ecrireEnt");
	}
	
	public void writeBool() {
		writeln("ecrireBool");
	}
	
	public void writeStr(String str) {
		writeln("ecrireChaine "+str);
	}
	
	public void read(int offset) {
		writeln("lireEnt "+offset);
	}
	
	public void backToLine() {
		writeln("aLaLigne");
	}
	
	public void label(String label) {
		writeln("\n"+label+":");
	}
	
	public void iffalse(String label) {
		writeln("iffaux "+label);
	}
	
	public void goTo(String label) {
		writeln("goto "+label);
	}
	
	public void ouvbloc(int n) {
		writeln("ouvbloc "+n);
	}
	
	public void fermebloc(int n) {
		writeln("fermebloc "+n);
	}
	
	public void ireturn(int a) {
		writeln("ireturn "+a);
	}
	
	public void call(String f) {
		writeln("call "+f);
	}
	
	public void reserveRetour() {
		writeln("reservRetour");
	}
	
}
