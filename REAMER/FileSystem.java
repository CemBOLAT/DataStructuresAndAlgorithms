import java.sql.Timestamp;

public class FileSystem {
	private final Directory	root;

	/**
	 * Creates a new file system.
	 */
	public FileSystem() {
		root = new Directory("/", null);
	}

	public void createFile(String name, Directory parent){
		// Create a new file

	}
	public void createDirectory(String name, Directory parent){
		// Create a new directory

	}
	public void deleteFileOrDirectory(String name, Directory parent){
		// Delete a element
	}

	public void moveElement(String name, String path, Directory parent){
		// Move an element to a new parent

	}

	public boolean search(String name){

	}

	public void printDirectoryTree(Directory directory){

	}

	public void listContents(Directory directory){

	}
	public void sortDirectoryByDate(Directory directory){
	}

	public String getCurrentPath(Directory directory){
		// Get the current path of a directory recursively
	}

	public Directory changeDirectory(Directory directory , String path){
	}

	private FileSystemElement searchElement(Directory directory, String absolutePath){
		// Search for an element in a directory
	}


	/**
	 * Returns the root directory.
	 * @return the root directory
	 */
	public Directory getRoot() {
		return root;
	}
}
