import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
		StringTokenizer tokenizer;
		String methodName;
		Method methodToCall = null;
		List<Integer> args = new ArrayList<Integer>();
		Method[] methods = null;
		
		//get YVMAsm method list
		try {
			methods = this.yvmasm.getClass().getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		while((line = this.reader.readLine()) != null) {
			//separate tokens by space
			tokenizer = new StringTokenizer(line, " ");
			
			//get method name
			methodName = tokenizer.nextToken();
			
			//construct args list
			while(tokenizer.hasMoreTokens()) {
				args.add(Integer.parseInt(tokenizer.nextToken()));
			}
			
			//find the method to call
			for(Method m : methods) {
				if(m.getName().equals(methodName)) {
					methodToCall = m;
					break;
				}
			}
			
			if(methodToCall == null) {
				throw new IllegalArgumentException("No " + methodName + " method in YVMasm");
			}
			
			//call the appropriate YVMasm method
			System.out.println(methodToCall.getName() + args.toString());
			try {
				methodToCall.invoke(this.yvmasm, (Object[]) args.toArray(new Integer[args.size()]));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//clear the argument list for the next line
			args.clear();
			methodToCall = null;
		}
		this.reader.close();
	}
}
