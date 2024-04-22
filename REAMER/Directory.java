import java.sql.Timestamp;
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;

public class Directory extends FileSystemElement {
	private List<FileSystemElement> children;
	private static final int ASCII_ACK = 6;

	public Directory(String name, Directory parent) {
		super(name, parent);
		children = new LinkedList<FileSystemElement>();
	}

	public Directory(String name, Directory parent, Timestamp dateCreated) {
		super(name, parent, dateCreated);
		children = new LinkedList<FileSystemElement>();
	}

	public void add(FileSystemElement child) {
		children.add(child);
		child.setParent(this);
	}

	public void remove(FileSystemElement child) {
		children.remove(child);
		child.setParent(null);
	}

	@Override
	public String toString(){
		return String.format("* %-15s", getName() + "/");
	}

	@Override
	public void print(String prefix) {
		System.out.println(prefix + getName() + "/");
		for (var child : children) {
			child.print(prefix + "  ");
		}
	}

	public List<FileSystemElement> getChildren() {
		return children;
	}
}
