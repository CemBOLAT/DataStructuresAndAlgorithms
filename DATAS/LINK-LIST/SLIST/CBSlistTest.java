public class CBSlistTest{
	public static void main(String[] args){

		try {
			CBSlist<Integer>	myList = new CBSlist<Integer>();
			for (int i = 10; i < 13; i++)
				myList.add(i,0);
			myList.printList();
			for (int i = 3; i < 7; i++)
				myList.add(i, myList.Size());
			myList.printList();
			myList.remove(3);
			myList.printList();
			myList.remove(10);
			myList.printList();
			myList.remove(12);
			myList.printList();
			myList.clear();
			myList.printList();
			for (int i = 10; i < 13; i++)
				myList.addHead(i);
			myList.printList();
			myList.removeHead();
			myList.printList();
			System.out.println(myList.getNode(0));
			System.out.println(myList.getNode(1));
			myList.add(66,1);
			myList.add(16,0);
			myList.add(36,myList.Size());
			System.out.println(myList.getNode(2)); // Exception
			myList.printList();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
