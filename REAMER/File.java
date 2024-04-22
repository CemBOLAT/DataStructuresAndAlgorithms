import java.sql.Timestamp;
import java.io.PrintWriter;

public class File extends FileSystemElement {

	private static final int ASCII_ACK = 6;

	/**
	 * Creates a new file with a name and a parent directory.
	 *
	 * @param name   the name of the file
	 * @param parent the parent directory
	 */
	public File(String name, FileSystemElement parent) {
		super(name, parent);
	}


	/**
	 * Creates a new file with a name, a parent directory, and a creation date.
	 *
	 * @param name        the name of the file
	 * @param parent      the parent directory
	 * @param dateCreated the creation date of the file
	 */
	public File(String name, FileSystemElement parent, Timestamp dateCreated) {
		super(name, parent, dateCreated);
	}

	/**
	 * Returns the name of the file.
	 *
	 * @return the name of the file
	 */
	@Override
	public String toString(){
		return String.format("%-17s", getName());
	}

	/**
	 * Prints the file with a prefix.
	 *
	 * @param prefix the prefix to print
	 */
	@Override
	public void print(String prefix) {
		System.out.println(prefix + getFullPath());
	}

	/**
	 * Saves the file to a file.
	 *
	 * @param writer the writer to save the file
	 */
	@Override
	public void saveElement(PrintWriter writer){
		writer.println(getName() + " " + getDateCreated().getTime() + " " + getParent().getFullPath());
	}
}
