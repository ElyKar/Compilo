import java.io.FileOutputStream;
import java.io.IOException;


public class YVMasm extends YVM {
	
	int msgCounter = 0;
	
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
		entete.append("\textrn ecrch:proc, ligsuiv:proc\n");
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
		nextLine();
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
		nextLine();
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
		nextLine();
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
		nextLine();
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
		nextLine();
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
		nextLine();
	}
	
	/********************
	 * Functions for input and output
	 *******************/
	
	public void lireEnt(int offset){
		writeln("\t; lireEnt "+offset);
		lea("dx",offset);
		pushDx();
		call("lirent");
		nextLine();
	}
	
	public void aLaLigne(){
		writeln("\t; aLaLigne");
		call("ligsuiv");
		nextLine();
	}
	
	public void ecrireChaine(String s){
		writeln("\t; ecrireChaine "+s);
		writeln(".DATA");
		writeln("\tmess"+msgCounter+" DB " + s.substring(0, s.length() -1) + "$\"");
		writeln(".CODE");
		lea("dx","mess"+msgCounter);
		pushDx();
		call("ecrch");
		msgCounter++;
		nextLine();
	}
	
	public void ecrireEnt(){
		writeln("\t; ecrireEnt");
		call("ecrent");
		nextLine();
	}
	
	public void ecrireBool() {
		writeln("\t; ecrireBool");
		call("ecrbool");
		nextLine();
	}
	
	public void goTo(String label) {
		writeln("\t; goto "+label);
		jmp(label);
		nextLine();
	}
	
	public void iffaux(String label) {
		writeln("\t; iffaux "+label);
		popAx();
		cmp("ax","0");
		je(label);
		nextLine();
	}
	
	public void label(String label) {
		writeln("; "+label);
		writeln(label);
		nextLine();
	}
	
	public void call(String f) {
		writeln("\tcall "+f);
		nextLine();
	}
	
	public void ouvbloc(int n) {
		writeln("; ouvbloc "+n);
		enter(n);
		nextLine();
	}
	
	public void fermebloc(int n) {
		writeln("\t; fermeBloc "+n);
		leave();
		ret(4);
		nextLine();
	}
	
	public void ireturn(int offset) {
		writeln("\t; ireturn "+offset);
		popAx();
		mov(offset);
		nextLine();
	}
	
	public void reserveRetour() {
		writeln("\t; reserveRetour");
		sub("sp",2);
		nextLine();
	}
	
	/*********************
	 * Helper functions
	 ********************/
	
	private void nextLine() {
		write("\n");
	}
	
	private void push(int c) {
		writeln("\tpush word ptr "+c);
	}
	
	private void pushAx() {
		writeln("\tpush ax");
	}

	private void pushDx() {
		writeln("\tpush dx");
	}
	private void pushOffset(int offset) {
		writeln("\tpush word ptr [bp"+((offset<0) ? offset :"+"+offset)+"]");
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
	
	private void cmp(String r1, String r2) {
		writeln("\tcmp "+r1+", "+r2);
	}
	
	private void cwd() {
		writeln("\tcwd");
	}
	
	private void je(int offset) {
		writeln("\tje $+"+offset);
	}
	
	private void je(String label) {
		writeln("\tje "+label);
	}
	
	private void jne(int offset) {
		writeln("\tjne $+"+offset);
	}
	
	private void jne(String label) {
		writeln("\tjne "+label);
	}
	
	private void jg(int offset) {
		writeln("\tjg $+"+offset);
	}
	
	private void jg(String label) {
		writeln("\tjg "+label);
	}
	
	private void jge(int offset) {
		writeln("\tjge $+"+offset);
	}
	
	private void jge(String label) {
		writeln("\tjge "+label);
	}
	
	private void jl(int offset) {
		writeln("\tjl $+"+offset);
	}
	
	private void jl(String label) {
		writeln("\tjl "+label);
	}
	
	private void jle(int offset) {
		writeln("\tjle $+"+offset);
	}
	
	private void jle(String label) {
		writeln("\tjle "+label);
	}
	
	private void jmp(int offset) {
		writeln("\tjmp $+"+offset);
	}
	
	private void jmp(String label) {
		writeln("\tjmp "+label);
	}
	
	private void lea(String r1, String r2) {
		writeln("\tlea "+r1+", "+r2);
	}
	
	private void lea(String r, int offset) {
		writeln("\tlea "+r+", [bp"+((offset<0) ? offset :"+"+offset)+"]");
	}
	
	private void enter(int n) {
		writeln("\tenter "+n+",0");
	}
	
	private void ret(int n) {
		writeln("\tret "+n);
	}
	
	private void leave() {
		writeln("leave");
	}
	
}
