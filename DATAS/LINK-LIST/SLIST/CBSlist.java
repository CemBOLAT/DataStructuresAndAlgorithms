public class CBSlist<E> {
	public Node<E>	head;
	public int		size;

	public CBSlist(){
		head = null;
		size = 0;
	}
	public boolean addHead(E newADD){ // add to head
		Node<E> newHead = new Node<E>(newADD, head);
		head = newHead;
		size++;
		return true;
	}
	public boolean add(E newADD){
		if (size == 0){
			head = new Node<E>(newADD);
		}
		else{
			Node<E>	lastElement = getLast();
			lastElement.next = new Node(newADD, null);
		}
		size++;
		return true;
	}
	public boolean add(E newADD, int index) throws Exception{
		if (index < 0 || index > size)
			throw new Exception("Out OF Bounds!");
		if (index == 0){
			addHead(newADD);
		}
		else {
			Node<E>	tmp = head;
			Node<E>	prev = null;
			for (int i = 0; i < index; i++){
				prev = tmp;
				tmp = tmp.next;
			}
			Node<E>	newNode = new Node<E>(newADD,tmp);
			prev.next = newNode;
			size++;
		}
		return true;
	}
	public E	getNode(int index) throws Exception{
		Node<E>	temp = head;
		if (index < 0 || index >= size){
			throw new Exception("Out OF Bounds!");
		}
		for (int i = 0; i < index && temp != null; i++){
			temp = temp.next;
		}
		return temp.data;
	}
	public boolean remove(E el){
		Node<E> tmp = head;
		Node<E> prev = null;
		boolean isFound = false;
		if (size == 0)
			return false;
		for (int i = 0; i < size; i++){
			if (tmp.data == el){
				if (tmp == head){
					removeHead();
				}
				else
					prev.next = tmp.next;
				isFound = true;
				size--;
				break;
			}
			prev = tmp;
			tmp = tmp.next;
		}
		return isFound;
	}
	public boolean removeHead(){
		if (head == null)
			return false;
		head = head.next;
		size--;
		return true;
	}
	public boolean clear(){
		head = null;
		size = 0;
		return false;
	}
	public int Size(){
		return size;
	}
	public void	printList(){
		Node<E> temp = head;
		while (temp != null){
			System.out.print(temp.data + "-");
			temp = temp.next;
		}
		System.out.println();
	}
	private Node<E> getLast(){
		Node<E>	last = head;
		while (last.next != null){
			last = last.next;
		}
		return last;
	}
	private static class Node<E> {
		private E	data;
		private Node<E> next;

		private	Node(E stored){
			data = stored;
			next = null;
		}

		private Node(E stored, Node<E> newNext){
			data = stored;
			next = newNext;
		}
	}
}
