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
	public void deleteFile(File file){
		// Delete a file
	}

	public void deleteDirectory(Directory directory){
		// Delete a directory
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

	public String getCurrentPath(Directory directory){
		// Get the full path of an element
		return null;
	}

	public Directory changeDirectory(Directory directory , String path){
		System.out.println("****" + path);
		// Change the current directory to a new directory based on the path make it recursive
		String[] pathParts = path.split("/", 2);
		System.out.println("****" + pathParts[0]);
		System.out.println("****" + path);

		if (pathParts.length == 0){
			return directory;
		}
		for (var element : directory.getChildren()){
			if (element.getName().equals(pathParts[0])){
				if (element instanceof Directory){
					return changeDirectory((Directory) element, path.substring(pathParts[0].length() + 1));
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
