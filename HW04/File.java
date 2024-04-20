import java.sql.Timestamp;
import java.io.PrintWriter;

public class File extends FileSystemElement {
	public File(String name, Timestamp dateCreated, FileSystemElement parent) {
		super(name, dateCreated, parent, parent.getPath() + name);
	}

	@Override
	public String toString(){
		return String.format("%-17s", getName());
	}

	@Override
	public void saveElement(PrintWriter writer) {
		writer.print(getName() + " ");
		writer.print(getDateCreated().getTime() + " " );
		writer.println(getPath());
	}

	@Override
	public void updatePathAtMove(String path) {
		setPath(path + getName());
		System.out.println("File moved: " + getName() + " to " + path);
	}
}
