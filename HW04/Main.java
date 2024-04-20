import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.Timestamp;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class Main {

	private static final String FILE_SYSTEM_FILE = "filesystem.txt";

	public static void main(String[] args){
		Main main = new Main();

		FileSystem fileSystem = new FileSystem();
		loadFileSystem(fileSystem);
		boolean exit = false;
		while (!exit){
			main.printMenu();
			try {
				int option = Integer.parseInt(System.console().readLine());
				switch (option){
					case 1:
						fileSystem.changeDirectory();
						break;
					case 2:
						fileSystem.listDirectoryContents();
						break;
					case 3:
						fileSystem.createFileOrDirectory();
						break;
					case 4:
						fileSystem.deleteFileOrDirectory();
						break;
					case 5:
						fileSystem.moveFileOrDirectory();
						break;
					case 6:
						fileSystem.searchFileOrDirectory();
						break;
					case 7:
						fileSystem.printDirectoryTree();
						break;
					case 8:
						fileSystem.sortContentsByDateCreated();
						break;
					case 9:
						exit = true;
						saverFileSystem(fileSystem);
						System.out.println("Saving and Exiting...");
						break;
					default:
						System.out.println("Invalid option. Please try again.");
				}
			} catch (NumberFormatException e){
				System.out.println("Invalid option. Please try again.");
			} catch (Exception e){
				/* Handle exception */
			}
		}
	}

	public static void saverFileSystem(FileSystem fileSystem){
		try{
			//PrintWriter writer = new PrintWriter(FILE_SYSTEM_FILE);
			PrintWriter writer = new PrintWriter(new FileWriter(FILE_SYSTEM_FILE));
			for (var element : fileSystem.getRoot().getChildren()){
				element.saveElement(writer);
			}
			writer.close();
		} catch (Exception e){
			System.out.println("Error saving file system.");
			/* Handle exception */
		}
	}
	public static void loadFileSystem(FileSystem fileSystem){
		try {
			Scanner scanner = new Scanner(new java.io.File(FILE_SYSTEM_FILE)); // new File(FILE_SYSTEM_FILE)
			while (scanner.hasNext()){
				String name = scanner.next();
				Timestamp dateCreated = new Timestamp(scanner.nextLong());
				String path = scanner.next().trim();
				if (path.endsWith("/")){
					path = path.substring(0, path.length() - (1 + name.length()));
					Directory parent = fileSystem.findDirectory(fileSystem.getRoot(), path);
					Directory directory = new Directory(name, dateCreated, parent);
					directory.setPath(path + name + "/");
					parent.add(directory);
				} else {
					path = path.substring(0, path.length() - name.length());
					Directory parent = fileSystem.findDirectory(fileSystem.getRoot(), path);
					File file = new File(name, dateCreated, parent);
					file.setPath(path + name);
					parent.add(file);
				}
			}
			scanner.close();
		} catch (Exception e){
			System.out.println("Error loading file system.");
			System.out.println(e);
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
