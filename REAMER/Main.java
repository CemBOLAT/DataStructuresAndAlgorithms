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
				/* Handle exception */
				System.out.println(e);
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
	}

	private static void changeDirectory(){
		System.out.println("Current directory: " + fileSystem.getCurrentPath(currentDirectory));
		String path = getString("Enter new directory path: ");
	}

	private static void searchFileOrDirectory(){
		String name = getString("Search query: ");
	}

	private static void listDirectoryContents(){
		System.out.println("Listing contents of " + fileSystem.getCurrentPath(currentDirectory) + ":");
	}

	private static void createFileOrDirectory() throws IllegalArgumentException {
		System.out.println("Current directory: " + fileSystem.getCurrentPath(currentDirectory));
	}

	private static void deleteFileOrDirectory(){
		System.out.println("Current directory: " + fileSystem.getCurrentPath(currentDirectory));
		String option = getString("Enter name of file/directory to delete: ");
	}

	private static void sortContentsByDateCreated(){
		System.out.println("Sorting contents of " + fileSystem.getCurrentPath(currentDirectory) + " by date created:");
	}

	private static void moveFileOrDirectory(){
		System.out.println("Current directory: " + fileSystem.getCurrentPath(currentDirectory));
		String name = getString("Enter name of file/directory to move: ");
		String path = getString("Enter new directory path:");
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
