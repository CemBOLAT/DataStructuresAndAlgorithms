
public class Test{

	public static void main(String[] args){

		myHash<myInt> hash = new myHash<myInt>();

		for (int i = 0; i < 10; i++){
			myInt temp = new myInt(i);
			hash.add(temp);
		}

		System.out.println(hash);

		hash.remove(new myInt(5));

		System.out.println(hash);

		System.out.println(hash.get(new myInt(3)).get());


	}
}
