import java.util.Iterator;
import myContainerPackage.JavaContainer;
import myContainerPackage.JavaSet;
import java.io.FileWriter;
import java.util.Scanner;

/*
	Taken from pdf file !
	Your driver code will be in a separate class.
	*It will do the following
		**Test each methodat least 2 times and printing the results.
		**Do not forget to test the constructors.
		***Writes some sets to text files. Do not forget to include your saved files.
*/

public class JavaSetTest {
	public static void main(String[] args) {
		JavaSet<Integer> v = new JavaSet<Integer>(); // JavaSet<Integer> v = new JavaSet<>();
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the number of elements to add:");
			int numElements = scanner.nextInt();
			if (numElements < 0) {
				throw new RuntimeException("Number of elements is invalid !");
			}
			System.out.println("Enter the elements:");
			for (int i = 0; i < numElements; i++) {
				int element = scanner.nextInt();
				v.Add(element);
			}
			System.out.println("Enter the element to remove:");
			numElements = scanner.nextInt();
			v.Remove(numElements);
			System.out.println("Enter the element to search:");
			numElements = scanner.nextInt();
			if (v.isIn(numElements)) {
				System.out.println("The element is in the set");
			} else {
				System.out.println("The element is not in the set");
			}
			scanner.close();
			// Default test Case
			//for (int i = 0; i < 10; i++) {
				//v.Add(i * 5 + 6);
				//v.Add(i * 5);
			//	v.Add(i * 2);
			//}
			//v.Add(13);
			//v.Add(13); // exception occurs
			//v.Add(-1);
			//v.Add(999);
			//v.Remove(13);
			//v.Remove(999);
			//v.Remove(13) ; // exception occurs
			JavaSet<Integer> cloneV = new JavaSet<Integer>(v);
			if (cloneV.equals(v)){
				System.out.println("1 -- Equal to ! "); // this line must be printed
			} else {
				System.out.println("1 -- Not Equal to ! ");
			}
			JavaSet<Integer> notClone = new JavaSet<>(v.Size());
			if (cloneV.equals(notClone)){
				System.out.println("2 -- Equal to ! ");
			} else {
				System.out.println("2 -- Not Equal to ! "); // this line must be printed
			}
			//System.out.println(v.getData(-4)); //exception occurs
			//JavaSet<Integer> vv = new JavaSet<>(-2); //exception occurs
			Integer	total = 0;
			var it = v.getIterator();
			while (it.hasNext()) {
				total += it.next();
			}
			System.out.println("Total = " + total);
			save(v, "set.txt"); // saving to file
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println(v);
			System.out.println("Done");
		}
	}

	// ***Writes some sets to text files. Do not forget to include your saved files.
	// I dont know what is meant by this line. I just saved the set to file.
	// While saving it to file, I used the toString method of JavaSet class.
	// I also used the save method of this class.
	public static <T extends Comparable<T>> void save(JavaSet<T> set, String fileName) {
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			fileWriter.write(set.toString() + "\n");
			fileWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

