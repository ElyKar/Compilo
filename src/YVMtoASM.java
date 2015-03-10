import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// CONFIGURE THE WORKSPACE FOR JAVA >= 1.7 !!
public class YVMtoASM {
	private YVMasm yvmasm;
	private BufferedReader reader;
	
	public YVMtoASM(String yvmfile, YVMasm yvmasm) throws IOException {
		this.yvmasm = yvmasm;
		this.reader = new BufferedReader(new FileReader(yvmfile));
	}

	public void toAsm() throws IOException {
		String line;
		String token;
		StringTokenizer tokenizer;
		String specvar = "";
		String methodName;
		String emptyLine = "^[\t| ]*$";
		Method methodToCall = null;
		Method label = null;
		Map<String, Method> map = new HashMap<>();
		List<Object> args = new ArrayList<Object>();
		Method[] methods = null;
		
		//get YVMAsm method list
		try {
			methods = this.yvmasm.getClass().getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		//constructs the Map
		for (Method m : methods) {
			if (m.getName().equals("goTo")) map.put("goto", m);
			if (m.getName().equals("label")) label = m;
			map.put(m.getName(), m);
		}
		
		while((line = this.reader.readLine()) != null) {
			
			if (!line.matches(emptyLine)) { //We process the line only if it's not empty
				
				specvar = "";
				//separate tokens by space
				tokenizer = new StringTokenizer(line, " ");
				
				//get method name
				methodName = tokenizer.nextToken();
				
				//construct args list
				while(tokenizer.hasMoreTokens()) {
					token = tokenizer.nextToken();
					try{
						args.add(Integer.parseInt(token));
					} catch(NumberFormatException e){
						specvar += token;
					}
				}
				
				if(!specvar.equals(""))
					args.add(specvar);
			
				//find the appropriate method to call
				if (methodName.charAt(methodName.length()-1)==':') {
					methodToCall = label; //Handles the case of labels
					args.add(methodName);
				} else {
					methodToCall = map.get(methodName);
				}
				
				if (methodToCall == null) {
					throw new IllegalArgumentException("No " + methodName + " method in YVMasm");
				}
				
				
				//call the appropriate YVMasm method
				System.out.println(methodToCall.getName() + args.toString());
				try {
					methodToCall.invoke(this.yvmasm, (Object[]) args.toArray());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				
				//clear the argument list for the next line
				args.clear();
				methodToCall = null;
			}
		}
		this.reader.close();
	}
}
