import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.Timestamp;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class Main {

	private static final String FILE_SYSTEM_FILE = "filesystem.txt";
	private static Directory currentDirectory;
	private static FileSystem fileSystem = new FileSystem();
	private static final int ASCII_ACK = 6;

	public static void main(String[] args){
		Main main = new Main();

		currentDirectory = fileSystem.getRoot();
		loadFileSystem(fileSystem);
		boolean exit = false;
		while (!exit){
			main.printMenu();
			try {
				int option = Integer.parseInt(System.console().readLine());
				switch (option){
					case 1:
						changeDirectory();
						break;
					case 2:
						listDirectoryContents();
						break;
					case 3:
						createFileOrDirectory();
						break;
					case 4:
						deleteFileOrDirectory();
						break;
					case 5:
						moveFileOrDirectory();
						break;
					case 6:
						searchFileOrDirectory();
						break;
					case 7:
						printDirectoryTree();
						break;
					case 8:
						sortContentsByDateCreated();
						break;
					case 9:
						exit = true;
						saverFileSystem();
						System.out.println("Saving and Exiting...");
						break;
					default:
						System.out.println("Invalid option. Please try again.");
				}
			} catch (NumberFormatException e){
				System.out.println("Invalid option. Please try again.");
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

	private static String getString(String message) throws IllegalArgumentException {
		System.out.print(message);
		String input = System.console().readLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException("Input cannot be empty.");
		} else {
			return input;
		}
	}

	private static void printDirectoryTree(){
		System.out.println("Path to current directory from root: ");
		fileSystem.printDirectoryTree(currentDirectory);
	}

	private static void changeDirectory(){
		currentDirectory.print("Current directory: ");
		String path = getString("Enter new directory path: ");
		currentDirectory = fileSystem.changeDirectory(fileSystem.getRoot(), path);
		currentDirectory.print("Directory changed to: ");
	}

	private static void searchFileOrDirectory(){
		String name = getString("Search query: ");
		System.out.println("Searching from root...");
		if (!fileSystem.search(name)){
			System.out.println("Element not found: " + name);
		}
	}

	private static void listDirectoryContents(){
		currentDirectory.print("Listing contents of ");
		fileSystem.listContents(currentDirectory);
	}

	private static void createFileOrDirectory() throws IllegalArgumentException {
		currentDirectory.print("Current directory: ");
		String option = getString("Create file or directory (f/d): ");
		if (option.equals("d")){
			String name = getString("Enter name for new directory: ");
			fileSystem.createDirectory(name, currentDirectory);
			System.out.println("Directory created: " + name + "/");
		} else if (option.equals("f")){
			String name = getString("Enter name for new file: ");
			fileSystem.createFile(name, currentDirectory);
			System.out.println("File created: " + name);
		} else {
			throw new IllegalArgumentException("Invalid option. Please try again.");
		}
	}

	private static void deleteFileOrDirectory(){
		currentDirectory.print("Current directory: ");
		String option = getString("Enter name of file/directory to delete: ");
		fileSystem.deleteFileOrDirectory(option, currentDirectory);

	}

	private static void sortContentsByDateCreated(){
		System.out.println("Sorting contents of " + fileSystem.getCurrentPath(currentDirectory) + " by date created:");
		fileSystem.sortDirectoryByDate(currentDirectory);
	}

	private static void moveFileOrDirectory(){
		currentDirectory.print("Current directory: ");
		String name = getString("Enter name of file/directory to move: ");
		String path = getString("Enter new directory path: ");

		fileSystem.moveElement(name, path, currentDirectory);
	}

	private static void saverFileSystem(){
		try{
			PrintWriter writer = new PrintWriter(new FileWriter(FILE_SYSTEM_FILE));
			Directory root = fileSystem.getRoot();
			root.saveElement(writer);
			writer.close();
		} catch (Exception e){
			System.out.println("Error saving file system.");
			/* Handle exception */
		}
	}

	private static void loadFileSystem(FileSystem fileSystem){
		try {
			Scanner scanner = new Scanner(new java.io.File(FILE_SYSTEM_FILE));
			String line;
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				String[] parts = line.split(" ");
				if (parts.length != 3){
					System.out.println("Error loading file system.");
					return;
				}
				if (parts[2].charAt(0) == '/' && parts[2].length() > 1){
					parts[2] = parts[2].substring(1);
				}
				if (parts[0].charAt(0) == (char)ASCII_ACK){
					Directory parent = fileSystem.changeDirectory(fileSystem.getRoot(), parts[2]);
					if (parent != null){
						Directory newDirectory = new Directory(parts[0].substring(1), parent, new Timestamp(Long.parseLong(parts[1])));
						parent.add(newDirectory);
					}
				} else {
					Directory parent = fileSystem.changeDirectory(fileSystem.getRoot(), parts[2]);
					if (parent != null){
						File newFile = new File(parts[0], parent, new Timestamp(Long.parseLong(parts[1])));
						parent.add(newFile);
					}
				}
			}
			scanner.close();
		} catch (Exception e){
			System.out.println("Error loading file system.");
			/* Handle exception */
		}
	}

	public void printMenu(){
		System.out.println("===== File System Management Menu =====");
		System.out.println("1. Change directory");
		System.out.println("2. List directory contents");
		System.out.println("3. Create file/directory");
		System.out.println("4. Delete file/directory");
		System.out.println("5. Move file/directory");
		System.out.println("6. Search file/directory");
		System.out.println("7. Print directory tree");
		System.out.println("8. Sort contents by date created");
		System.out.println("9. Exit");
		System.out.print("Please select an option: ");
	}
}
