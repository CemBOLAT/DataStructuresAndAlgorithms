import java.sql.Timestamp;
import java.io.PrintWriter;

public abstract class FileSystemElement {
    private final String        name;
    private final Timestamp     dateCreated;
    private FileSystemElement   parent;
    private String              path;

    public FileSystemElement(String name, Timestamp dateCreated, FileSystemElement parent, String path) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    public abstract void saveElement(PrintWriter writer);
    public abstract void updatePathAtMove(String path);
}
