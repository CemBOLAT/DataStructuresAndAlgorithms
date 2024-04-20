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
		File file = new File(name, parent);
		parent.add(file);
		file.print("File created: ");
	}
	public void createDirectory(String name, Directory parent){
		// Create a new directory
		Directory directory = new Directory(name, parent);
		parent.add(directory);
		directory.print("Directory created: ");
	}
	public void deleteFileOrDirectory(String name, Directory parent){
		// Delete a element
		for (var element : parent.getChildren()){
			if (element.getName().equals(name)){
				parent.remove(element);
				if (element instanceof Directory){
					element.print("Directory deleted: ");
				}
				else{
					element.print("File deleted: ");
				}
				return;
			}
		}
	}

	public void moveElement(String name, String path, Directory parent){
		// Move an element to a new parent
		FileSystemElement moved = searchElement(parent, name);
		if (moved == null){
			System.out.println("Element not found.");
			return;
		}
		FileSystemElement newParent = searchElement(parent, path);
		if (newParent == null || !(newParent instanceof Directory)){
			System.out.println("New parent not found.");
			return;
		}

		parent.remove(moved);
		((Directory)newParent).add(moved);
		if (moved instanceof Directory){
			moved.print("Directory moved " + name + " to " + path + ": ");
		}
		else{
			moved.print("File moved " + name + " to " + path + ": ");
		}
	}

	private int searchRec(Directory directory, String name, int result){
		// Search for an element recursively
		for (var element : directory.getChildren()){
			if (element.getName().equals(name)){
				result += 1;
				if (element instanceof File)
					System.out.println("Found: " + getCurrentPath(directory) + name);
				else
					System.out.println("Found: " + getCurrentPath(directory) + name + "/");
			}
			if (element instanceof Directory){
				result = searchRec((Directory)element, name, result);
			}
		}
		return result;
	}
	public boolean search(String name){
		int result = searchRec(root, name, 0);
		return result != 0;
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
		for (var element : directory.getChildren()){
			System.out.println(element);
		}
	}
	public void sortDirectoryByDate(Directory directory){
		// Sort the contents of a directory by date
		directory.sortByDateCreated(); // its change the list !
		for (var element : directory.getChildren()){
			System.out.println(element + " (" + element.getDateCreated() + ")");
		}
	}

	private String getCurrentPathRecursively(Directory directory, String path){
		// Get the current path of a directory recursively
		if (directory.getParent() == null){
			return "/" + path;
		}
		return getCurrentPathRecursively((Directory)directory.getParent(), directory.getName() + "/" + path);
	}

	public String getCurrentPath(Directory directory){
		// Get the current path of a directory recursively
		return getCurrentPathRecursively(directory, "");
	}

	public Directory changeDirectory(Directory directory , String path){
		// Change the current directory to a new directory based on the path make it recursive
		if (path.equals("/")){
			return root;
		}
		else if (path.equals("..")){
			return (directory.getParent() == null) ? directory : (Directory)directory.getParent();
		}
		else{
			FileSystemElement el  = searchElement(directory, path);
			if (el != null && el instanceof Directory){
				return (Directory)el;
			}
		}
		return null;
	}

	private FileSystemElement searchElement(Directory directory, String absolutePath){
		// Search for an element recursively
		if (absolutePath.equals("/")){
			return root;
		}
		String[] paths = absolutePath.split("/");
		for (var element : directory.getChildren()) {
			if (element.getName().equals(paths[0])) {
				if (paths.length == 1) {
					return element;
				} else {
					return changeDirectory((Directory)element, absolutePath.substring(paths[0].length() + 1));
				}
			}
		}
		return null;
	}


	/**
	 * Returns the root directory.
	 * @return the root directory
	 */
	public Directory getRoot() {
		return root;
	}
}
