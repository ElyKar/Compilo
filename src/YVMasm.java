import java.io.FileOutputStream;
import java.io.IOException;


public class YVMasm extends YVM {
	
	public YVMasm(String fileName) throws IOException {
		file = new FileOutputStream(fileName+".asm");
	}
	
	/*********************
	 * Functions for writing the header and footer of the .asm file
	 ********************/
	
	public void entete() {
		StringBuilder entete = new StringBuilder();
		entete.append("\t; entete\n");
		entete.append("\textrn lirent:proc, ecrent:proc\n");
		entete.append("\textrn ecrbool:proc\n");
		entete.append("\textrn ecrchn:proc, ligsuiv:proc\n");
		entete.append(".model SMALL\n");
		entete.append(".586\n\n");
		entete.append(".CODE\n");
		entete.append("debut :\n");
		entete.append("\tSTARTUPCODE\n");
		writeln(entete.toString());
	}
	
	public void queue() {
		StringBuilder str = new StringBuilder();
		str.append("\t; queue\n");
		str.append("\tnop\n");
		str.append("\texitcode\n");
		str.append("\tend debut\n");
		writeln(str.toString());
	}
	
	public void ouvrePrinc(int n) {
		writeln("; ouvrePrinc "+n);
		mov("bp", "sp");
		sub("sp", n);
		nextLine();
	}
	
	/*********************
	 * Functions for pushing values into the stack
	 ********************/
	
	public void iconst(int c) {
		writeln("\t; iconst "+c);
		push(c);
		nextLine();
	}
	
	public void iload(int offset) {
		writeln("\t; iload "+offset);
		pushOffset(offset);
		nextLine();
	}
	
	public void istore(int offset) {
		writeln("\t; istore "+offset);
		popAx();
		mov(offset);
		nextLine();
	}
	
	/*********************
	 * Operations on integers
	 ********************/
	
	public void iadd() {
		writeln("\t; iadd");
		popBx();
		popAx();
		add();
		pushAx();
		nextLine();
	}
	
	public void isub() {
		writeln("\t; isub");
		popBx(); 
		popAx();
		sub();
		pushAx();
		nextLine();
	}
	
	public void imul() {
		writeln("\t; imul");
		popBx();
		popAx();
		mul();
		pushAx();
		nextLine();
	}
	
	public void idiv() {
		writeln("\t; idiv");
		popBx();
		popAx();
		cwd();
		div();
		pushAx();
		nextLine();
	}
	
	public void ineg() {
		writeln("\t; ineg");
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
		writeln("\t; iand");
		popBx();
		popAx();
		and();
		pushAx();
		nextLine();
	}
	
	public void ior() {
		writeln("\t; ior");
		popBx();
		popAx();
		or();
		pushAx();
		nextLine();
	}
	
	public void inot() {
		writeln("\t; inot");
		popAx();
		not();
		pushAx();
		nextLine();
	}
	
	/*********************
	 * Comparisons
	 ********************/
	
	public void iegal() {
		writeln("\t; iegal");
		popBx();
		popAx();
		cmp();
		jne(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void idiff() {
		writeln("\t; idiff");
		popBx();
		popAx();
		cmp();
		je(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void iinf() {
		writeln("\t; iinf");
		popBx();
		popAx();
		cmp();
		jge(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void iinfegal() {
		writeln("\t; iinfegal");
		popBx();
		popAx();
		cmp();
		jg(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void isup() {
		writeln("\t; isup");
		popBx();
		popAx();
		cmp();
		jle(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	public void isupegal() {
		writeln("\t; isupegal");
		popBx();
		popAx();
		cmp();
		jl(6);
		push(-1);
		jmp(4);
		push(0);
	}
	
	/*********************
	 * Helper functions
	 ********************/
	
	private void nextLine() {
		write("\n");
	}
	
	private void push(int c) {
		writeln("\tpush "+c+"\n");
	}
	
	private void pushAx() {
		writeln("\tpush ax\n");
	}
	
	private void pushOffset(int offset) {
		writeln("\tpush word ptr [bp"+offset+"]\n");
	}
	
	private void popAx() {
		writeln("\tpop ax\n");
	}
	
	private void popBx() {
		writeln("\tpop bx\n");
	}
	
	private void mov(String r1, String r2) {
		writeln("\tmov "+r1+", "+r2+"\n");
	}
	
	private void mov(int offset) {
		writeln("\tmov word ptr [bp"+offset+"], ax\n");
	}
	
	private void mov(String r, int c) {
		writeln("\tmov "+r+", "+c+"\n");
	}
	
	private void add() {
		writeln("\tadd ax, bx\n");
	}
	
	private void div() {
		writeln("\tidiv bx\n");
	}
	
	private void mul() {
		writeln("\timul bx\n");
	}
	
	private void sub() {
		writeln("\tsub ax, bx\n");
	}
	
	private void sub(String r, int c) {
		writeln("\tsub "+r+", "+c+"\n");
	}
	
	private void and() {
		writeln("\tand ax, bx\n");
	}
	
	private void or() {
		writeln("\tor ax, bx\n");
	}
	
	private void not() {
		writeln("\tnot ax");
	}
	
	private void cmp() {
		writeln("\tcmp ax, bx\n");
	}
	
	private void cwd() {
		writeln("\tcwd\n");
	}
	
	private void je(int offset) {
		writeln("\tje $+"+offset+"\n");
	}
	
	private void jne(int offset) {
		writeln("\tjne $+"+offset+"\n");
	}
	
	private void jg(int offset) {
		writeln("\tjg $+"+offset+"\n");
	}
	
	private void jge(int offset) {
		writeln("\tjge $+"+offset+"\n");
	}
	
	private void jl(int offset) {
		writeln("\tjl $+"+offset+"\n");
	}
	
	private void jle(int offset) {
		writeln("\tjle $+"+offset+"\n");
	}
	
	private void jmp(int offset) {
		writeln("\tjmp $+"+offset+"\n");
	}
	
}
