import java.sql.Timestamp;
import java.util.List;
import java.util.LinkedList;

public class Directory extends FileSystemElement {
	private List<FileSystemElement> children;

	public Directory(String name, Timestamp dateCreated, FileSystemElement parent) {
		super(name, dateCreated, parent, (parent == null) ? "/" : parent.getPath() + name + "/");
		children = new LinkedList<>();
	}

	public List<FileSystemElement> getChildren() {
		return children;
	}

	public void add(FileSystemElement element) {
		children.add(element);
		element.setParent(this);
	}

	public void remove(FileSystemElement element) {
		children.remove(element);
		element.setParent(null);
	}

	@Override
	public String toString(){
		return String.format("* %s/", getName());
	}

	public FileSystemElement getChild(String name) {
		for (var element : children) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}
}
