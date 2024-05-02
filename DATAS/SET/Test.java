

public class Test {
	public static void main(String[] args){
		mySet<Integer> set1 = new mySet<Integer>();

		for (int i = 0; i < 10; i++) {
			set1.add(i);
		}

		mySet<Integer> set2 = new mySet<Integer>();

		for (int i = 5; i < 15; i++) {
			set2.add(i);
		}

		System.out.println("set1: " + set1);
		System.out.println("set2: " + set2);

		System.out.println("set1.contains(5): " + set1.contains(5));
		System.out.println("set1.contains(15): " + set1.contains(15));

		System.out.println("set1.size(): " + set1.size());
		System.out.println("set2.size(): " + set2.size());

		System.out.println("set1.containsAll(set2): " + set1.containsAll(set2));


		set1.union(set2);
		System.out.println("set1.union(set2): " + set1);

		set1.difference(set2);

		System.out.println("set1.difference(set2): " + set1);

		set1.intersection(set2);
		System.out.println("set1.intersection(set2): " + set1);
	}
}
