import java.io.FileOutputStream;
import java.io.IOException;


public class YVMasm extends YVM {
	
	int msgcounter = 0;
	
	public YVMasm(String fileName) throws IOException {
		file = new FileOutputStream(fileName+".asm");
	}
	
	/*********************
	 * Functions for writing the header and footer of the .asm file
	 ********************/
	
	public void entete() {
		StringBuilder entete = new StringBuilder();
		entete.append(".model SMALL\n");
		entete.append(".586\n\n");
		entete.append(".CODE\n");
		entete.append("debut :\n");
		entete.append("\tSTARTUPCODE\n");
		writeln(entete.toString());
	}
	
	public void queue() {
		StringBuilder str = new StringBuilder();
		str.append("\tnop\n");
		str.append("\texitcode\n");
		str.append("\tend debut\n");
		writeln(str.toString());
	}
	
	public void ouvrePrinc(int n) {
		mov("bp", "sp");
		sub("sp", n);
		nextLine();
	}
	
	/*********************
	 * Functions for pushing values into the stack
	 ********************/
	
	public void iconst(int c) {
		push(c);
		nextLine();
	}
	
	public void iload(int offset) {
		pushOffset(offset);
		nextLine();
	}
	
	public void istore(int offset) {
		popAx();
		mov(offset);
		nextLine();
	}
	
	/*********************
	 * Operations on integers
	 ********************/
	
	public void iadd() {
		popBx();
		popAx();
		add();
		pushAx();
		nextLine();
	}
	
	public void isub() {
		popBx(); 
		popAx();
		sub();
		pushAx();
		nextLine();
	}
	
	public void imul() {
		popBx();
		popAx();
		mul();
		pushAx();
		nextLine();
	}
	
	public void idiv() {
		popBx();
		popAx();
		cwd();
		div();
		pushAx();
		nextLine();
	}
	
	public void ineg() {
		popBx();
		mov("ax", 0);
		sub();
		pushAx();
		nextLine();
	}
	
	/*********************
	 * Operations on booleans
	 ********************/
	
	public void iand() {
		popBx();
		popAx();
		and();
		pushAx();
		nextLine();
	}
	
	public void ior() {
		popBx();
		popAx();
		or();
		pushAx();
		nextLine();
	}
	
	public void inot() {
		popAx();
		not();
		pushAx();
		nextLine();
	}
	
	/*********************
	 * Comparisons
	 ********************/
	
	public void iegal() {
		popBx();
		popAx();
		cmp();
		jne(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void idiff() {
		popBx();
		popAx();
		cmp();
		je(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void iinf() {
		popBx();
		popAx();
		cmp();
		jge(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void iinfegal() {
		popBx();
		popAx();
		cmp();
		jg(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void isup() {
		popBx();
		popAx();
		cmp();
		jle(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void isupegal() {
		popBx();
		popAx();
		cmp();
		jl(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	/********************
	 * Fonctions inout
	 *******************/
	
	public void lireEnt(int offset){
		writeln("\tlea dx,[bp-2]");
		writeln("\tpush dx");
		writeln("\tcall lirent");
	}
	
	public void aLaLigne(){
		writeln("\tcall ligsuiv");
	}
	
	public void ecrireChaine(String s){
		writeln(".DATA");
		writeln("\tmess"+msgcounter+" DB " + s.substring(0, s.length() -1) + "$\"");
		writeln(".CODE");
		writeln("\tlea dx,mess"+msgcounter);
		pushDx();
		writeln("\tcall ecrch");
		msgcounter++;
	}
	
	public void ecrireEnt(){
		writeln("\tcall ecrent");
	}
	
	public void ecrireBool(){
		writeln("\tcall ecrbool");
	}
	/*********************
	 * Helper functions
	 ********************/
	
	private void nextLine() {
		write("\n");
	}
	
	private void push(int c) {
		writeln("\tpush "+c);
	}
	
	private void pushAx() {
		writeln("\tpush ax");
	}

	private void pushDx() {
		writeln("\tpush dx");
	}
	private void pushOffset(int offset) {
		writeln("\tpush word ptr[bp"+offset+"]");
	}
	
	private void popAx() {
		writeln("\tpop ax");
	}
	
	private void popBx() {
		writeln("\tpop bx");
	}
	
	private void mov(String r1, String r2) {
		writeln("\tmov "+r1+", "+r2);
	}
	
	private void mov(int offset) {
		writeln("\tmov word ptr [bp"+offset+"], ax");
	}
	
	private void mov(String r, int c) {
		writeln("\tmov "+r+", "+c);
	}
	
	private void add() {
		writeln("\tadd ax, bx");
	}
	
	private void div() {
		writeln("\tidiv bx");
	}
	
	private void mul() {
		writeln("\timul bx");
	}
	
	private void sub() {
		writeln("\tsub ax, bx");
	}
	
	private void sub(String r, int c) {
		writeln("\tsub "+r+", "+c);
	}
	
	private void and() {
		writeln("\tand ax, bx");
	}
	
	private void or() {
		writeln("\tor ax, bx");
	}
	
	private void not() {
		writeln("\tnot ax");
	}
	
	private void cmp() {
		writeln("\tcmp ax, bx");
	}
	
	private void cwd() {
		writeln("\tcwd");
	}
	
	private void je(int offset) {
		writeln("\tje $+"+offset);
	}
	
	private void jne(int offset) {
		writeln("\tjne $+"+offset);
	}
	
	private void jg(int offset) {
		writeln("\tjg $+"+offset);
	}
	
	private void jge(int offset) {
		writeln("\tjge $+"+offset);
	}
	
	private void jl(int offset) {
		writeln("\tjl $+"+offset);
	}
	
	private void jle(int offset) {
		writeln("\tjle $+"+offset);
	}
	
	private void jmp(int offset) {
		writeln("\tjmp $+"+offset);
	}
	
}
