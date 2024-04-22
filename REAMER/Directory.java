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

	public void removeRec(FileSystemElement child){
		if (child instanceof Directory){
			Directory dir = (Directory) child;
			for (var e : dir.getChildren()){
				removeRec(e);
			}
		}
		children.remove(child);
		child.setParent(null);

	}

	public void remove(FileSystemElement child) {
		removeRec(child);
		if (child instanceof Directory) {
			System.out.println("Directory deleted: " + child.getName() + "/");
		} else {
			System.out.println("File deleted: " + child.getName());
		}
	}


	public void singleRemove(FileSystemElement child) {
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

	public void sortByDate() {
		children.sort((a, b) -> a.getDateCreated().compareTo(b.getDateCreated()));
	}

	public List<FileSystemElement> getChildren() {
		return children;
	}

	@Override
	public void saveElement(PrintWriter writer) {
		if (getParent() != null) {
			writer.println((char)ASCII_ACK + getName() + " " + getDateCreated().getTime() + " " + getParent().getFullPath());
		}
		for (var child : children) {
			child.saveElement(writer);
		}
	}
}
