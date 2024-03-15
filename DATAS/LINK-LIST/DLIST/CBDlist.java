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
		Node<E>	newNode = new Node<E>(newADD);
		if (size == 0){
			head = newNode;
			tail = newNode;
		}
		else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}
	public boolean add(E newADD){
		Node<E>	newNode = new Node<E>(newADD);
		if (size == 0){
			head = newNode;
			tail = newNode;
		}
		else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
		return true;
	}
	public Node<E>	getNode(int index) throws Exception{
		Node<E>	temp = head;
		if (index < 0 || index >= size){
			throw new Exception("Out OF Bounds!");
		}
		for (int i = 0; i < index && temp != null; i++){
			temp = temp.next;
		}
		return temp;
	}
	public boolean add(E newADD, int index) throws Exception {
		if (index == 0){
			addHead(newADD);
		}
		else if (index == size - 1){
			add(newADD);
		}
		else {
			Node<E>	node = getNode(index);
			Node<E> newNode = new Node<E>(newADD);
			newNode.prev = node.prev;
			newNode.next = node;
			node.prev.next = newNode;
			node.prev = newNode;
			size++;
		}
		return true;
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
				{
					tmp.prev.next = tmp.next;
					tmp.next.prev = tmp.prev;
					size--;
				}
				isFound = true;
				break;
			}
			tmp = tmp.next;
		}
		return isFound;
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
	public boolean clear(){
		head = null;
		tail = null;
		size = 0;
		return false;
	}
	public int Size(){
		return size;
	}
	public boolean removeHead(){
		if (head == null)
			return false;
		head = head.next;
		head.prev = null;
		size--;
		return true;
	}
	public void	printList(){
		Node<E> temp = head;
		while (temp != null){
			System.out.print(temp.data + "-");
			temp = temp.next;
		}
		System.out.println();
	}
	public E	getData(int index) throws Exception{
		return (getNode(index).data);
	}
}
