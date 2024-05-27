
public class BTree<E extends Comparable<E>>{
	/** The root node. */

	private Node<E> root = null;
	/** The maximum number of children of a node */
	private int order;

	/** Construct a B-tree with node size order
		@param order the size of a node
	*/
	public BTree(int order) {
		this.order = order;
		root = null;
	}

	private int binarySearch(E item, E[] data, int low, int high){
		if(low == high){
			return low;
		}
		int mid = (low + high) / 2;
		if(item.compareTo(data[mid]) <= 0){
			return binarySearch(item, data, low, mid);
		}
		else{
			return binarySearch(item, data, mid + 1, high);
		}
	}

	private boolean insert(Node<E> root, E item){
		int index = binarySearch(item, root.data, 0, root.size);
		if (index != root.size && item.compareTo(root.data[index]) == 0){ // item is already in the tree
			return false;
		}

		if (root.child[index] == null){
			if (root.size < order - 1){
				insertIntoNode(root, index, item, null);
			}


		} else {

		}

	}

	 /** Method to insert a new value into a node
		pre: node.data[index-1] < item < node.data[index];
		post: node.data[index] == item and old values are moved right one position
		@param node The node to insert the value into
		@param index The index where the inserted item is to be placed
		@param item The value to be inserted
		@param child The right child of the value to be inserted
	*/
	private void insertIntoNode(Node<E> node, int index, E obj, Node<E> child) {
		for (int i = node.size; i > index; i--) {
			node.data[i] = node.data[i- 1];
			node.child[i + 1] = node.child[i];
		}
		node.data[index] = obj;
		node.child[index + 1] = child;
		node.size++;
	}


	public boolean insert(E item){
		if(root == null){
			root = new Node<E>(order);
			root.data[0] = item;
			root.size = 1;
			return true;
		}
		else{
			return insert(root, item);
		}
	}


	private static class Node<E> {
		// Data Fields
		/** The number of data items in this node */
		private int size = 0;
		/** The information */
		private E[] data;

		/** The links to the children. child[i] refers to
		the subtree of children < data[i] for i < size
		and to the subtree of children > data[size-1]
		for i == size */

		private Node<E>[] child;
		/** Create an empty node of size order
			@param order The size of a node
		*/
		@SuppressWarnings("unchecked")
		public Node(int order) {
			data = (E[]) new Comparable[order - 1];
			child = (Node<E>[]) new Node[order];
			size = 0;
		}
	}

}
