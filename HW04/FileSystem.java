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

	private Directory findDirectory(Directory directory, String path) {
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

	public void changeDirectory(){
		printCurrentDirectory();
		String absolutePath = getStringUntil("Enter new directory path: ");
		if (absolutePath.equals("..")) {
			if (currentDirectory.getParent() != null) {
				currentDirectory = (Directory)currentDirectory.getParent();
				System.out.println("Directory changed to: " + currentDirectory.getPath());
			}
		} else if (absolutePath.equals("/")) {
			currentDirectory = root;
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

	public void createFileOrDirectory(){
		printCurrentDirectory();
		String option;
		do {
			System.out.print("Create file or directory (f/d): ");
			option = System.console().readLine();
			if (option.equals("f") && option.equals("d")) {
				System.out.println("Invalid option. Please enter 'f' or 'd'.");
			}
		}
		while (!option.equals("f") && !option.equals("d"));
		FileSystemElement fileSystemElement = null;
		if (option.equals("f")) {
			String name = getStringUntil("Enter name for new file: ");
			fileSystemElement = new File(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
			System.out.println("File created: " + fileSystemElement.getName());
		} else {
			String name = getStringUntil("Enter name for new directory: ");
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

	public void deleteFileOrDirectory(){
		printCurrentDirectory();
		String name = getStringUntil("Enter name of file or directory to delete: ");
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

	private void printCurrentDirectory(){
		System.out.println("Current directory: " + currentDirectory.getPath());
	}
	private String getStringUntil(String message){
		do {
			System.out.print(message);
			String input = System.console().readLine();
			if (input.isEmpty()) {
				System.out.println("Invalid input. Please try again.");
			} else {
				return input;
			}
		} while (true);
	}
}
