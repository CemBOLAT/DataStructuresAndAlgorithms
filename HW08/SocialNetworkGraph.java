import java.util.*;

public class SocialNetworkGraph {
	Map<String, Person> people = new HashMap<>();
	Map<Person, List<Person>> friendships = new HashMap<>();

	private static double MUTUAL_FRIENDS = 1;
	private static double COMMON_HOBBIES = 0.5;

	// Method to add a person
	public void addPerson(String name, int age, List<String> hobbies) {
		Person person = new Person(name, age, hobbies);
		people.put(name, person);
		friendships.put(person, new ArrayList<>());
		System.out.println("Person added: " + person.print());
	}

	// Method to remove a person
	public void removePerson(String name) {
		Person person = people.get(name);
		if (person != null) {
			people.remove(name);
			friendships.remove(person);
			for (List<Person> friends : friendships.values()) {
				friends.remove(person);
			}
			System.out.println("Person removed: " + person.print());
		} else {
			System.out.println("Person not found in the network.");
		}
	}

	// Method to add a friendship
	public void addFriendship(String name1, String name2) {
		Person person1 = people.get(name1);
		Person person2 = people.get(name2);
		if (person1 != null && person2 != null) {
			friendships.get(person1).add(person2);
			friendships.get(person2).add(person1);
			System.out.println("Friendship added between " + person1.name + " and " + person2.name);
		} else {
			System.out.println("One or both persons not found in the network.");
		}
	}

	public void removeFriendship(String name1, String name2) {
		Person person1 = people.get(name1);
		Person person2 = people.get(name2);
		if (person1 != null && person2 != null) {
			friendships.get(person1).remove(person2);
			friendships.get(person2).remove(person1);
			System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
		} else {
			System.out.println("One or both persons not found in the network.");
		}
	}

	// Method to find the shortest path using BFS
	public void findShortestPath(String startName, String endName) {
		//implement logic here
		Person start = people.get(startName);
		Person end = people.get(endName);

		if (start == null || end == null) {
			System.out.println("One or both persons not found in the network.");
			return;
		}

		Map<Person, Person> prev = new HashMap<>();
		Queue<Person> queue = new LinkedList<>();

		queue.add(start);
		prev.put(start, null);

		while (!queue.isEmpty()) {
			Person current = queue.poll();
			if (current == end) {
				printPath(start, end, prev);
				return;
			}

			for (Person neighbor : friendships.get(current)) {
				if (!prev.containsKey(neighbor)) {
					queue.add(neighbor);
					prev.put(neighbor, current);
				}
			}
		}

	}

	private void printPath(Person start, Person end, Map<Person, Person> prev) {
		List<Person> path = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (Person at = end; at != null; at = prev.get(at)) {
			path.add(at);
		}
		Collections.reverse(path);
		for (Person person : path) {
			sb.append(person.name).append(" -> ");
		}
		System.out.println("Shortest path: " + sb.substring(0, sb.length() - 4));
	}

	// Method to count clusters using BFS
	public void countClusters() {
		//implement logic here

		Set<Person> visited = new HashSet<>();
		List<List<Person>> clusters = new ArrayList<>();
		int count = 0;

		for (Person person : people.values()) {
			if (!visited.contains(person)) {
				List<Person> cluster = new ArrayList<>();
				bfs(person, visited, cluster);
				count++;
				clusters.add(cluster);
			}
		}
		System.out.println("Number of clusters: " + count + "\n");
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println("Cluster " + (i + 1) + ":");
			for (Person person : clusters.get(i)) {
				System.out.println(person.name);
			}
			System.out.println();
		}

	}

	private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
		Queue<Person> queue = new LinkedList<>();
		queue.add(start);
		visited.add(start);

		while (!queue.isEmpty()) {
			Person current = queue.poll();
			cluster.add(current);

			for (Person neighbor : friendships.get(current)) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
	}

	private static class Point {
		private double score;
		private int nbr_of_mutual_friends;
		private int nbr_of_common_hobbies;

		public Point(double score, int nbr_of_mutual_friends, int nbr_of_common_hobbies) {
			this.score = score;
			this.nbr_of_mutual_friends = nbr_of_mutual_friends;
			this.nbr_of_common_hobbies = nbr_of_common_hobbies;
		}

	}

	private void suggestFriends(Person person, int maxFriends) {
		Map<Person, Point> scores = new HashMap<>();
		for (var people : people.values()){
			if (people == person) continue;
			if (friendships.get(person).contains(people)) continue;

			int mutualFriends = 0;
			for (Person friend : friendships.get(person)) {
				if (friendships.get(people).contains(friend)) {
					mutualFriends++;
				}
			}

			int commonHobbies = 0;
			for (String hobby : person.hobbies) {
				if (people.hobbies.contains(hobby)) {
					commonHobbies++;
				}
			}

			double score = mutualFriends * MUTUAL_FRIENDS + commonHobbies * COMMON_HOBBIES;
			scores.put(people, new Point(score, mutualFriends, commonHobbies));
		}

		List<Person> suggestedFriends = new ArrayList<>(scores.keySet());
		suggestedFriends.sort((p1, p2) -> scores.get(p2).score > scores.get(p1).score ? 1 : -1);

		System.out.println("Suggested friends for " + person.name + ":\n");

		for (int i = 0; i < Math.min(maxFriends, suggestedFriends.size()); i++) {
			System.out.println(suggestedFriends.get(i).name + " (Score: " + scores.get(suggestedFriends.get(i)).score + ", " + scores.get(suggestedFriends.get(i)).nbr_of_mutual_friends + " mutual friends, " + scores.get(suggestedFriends.get(i)).nbr_of_common_hobbies + " common hobbies)");
		}
	}


	// Method to suggest friends
	public void suggestFriends(String name, int maxFriends) {
		Person person = people.get(name);
		if (person == null) {
			System.out.println("Person not found in the network.");
			return;
		}

		suggestFriends(person, maxFriends);
	}
}
