import java.util.*;

public class Main {
	public static void main(String[] args) {
		SocialNetworkGraph network = new SocialNetworkGraph();
		// Scanner scanner = new Scanner(System.in);

		//// Adding some people for demonstration
		network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
		network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
		network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
		network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
		network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
		network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));
//
		//// Adding friendships for demonstration
		network.addFriendship("John Doe", "Jane Smith");
		network.addFriendship("Bob Brown", "Alice Johnson");
		network.addFriendship("Jane Smith", "Bob Brown");
		network.addFriendship("Emily Davis", "Frank Wilson");
//
		//// // Finding shortest path for demonstration
		network.findShortestPath("John Doe", "Alice Johnson");
//
		//// // Counting clusters for demonstration
		//network.countClusters();

		// scanner.close();


		try {
			boolean exit = false;
			while (!exit) {
				printMenu();
				try{
					int option;
					option = Integer.parseInt(System.console().readLine());
					switch (option) {
						case 1:
							addPerson(network);
						break;
						case 2:
							removePerson(network);
						break;
						case 3:
							addFriendship(network);
						break;
						case 4:
							removeFriendship(network);
						break;
						case 5:
							findShortestPath(network);
						break;
						case 6:
							suggestFriends(network);
						break;
						case 7:
							countClusters(network);
						break;
						case 8:
							exit = true;
						break;
						default:
							System.out.println("Invalid option. Please select a valid option.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid option. Please select a valid option.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private static String getString(String message) throws Exception {
		String output = "";
		System.out.print(message + " ");
		output = System.console().readLine();
		if (output == null || output.isEmpty()) {
			throw new Exception("Invalid input. Please enter a valid string.");
		}
		return output;
	}

	private static void addPerson(SocialNetworkGraph network) throws Exception {
		String name = getString("Enter the name of the person:");

		System.out.print("Enter the age of the person:");
		int age = Integer.parseInt(System.console().readLine());

		System.out.print("Enter hobbies (comma-separated):");
		String[] hobbies = System.console().readLine().split(",");
		network.addPerson(name, age, Arrays.asList(hobbies));
	}

	private static void removePerson(SocialNetworkGraph network) throws Exception {
		String name = getString("Enter the name of the person to remove:");
		network.removePerson(name);
	}

	private static void addFriendship(SocialNetworkGraph network) throws Exception {
		String name1 = getString("Enter first person’s name:");
		String name2 = getString("Enter second person’s name:");
		network.addFriendship(name1, name2);
	}

	private static void removeFriendship(SocialNetworkGraph network) throws Exception {
		String name1 = getString("Enter first person’s name:");
		String name2 = getString("Enter second person’s name:");
		network.removeFriendship(name1, name2);
	}

	private static void findShortestPath(SocialNetworkGraph network) throws Exception {
		String name1 = getString("Enter first person’s name:");
		String name2 = getString("Enter second person’s name:");
		network.findShortestPath(name1, name2);
	}

	private static void suggestFriends(SocialNetworkGraph network) throws Exception {
		String name = getString("Enter the name of the person:");
		System.out.print("Enter maximum number of friends to suggest: ");
		int maxFriends = Integer.parseInt(System.console().readLine());
		network.suggestFriends(name, maxFriends);
	}

	private static void countClusters(SocialNetworkGraph network) {
		System.out.println("Counting clusters in the social network...");
		network.countClusters();
	}

	private static void printMenu() {
		System.out.println("===== Social Network Analysis Menu =====");
		System.out.println("1. Add person");
		System.out.println("2. Remove person");
		System.out.println("3. Add friendship");
		System.out.println("4. Remove friendship");
		System.out.println("5. Find shortest path");
		System.out.println("6. Suggest friends");
		System.out.println("7. Count clusters");
		System.out.println("8. Exit");
		System.out.print("Please select an option: ");
	}
}
