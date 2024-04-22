import java.sql.Timestamp;
import java.io.PrintWriter;

/**
 * Represents a file system element.
 *
 */
public abstract class FileSystemElement {
    private final String        name;
    private final Timestamp     dateCreated;
    private FileSystemElement   parent;

    /**
     * Creates a new file system element.
     * @param name the name of the file system element
     * @param parent the parent directory
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.parent = parent;
    }

    /**
     * Creates a new file system element.
     * @param name the name of the file system element
     * @param parent the parent directory
     * @param dateCreated the date created
     */
    public FileSystemElement(String name, FileSystemElement parent, Timestamp dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    /**
     * Returns the name of the file system element.
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date created.
     * @return the date created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent directory.
     * @return the parent directory
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory.
     * @param parent the parent directory
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Returns the path of the file system element.
     * @return the path of the file system element
     */
    private String getPathRecursive() {
        if (getParent() == null) {
            return "";
        }
        return getParent().getPathRecursive() + "/" + getName();
    }

    /**
     * Returns the full path of the file system element.
     * @return the full path of the file system element
     */
	public String getFullPath() {
		if (getParent() == null) {
			return "/";
		}
        return getPathRecursive();
	}


    public abstract void print(String prefix);
    public abstract void saveElement(PrintWriter writer);
}
