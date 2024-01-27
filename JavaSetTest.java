import java.util.Iterator;
import myContainerPackage.JavaContainer;
import myContainerPackage.JavaSet;

public class JavaSetTest {
	public static void main(String[] args) {
		JavaSet<Integer> v = new JavaSet<>();
		try {
			for (int i = 0; i < 10; i++) {
				v.Add(i * 3);
			}
			v.Add(13);
			//v.Add(13); // exception occurs
			v.Add(-1);
			v.Add(999);
			v.Remove(13);
			v.Remove(999);
			//v.Remove(13) ; // exception occurs
			JavaSet<Integer> cloneV = new JavaSet<>(v);
			if (cloneV.equals(v)){
				System.out.println("1 -- Equal to ! ");
			} else {
				System.out.println("1 -- Not Equal to ! ");
			}
			JavaSet<Integer> notClone = new JavaSet<>(v.Size());
			if (cloneV.equals(notClone)){
				System.out.println("2 -- Equal to ! ");
			} else {
				System.out.println("2 -- Not Equal to ! ");
			}
			//System.out.println(v.getData(-4)); //exception occurs
			//JavaSet<Integer> vv = new JavaSet<>(-2); //exception occurs
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println(v);
			System.out.println("Done");
		}
	}
}
