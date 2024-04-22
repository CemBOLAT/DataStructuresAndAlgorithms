import java.sql.Timestamp;
import java.io.PrintWriter;

public class File extends FileSystemElement {

	private static final int ASCII_ACK = 6;

	public File(String name, FileSystemElement parent) {
		super(name, parent);
	}

	public File(String name, FileSystemElement parent, Timestamp dateCreated) {
		super(name, parent, dateCreated);
	}

	@Override
	public String toString(){
		return String.format("%-15s", getName());
	}

	@Override
	public void print(String prefix) {
		System.out.println(prefix + getName());
	}

	@Override
	public void saveElement(PrintWriter writer){
		writer.println(getName() + " " + getDateCreated().getTime() + " " + getParent().getFullPath());
	}
}
