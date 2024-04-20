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

	public void moveElement(String name, Directory newParent){
		// Move an element to a new parent
	}

	public boolean search(String name){
		// Search for an element
		return false;
	}

	public void printDirectoryTree(){
		// Print the directory tree
	}

	public void listContents(Directory directory){
		// List the contents of a directory
		for (var element : directory.getChildren()){
			System.out.println(element);
		}
	}
	public void sortDirectoryByDate(Directory directory){
		// Sort the contents of a directory by date
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
		String[] paths = path.split("/");
		for (var element : directory.getChildren()) {
			if (element.getName().equals(paths[0])) {
				if (paths.length == 1) {
					return (Directory)element;
				} else {
					return changeDirectory((Directory)element, path.substring(paths[0].length() + 1));
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
