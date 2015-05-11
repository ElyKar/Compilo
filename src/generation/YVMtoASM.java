package generation;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// CONFIGURE THE WORKSPACE FOR JAVA >= 1.7 !!
public class YVMtoASM {
	private YVMasm yvmasm;
	private BufferedReader reader;
	private static final String TOKENIZE_REGEX = "\"([^\"]*)\"|\'([^\']*)\'|(\\S+)";

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
				if(methodName.trim().length() != line.trim().length()){
					String argString = line.substring(methodName.length()+1);
					args = tokenize(argString);
				}
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
				//System.out.println(methodToCall.getName() + args.toString());
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
	
	private List<Object> tokenize(String args) {
		List<Object> tokens = new ArrayList<Object>();
	    Matcher m = Pattern.compile(TOKENIZE_REGEX).matcher(args);
	    while (m.find()) {
	        if (m.group(1) != null) {
	        	// Double-quoted token
	        	tokens.add(m.group(1));
	        } 
	        else if(m.group(2) != null){
	        		// Single quoted token
	        	tokens.add(m.group(2));
	        }
	        else {
	        	String token = m.group(3);
	        	try {
	        		int i = Integer.parseInt(token);
	        		tokens.add(i);
	        	} catch (NumberFormatException e) {
	        		tokens.add(token);
	        	}
	        }
	    }
	    return tokens;
	}
}
