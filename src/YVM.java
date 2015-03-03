

import java.io.FileOutputStream;
import java.io.IOException;

public class YVM {
	private FileOutputStream file;
	
	public YVM() throws IOException {
		file = new FileOutputStream("output.yvm");
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
