public class CBDlist<E> {
	public Node<E>	head;
	public Node<E>	tail;
	public int		size;

	public CBDlist(){
		head = null;
		tail = null;
		size = 0;
	}
	public boolean addHead(E newADD){
		Node<E> newHead = new Node<E>(newADD, head, null);
		head = newHead;
		if (size == 0)
			tail = newHead;
		size++;
		return true;
	}
	public boolean add(E newADD){
		if (size == 0){
			head = new Node<E>(newADD);
		}
		else{
			tail.next = new Node<E>(newADD, null, tail);
		}
		size++;
		return true;
	}
	public boolean addTail(E newADD){
		Node<E> nn = new Node<E>(newADD);
		if (size == 0){
			head = nn;
			tail = nn;
		}
		else{
			tail.next = nn;
			tail = nn;
		}
		size++;
		return true;
	}
	public boolean add(E newADD, int index) throws Exception{
		if (index < 0 || index > size)
			throw new Exception("Out OF Bounds!");
		if (index == 0)
			addHead(newADD);
		else if (index == size)
			addTail(newADD);
		else {
			Node<E>	tmp = head;
			for (int i = 0; i < index; i++){
				tmp = tmp.next;
			}
			Node<E>	newNode = new Node<E>(newADD,tmp, tmp.prev);
			if (newNode.next == null)
				tail = newNode;
			if (newNode.prev == null)
				head = newNode;
			size++;
		}
		return true;
	}
	public E	getNode(int index){
		Node<E>	temp = head;
		for (int i = 0; i < index && temp != null; i++){
			temp = temp.next;
		}
		return temp.data;
	}
	public boolean remove(E el){
		Node<E> tmp = head;
		boolean isFound = false;
		if (size == 0)
			return false;
		for (int i = 0; i < size; i++){
			if (tmp.data == el){
				if (tmp == head){
					removeHead();
				}
				else
					tmp.prev = tmp.next;
				isFound = true;
				size--;
				break;
			}

			tmp = tmp.next;
		}
		return isFound;
	}
	public boolean removeHead(){
		if (head == null)
			return false;
		head = head.next;
		head.prev = null;
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
		private Node<E> prev;

		private	Node(E stored){
			data = stored;
			next = null;
			prev = null;
		}

		private Node(E stored, Node<E> newNext, Node<E> newPrev){
			data = stored;
			next = newNext;
			prev = newPrev;
		}
	}
}
