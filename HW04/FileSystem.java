import java.sql.Timestamp;

public class FileSystem {
	private Directory root;
	private Directory currentDirectory;

	public FileSystem() {
		root = new Directory("/", new Timestamp(System.currentTimeMillis()), null);
		currentDirectory = root;
	}

	public Directory getRoot() {
		return root;
	}

	public Directory getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(Directory currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

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
		if (option.isEmpty() || option.length() > 1 || (!option.equals("f") && !option.equals("d"))){
			throw new IllegalArgumentException("Invalid option.");
		}
		FileSystemElement fileSystemElement = null;
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
			// We can use equals() for exact match but the variable name is query so I assume partial match is way better
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
		Integer found = search(currentDirectory, query);
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
}
