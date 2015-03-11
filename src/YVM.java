

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
	
	
	/*********************
	 * overhead and footer of the yvm program
	 ********************/
	
	public void entete() {
		writeln("entete");
	}
	
	public void ouvrePrinc(int offset) {
		writeln("ouvrePrinc "+offset);
	}
	
	public void queue() {
		writeln("queue");
	}
	
	/*********************
	 * Storing a value on the top of the stack
	 ********************/
	
	public void iconst(int c) {
		writeln("iconst "+c);
	}
	
	public void iload(int offset) {
		writeln("iload "+offset);
	}
	
	public void istore(int offset) {
		writeln("istore "+offset);
	}
	
	/*********************
	 * Pop the first two values and push the result of the binary operation called
	 ********************/
	
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
	
	/*********************
	 * Pop the first value and push the result of the unary operation
	 ********************/
	
	public void ineg() {
		writeln("ineg");
	}
	
	public void inot() {
		writeln("inot");
	}
	
	/*********************
	 * Pop the first 2 values and push the result of the comparison
	 ********************/
	
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
	
	/*********************
	 * Input and Output functions
	 ********************/
	
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
	
	/*********************
	 * Creates a label
	 ********************/
	
	public void label(String label) {
		writeln("\n"+label+":");
	}
	
	/*********************
	 * Condition
	 ********************/
	
	public void iffalse(String label) {
		writeln("iffaux "+label);
	}
	
	/*********************
	 * Go to the specified label
	 ********************/
	
	public void goTo(String label) {
		writeln("goto "+label);
	}
	
	/*********************
	 * Functions instructions
	 ********************/
	
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
		writeln("reserveRetour");
	}
	
	public void mainStart(){
		writeln("\nmain:");
	}
	
}
