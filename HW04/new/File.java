import java.sql.Timestamp;
import java.io.PrintWriter;

public class File extends FileSystemElement {
	public File(String name, FileSystemElement parent) {
		super(name, parent);
	}

	@Override
	public String toString(){
		return String.format("%-15s", getName());
	}

	@Override
	public void print(String prefix) {
		System.out.println(prefix + getName());
	}
}
