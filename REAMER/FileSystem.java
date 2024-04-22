import java.sql.Timestamp;
import java.io.PrintWriter;

public class FileSystem {
	private final Directory	root;
	private static final int ASCII_ACK = 6;
	/**
	 * Creates a new file system.
	 *
	 * <p> The file system has a root directory. </p>
	 *
	 */
	public FileSystem() {
		root = new Directory("/", null);
	}

	/**
	 * Creates new file to parent directory.
	 *
	 * @param name the name of the file
	 * @param parent the parent directory
	 */
	public void createFile(String name, Directory parent){
		// Create a new file
		File newFile = new File(name, parent);
		parent.add(newFile);
	}
	/**
	 * Creates new file to parent directory.
	 *
	 * @param name the name of the file
	 * @param parent the parent directory
	 * @throws IllegalArgumentException if the file already exists
	 */
	public void createDirectory(String name, Directory parent) throws IllegalArgumentException{
		// Create a new directory
		// if exist already, print error message
		for (var e : parent.getChildren()){
			if (e.getName().equals(name)){
				throw new IllegalArgumentException("Directory already exists.");
			}
		}
		Directory newDirectory = new Directory(name, parent);
		parent.add(newDirectory);

	}
	/**
	 * Deletes a file or directory.
	 *
	 * @param name the name of the file or directory
	 * @param parent the parent directory
	 */
	public void deleteFileOrDirectory(String name, Directory parent){
		// Delete a element recursively
		for (var e : parent.getChildren()){
			if (e.getName().equals(name)){
				parent.remove(e);
				return;
			}
		}
	}

	/**
	 * Moves a file or directory to a new parent directory.
	 *
	 * @param name the name of the file or directory
	 * @param path the path of the new parent directory
	 * @param parent the parent directory
	 * @throws IllegalArgumentException if the file or directory does not exist or if the file or directory already exists in the new parent directory
	 */
	public void moveElement(String name, String path, Directory parent) throws IllegalArgumentException{
		// Move an element to a new parent

		FileSystemElement element = null;
		for (var e : parent.getChildren()){
			if (e.getName().equals(name)){
				element = e;
				break;
			}
		}

		if (element == null){
			throw new IllegalArgumentException("Element not found.");
		}
		Directory newParent = changeDirectory(root, path);
		for (var e : newParent.getChildren()){
			if (e.getName().equals(name)){
				throw new IllegalArgumentException("Element already exists in the new directory.");
			}
		}
		parent.singleRemove(element);
		newParent.add(element);
		if (element instanceof Directory){
			// Directory moved: Reports to /home/user/Documents
			System.out.println("Directory moved: " + element.getName() + " to " + path);
		}
		else {
			// File moved: Report.docx to /home/user/Documents/Reports
			System.out.println("File moved: " + element.getName() + " to " + path);
		}
	}

	/**
	 * search for an element in the file system recursively
	 * @param directory the directory to search in
	 * @param name the name of the element to search for
	 * @param f the number of elements found
	 * @return the number of elements found
	 */
	private int searchRec(Directory directory, String name, int f){
		// Search for an element in a directory recursively
		for (var e : directory.getChildren()){
			if (e.getName().equals(name)){
				e.print("Found: ");
				f += 1;
			}
			if (e instanceof Directory){
				f = searchRec((Directory)e, name, f);
			}
		}
		return f;
	}

	/**
	 * search for an element in the file system
	 * @param name the name of the element to search for
	 * @return true if the element is found, false otherwise
	 */
	public boolean search(String name){
		// Search for an element in the file system
		int f = searchRec(root, name, 0);
		return f > 0;
	}

	/**
	 * print the directory tree recursively
	 * <br>
	 * it calculates the path of the directory and prints the tree according to '/' in the path
	 * @param directory the directory to print
	 */
	public void printDirectoryTree(Directory directory){
		String path = getCurrentPath(directory);

		int nbrOfSpace = 0;
		if (!path.equals("/")){
			String[] token = path.split("/");
			for (int j = 0; j < token.length - 1; j++){
				for (int i = 0; i < nbrOfSpace; i++){
					System.out.print(" ");
				}
				System.out.println("* " + token[j] + "/");
				nbrOfSpace += 2;
			}
		}
		for (int i = 0; i < nbrOfSpace; i++){
			System.out.print(" ");
		}
		System.out.println((directory.getParent() == null) ? ("* / (Current Directory)") : ("* " + directory.getName() + "/ (Current Directory)"));
		for (var t : directory.getChildren()){
			for (int i = 0; i < nbrOfSpace + 2; i++){
				System.out.print(" ");
			}
			System.out.println(t);
		}
	}

	/**
	 * list the contents of a directory
	 *
	 * @param directory the directory to list the contents of
	 */
	public void listContents(Directory directory){
		// List the contents of a directory
		for (var e : directory.getChildren()){
			if (e instanceof File){
				System.out.println(e);
			} else {
				System.out.println(e);
			}
		}
	}
	/**
	 * sort the contents of a directory by date created
	 *
	 * @param directory the directory to sort the contents of
	 */
	public void sortDirectoryByDate(Directory directory){
		// Sort the contents of a directory by date created
		directory.sortByDate();
		for (var e : directory.getChildren()){
			if (e instanceof File){
				System.out.println(e + " (" + ((File)e).getDateCreated() + ")");
			} else {
				System.out.println(e + " (" + ((Directory)e).getDateCreated() + ")");
			}
		}
	}

	/**
	 * get the path of a directory recursively
	 * @param dir the directory to get the path of
	 * @param path the path of the directory
	 *
	 * @return the path of the directory
	 */
	private String getPathRec(Directory dir, String path){
		// Get the path of a directory recursively
		if (dir.getParent() == null)
			return path;
		return getPathRec((Directory)dir.getParent(), "/" + dir.getName() + path);
	}

	/**
	 * get the current path of a directory
	 * @param directory the directory to get the path of
	 * @return the current path of the directory
	 */
	public String getCurrentPath(Directory directory){
		// Get the current path of a directory recursively
		if (directory.getParent() == null)
			return "/";
		return getPathRec(directory, "");
	}

	/**
	 * change the current directory recursively
	 * @param directory the current directory
	 * @param path the path to change to
	 * @return the new directory
	 * @throws IllegalArgumentException if the path is invalid
	 */
	public Directory changeDirectory(Directory directory , String path) throws IllegalArgumentException{
		// Change the current directory recursively
		if (path.length() == 0){
			return directory;
		}
		else if (path.equals("/")){
			return root;
		}
		else if (path.equals(".")){
			return directory;
		}
		else if (path.equals("..")){
			if (directory.getParent() == null){
				return directory;
			}
			return (Directory)directory.getParent();
		}
		else if (path.charAt(0) == '/'){
			return changeDirectory(directory, path.substring(1));
		}
		else {
			String[] parts = path.split("/");
			for (var e : directory.getChildren()){
				if (e.getName().equals(parts[0])){
					if (e instanceof Directory){
						if (parts.length == 1){
							return (Directory)e;
						}
						return changeDirectory((Directory)e, path.substring(parts[0].length()));
					}
					else {
						throw new IllegalArgumentException("Invalid path.");
					}
				}
			}
		}
		throw new IllegalArgumentException("Invalid path.");
	}

	/**
	 * Returns the root directory.
	 * @return the root directory
	 */
	public Directory getRoot() {
		return root;
	}
}
