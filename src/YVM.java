

import java.io.FileOutputStream;
import java.io.IOException;

public class YVM {
	protected FileOutputStream file;
	
	public YVM() {}
	
	public YVM(String fileName) throws IOException {
		file = new FileOutputStream(fileName+".yvm");
	}
	
	public void write(String str) {
		try {
			file.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeln(String str) {
		write(str+"\n");
	}
	
}
