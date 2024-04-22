import java.sql.Timestamp;
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;

/**
 * Directory class represents a directory in the file system.
 * <p> A directory can contain other directories and files. </p>
 *
 * <p> A directory can be created with a name and a parent directory. </p>
 */

public class Directory extends FileSystemElement {
	private List<FileSystemElement> children;
	private static final int ASCII_ACK = 6;


	/**
	 * Creates a new directory with a name and a parent directory.
	 *
	 * @param name   the name of the directory
	 * @param parent the parent directory
	 */
	public Directory(String name, Directory parent) {
		super(name, parent);
		children = new LinkedList<FileSystemElement>();
	}

	/**
	 * Creates a new directory with a name, a parent directory, and a creation date.
	 *
	 * @param name        the name of the directory
	 * @param parent      the parent directory
	 * @param dateCreated the creation date of the directory
	 */
	public Directory(String name, Directory parent, Timestamp dateCreated) {
		super(name, parent, dateCreated);
		children = new LinkedList<FileSystemElement>();
	}

	/**
	 * Adds a child element to the directory.
	 *
	 * @param child the child element to add
	 */
	public void add(FileSystemElement child) {
		children.add(child);
		child.setParent(this);
	}

	/**
	 * Removes a child element from the directory recursively.
	 *
	 * @param child the child element to remove
	 */
	private void removeRec(FileSystemElement child){
		if (child instanceof Directory){
			Directory dir = (Directory) child;
			for (var e : dir.getChildren()){
				removeRec(e);
			}
		}
		children.remove(child);
		child.setParent(null);

	}

	/**
	 * Removes a child element from the directory.
	 *
	 * @param child the child element to remove
	 */
	public void remove(FileSystemElement child) {
		removeRec(child);
		if (child instanceof Directory) {
			System.out.println("Directory deleted: " + child.getName() + "/");
		} else {
			System.out.println("Directory deleted: " + child.getName());
		}
	}

	/**
	 * Removes a single child element from the directory.
	 *
	 * @param child the child element to remove
	 */
	public void singleRemove(FileSystemElement child) {
		children.remove(child);
		child.setParent(null);
	}

	/**
	 * Returns the name of the directory.
	 *
	 * @return the name of the directory
	 */
	@Override
	public String toString(){
		return String.format("* %-15s", getName() + "/");
	}

	/**
	 * Prints the directory and its children.
	 *
	 * @param prefix the prefix to print before the directory
	 */
	@Override
	public void print(String prefix) {
		System.out.println(prefix + getFullPath());
	}

	/**
	 * Sorts the children of the directory by name.
	 */
	public void sortByDate() {
		children.sort((a, b) -> a.getDateCreated().compareTo(b.getDateCreated()));
	}

	/**
	 * Returns the children of the directory.
	 *
	 * @return the children of the directory
	 */
	public List<FileSystemElement> getChildren() {
		return children;
	}

	/**
	 * Saves the directory and its children to a file.
	 *
	 * @param writer the PrintWriter to write to the file
	 */
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
