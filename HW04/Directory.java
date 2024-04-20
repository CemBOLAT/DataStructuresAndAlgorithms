import java.sql.Timestamp;
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;

/*
 * Represents a directory in the file system.
 <br>
 * A directory can contain other directories and files.
*/

public class Directory extends FileSystemElement {
	private List<FileSystemElement> children;

	/**
	 * Creates a new directory.
	 * @param name the name of the directory
	 * @param dateCreated the date the directory was created
	 * @param parent the parent directory
	 */
	public Directory(String name, Timestamp dateCreated, FileSystemElement parent) {
		super(name, dateCreated, parent, (parent == null) ? "/" : parent.getPath() + name + "/");
		children = new LinkedList<>();
	}
	/**
	 * Returns the children of the directory.
	 * @return the children of the directory
	 */
	public List<FileSystemElement> getChildren() {
		return children;
	}
	/**
	 * Adds a file or directory to the directory.
	 * @param element the file or directory to add
	 */
	public void add(FileSystemElement element) {
		children.add(element);
		element.setParent(this);
	}
	/**
	 * Removes a file or directory from the directory.
	 * @param element the file or directory to remove
	 */
	public void remove(FileSystemElement element) {
		children.remove(element);
		element.setParent(null);
	}
	/**
	 * Returns the child with the specified name.
	 * @param name the name of the child
	 * @return the child with the specified name
	 */
	@Override
	public String toString(){
		return String.format("* %-15s", getName() + "/");
	}
	/**
	 * Returns the child with the specified name.
	 * @param name the name of the child
	 * @return the child with the specified name
	 */
	public FileSystemElement getChild(String name) {
		for (var element : children) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}
	/**
	 * Sorts the children of the directory by date created.
	*/
	public void sortChildrenByDateCreated() {
		children.sort((a, b) -> a.getDateCreated().compareTo(b.getDateCreated())); // lambda expression to sort by date
	}
	/**
	 * Prints the children of the directory to the console.
	*/
	public void printChildrenToDate() {
		children.forEach( (element) -> {
			System.out.print(element + " (" + element.getDateCreated() + ")\n");
		});
	}
	/**
	 * Saves the directory to a file.
	 * @param writer the writer to use
	*/
	@Override
	public void saveElement(PrintWriter writer) {
		writer.print(getName() + " ");
		writer.print(getDateCreated().getTime() + " " );
		writer.println(getPath());
		for (var child : getChildren()) {
			child.saveElement(writer);
		}
	}

	private void updatePathAtRecursiveMove(String path) {
		setPath(path + getName() + "/");
		for (var element : getChildren()) {
			if (element instanceof Directory) {
				((Directory)element).updatePathAtRecursiveMove(path + getName() + "/");
			} else {
				element.setPath(path + getName() + "/" + element.getName());
			}
		}
	}
	@Override
	public void updatePathAtMove(String path) {
		setPath(path + getName() + "/");
		updatePathAtRecursiveMove(path);
		System.out.println("Directory moved: " + getName() + " to " + path);
	}
}
