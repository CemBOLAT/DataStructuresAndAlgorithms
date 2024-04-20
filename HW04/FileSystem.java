import java.sql.Timestamp;

public class FileSystem {
	private final Directory	root;
	private Directory		currentDirectory;

	/**
	 * Creates a new file system.
	 */
	public FileSystem() {
		root = new Directory("/", new Timestamp(System.currentTimeMillis()), null);
		currentDirectory = root;
	}

	/**
	 * Returns the root directory.
	 * @return the root directory
	 */
	public Directory getRoot() {
		return root;
	}

	/**
	 * Returns the current directory.
	 * @return the current directory
	 */
	public Directory getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * Sets the current directory.
	 * @param currentDirectory the current directory
	 */
	public void setCurrentDirectory(Directory currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
	/**
	 * Finds a directory with the specified path.
	 * @param directory the directory to search
	 * @param path the path of the directory to find
	 * @return the directory with the specified path or null if not found
	 */
	public Directory findDirectory(Directory directory, String path) {
		if (directory.getPath().equals(path)) {
			return directory;
		}
		for (var element : directory.getChildren()) {
			if (element instanceof Directory) {
				Directory found = findDirectory((Directory)element, path);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}
	/**
	 * <p>Changes the current directory.</p>
	 * <p>When the user enters "..", the current directory is changed to the parent directory.</p>
	 * <p>When the user enters "/", the current directory is changed to the root directory.</p>
	 * <p>The operation only works for absolute paths.</p>
	 * @throws IllegalArgumentException if the directory does not exist
	 */
	public void changeDirectory() throws IllegalArgumentException {
		printCurrentDirectory();
		String absolutePath = getString("Enter new directory path: ");
		if (absolutePath.equals("..")) {
			if (currentDirectory.getParent() != null) {
				currentDirectory = (Directory)currentDirectory.getParent();
				System.out.println("Directory changed to: " + currentDirectory.getPath());
			}
		} else if (absolutePath.equals("/")) {
			currentDirectory = root;
			System.out.println("Directory changed to: /");
		} else if (!absolutePath.equals(".")){
			Directory newDirectory = findDirectory(root, absolutePath);
			if (newDirectory != null) {
				currentDirectory = newDirectory;
				System.out.println("Directory changed to: " + currentDirectory.getPath());
			} else {
				System.out.println("Directory not found.");
			}
		}
	}

	public void createFileOrDirectory() throws IllegalArgumentException {
		printCurrentDirectory();
		String option = getString("Create file or directory (f/d): ");
		if (option.length() != 1 || (!option.equals("f") && !option.equals("d"))){
			throw new IllegalArgumentException("Invalid option.");
		}
		FileSystemElement fileSystemElement;
		if (option.equals("f")) {
			String name = getString("Enter name for new file: ");
			fileSystemElement = new File(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
			System.out.println("File created: " + fileSystemElement.getName());
		} else {
			String name = getString("Enter name for new directory: ");
			fileSystemElement = new Directory(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
			System.out.println("Directory created: " + fileSystemElement.getName() + "/" );
		}
		currentDirectory.add(fileSystemElement);
	}

	public void listDirectoryContents(){
		System.out.println("Listing contents of " + currentDirectory.getPath());
		for (var element : currentDirectory.getChildren()) {
			System.out.println(element);
		}
	}

	public void deleteFileOrDirectory() throws IllegalArgumentException {
		printCurrentDirectory();
		String name = getString("Enter name of file or directory to delete: ");
		FileSystemElement element = currentDirectory.getChild(name);
		if (element == null) {
			System.out.println("File or directory not found.");
		} else {
			currentDirectory.remove(element);
			if (element instanceof File) {
				System.out.println("File deleted: " + element.getName());
			} else {
				System.out.println("Directory deleted: " + element.getName() + "/");
			}
		}
	}

	private Integer search(Directory directory, String query) {
		Integer found = 0;
		for (var element : directory.getChildren()) {
			// We can use equals() for exact match but the variable name is query, so I assume partial match is way better
			if (element.getName().contains(query)) {
				System.out.println("Found: " + element.getPath());
				found++;
			}
			if (element instanceof Directory) {
				found += search((Directory)element, query);
			}
		}
		return found;
	}

	public void searchFileOrDirectory() throws IllegalArgumentException {
		String query = getString("Search query: ");
		System.out.println("Searching from root...");
		Integer found = search(root, query);
		if (found == 0) {
			System.out.println("No files or directories found.");
		}
	}

	private void printCurrentDirectory(){
		System.out.println("Current directory: " + currentDirectory.getPath());
	}
	private String getString(String message) throws IllegalArgumentException {
		System.out.print(message);
		String input = System.console().readLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException("Input cannot be empty.");
		} else {
			return input;
		}
	}
	public void moveFileOrDirectory(){
		printCurrentDirectory();
		String name = getString("Enter the name of file/directory to move: ");
		FileSystemElement element = currentDirectory.getChild(name);
		if (element == null) {
			System.out.println("File or directory not found.");
		} else {
			String newPath = getString("Enter new directory path: ");
			Directory newDirectory = findDirectory(root, newPath);
			if (newDirectory != null) {
				currentDirectory.remove(element);
				newDirectory.add(element);
				element.updatePathAtMove(newDirectory.getPath());
			} else {
				System.out.println("Directory not found.");
			}
		}

	}

	public void sortContentsByDateCreated(){
		System.out.println("Sorted contents of " + currentDirectory.getPath() + " by date created:");
		currentDirectory.sortChildrenByDateCreated();
		currentDirectory.printChildrenToDate();
	}
	public void printDirectoryTree() {
		System.out.println("Path to current directory from root: ");
		int		nbrOfSpace = 0;
		if (!currentDirectory.getPath().equals("/")){
			String[] token = currentDirectory.getPath().split("/");
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
		System.out.println((currentDirectory.getParent() == null) ? ("* / (Current Directory)") : (currentDirectory + " (Current Directory)"));
		for (var t : currentDirectory.getChildren()){
			for (int i = 0; i < nbrOfSpace + 2; i++){
				System.out.print(" ");
			}
			System.out.println(t);
		}
	}
}
