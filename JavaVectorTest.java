import java.util.Iterator; // Make sure to import Iterator from java.util
import myContainerPackage.JavaVector;
import myContainerPackage.JavaContainer;

public class JavaVectorTest {
	public static void main(String[] args) {
		JavaVector<Integer> v = new JavaVector<>(); // define outside of try-catch to use in finally
		try {
			for (int i = 0; i < 10; i++) {
				v.Add(i * 2);
			}
			v.Remove(4);
			Iterator<Integer> iterator = v.getIterator();
			int index = 0;
			while (iterator.hasNext()) {
				Integer element = iterator.next();
				v.setExactData(index, element * 22);
				index++;
			}
			JavaVector<Integer> cloneV = new JavaVector<>(v);
			if (cloneV.equals(v)){
				System.out.println("1 -- Equal to ! ");
			} else {
				System.out.println("1 -- Not Equal to ! ");
			}
			JavaVector<Integer> notClone = new JavaVector<>(v.Size());
			if (cloneV.equals(notClone)){
				System.out.println("2 -- Equal to ! ");
			} else {
				System.out.println("2 -- Not Equal to ! ");
			}
			//v.setExactData(-1, 55); //exception occurs
			//System.out.println(v.getData(-4)); //exception occurs
			//JavaVector<Integer> vv = new JavaVector<>(-2); //exception occurs
			//v.Remove(4); //exception occurs
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println(v);// last mapped vector !
			System.out.println("Done");
		}
	}
}
