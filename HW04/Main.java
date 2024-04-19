

public class Main {
	public static void main(String[] args){
		Main main = new Main();

		FileSystem fileSystem = new FileSystem();
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
						//main.moveFileOrDirectory(fileSystem);
						break;
					case 6:
						//main.searchFileOrDirectory(fileSystem);
						break;
					case 7:
						//main.printDirectoryTree(fileSystem);
						break;
					case 8:
						//main.sortContentsByDateCreated(fileSystem);
						break;
					case 9:
						exit = true;
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
	public void printLine(){
		System.out.println("======================================");
	}
}
