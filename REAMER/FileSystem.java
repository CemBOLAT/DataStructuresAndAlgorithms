import java.sql.Timestamp;
import java.io.PrintWriter;

public class FileSystem {
	private final Directory	root;
	private static final int ASCII_ACK = 6;
	/**
	 * Creates a new file system.
	 */
	public FileSystem() {
		root = new Directory("/", null);
	}

	public void createFile(String name, Directory parent){
		// Create a new file
		File newFile = new File(name, parent);
		parent.add(newFile);
	}
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
	public void deleteFileOrDirectory(String name, Directory parent){
		// Delete a element recursively
		for (var e : parent.getChildren()){
			if (e.getName().equals(name)){
				if (e instanceof Directory){
					System.out.println("Directory deleted: " + e.getName() + "/");
				}
				else {
					System.out.println("File deleted: " + e.getName());
				}
				parent.remove(e);
				return;
			}
		}
	}

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

	private int searchRec(Directory directory, String name, int f){
		// Search for an element in a directory recursively
		for (var e : directory.getChildren()){
			if (e.getName().equals(name)){
				System.out.println("Found: " + getCurrentPath((Directory)e.getParent()) + "/" + e.getName());
				f += 1;
			}
			if (e instanceof Directory){
				f = searchRec((Directory)e, name, f);
			}
		}
		return f;
	}

	public boolean search(String name){
		// Search for an element in the file system
		int f = searchRec(root, name, 0);
		return f > 0;
	}

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

	public void listContents(Directory directory){
		// List the contents of a directory
		for (var e : directory.getChildren()){
			if (e instanceof File){
				e.print("");
			} else {
				System.out.println("* " + e.getName() + "/");
			}
		}

	}
	public void sortDirectoryByDate(Directory directory){
		// Sort the contents of a directory by date created
		directory.sortByDate();
		for (var e : directory.getChildren()){
			if (e instanceof File){
				System.out.println(e.getName() + " (" + ((File)e).getDateCreated() + ")");
			} else {
				System.out.println("* " + e.getName() + "/" + " (" + ((Directory)e).getDateCreated() + ")");
			}
		}
	}

	private String getPathRec(Directory dir, String path){
		// Get the path of a directory recursively
		if (dir.getParent() == null)
			return path;
		return getPathRec((Directory)dir.getParent(), "/" + dir.getName() + path);
	}

	public String getCurrentPath(Directory directory){
		// Get the current path of a directory recursively
		if (directory.getParent() == null)
			return "/";
		return getPathRec(directory, "");
	}


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
