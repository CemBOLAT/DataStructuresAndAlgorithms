import java.sql.Timestamp;
import java.io.PrintWriter;

public abstract class FileSystemElement {
    private final String        name;
    private final Timestamp     dateCreated;
    private FileSystemElement   parent;

    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.parent = parent;
    }

    public FileSystemElement(String name, FileSystemElement parent, Timestamp dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public FileSystemElement getParent() {
        return parent;
    }

    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    private String getPathRecursive() {
        if (getParent() == null) {
            return "";
        }
        return getParent().getPathRecursive() + "/" + getName();
    }

	public String getFullPath() {
		if (getParent() == null) {
			return "/";
		}
        return getPathRecursive();
	}


    public abstract void print(String prefix);
    public abstract void saveElement(PrintWriter writer);
}
