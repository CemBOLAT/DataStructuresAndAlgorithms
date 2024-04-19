import java.sql.Timestamp;

public class File extends FileSystemElement {
	public File(String name, Timestamp dateCreated, FileSystemElement parent) {
		super(name, dateCreated, parent, parent.getPath() + name);
	}

	@Override
	public String toString(){
		return String.format("%s", getName());
	}
}
