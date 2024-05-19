

/*
	Root is always black
	Every path from root to null has same number of black nodes
	Every new node is red
	Every leaf node is black
	Every red node has black children

	// silme yok1
*/

public class RedBlackTree {

	private Node root;

	public RedBlackTree() {
		this.root = null;
	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int height() {
		return height(root);
	}

	private Node getUncle(Node node) {
		if (node == null || node.parent == null || node.parent.parent == null) {
			return null;
		}
		if (node.parent == node.parent.parent.left) {
			return node.parent.parent.right;
		} else {
			return node.parent.parent.left;
		}
	}

	private Node leftRotate(Node node) {
		Node right = node.right;
		Node rightLeft = right.left;
		right.left = node;
		node.right = rightLeft;
		return right;
	}

	private Node rightRotate(Node node) {
		Node left = node.left;
		Node leftRight = left.right;
		left.right = node;
		node.left = leftRight;
		return left;
	}

	private Node recoverRedRedViolation(Node node) {
		// 4 cases first only root is red
		if (root == node) {
			node.isRed = false;
			return node;
		}
		if (getUncle(node) != null && getUncle(node).isRed) {
			node.parent.isRed = false;
			getUncle(node).isRed = false;
			node.parent.parent.isRed = true;
			return recoverRedRedViolation(node.parent.parent);
		}
		// triangle cases
		if (node.parent == node.parent.parent.left) {
			if (node == node.parent.right) { // triangle case
				node = node.parent;
				leftRotate(node);
			}
			rightRotate(node.parent.parent); // line case
			node.parent.isRed = false;
			node.parent.right.isRed = true;
		} else {
			if (node == node.parent.left) { // triangle case
				node = node.parent;
				rightRotate(node);
			}
			leftRotate(node.parent.parent); // line case
			node.parent.isRed = false;
			node.parent.left.isRed = true;
		}
		return node;
	}

	public void delete(int data) {
		// TODO
		root = delete(root, data);
	}

	private Node insert(Node node, int data, Node parent) {
		if (node == null) {
			return new Node(data, parent);
		}
		if (data < node.data) {
			node.left = insert(node.left, data, node);
		} else if (data > node.data) {
			node.right = insert(node.right, data, node);
		}

		// Fix red red violation
		return recoverRedRedViolation(node);
	}

	public void insert(int data) {
		root = insert(root, data, null);
		root.isRed = false;
	}

	private static class Node {
		int data;
		Node left, right;
		Node parent;
		boolean isRed;
		Node(int data, Node parent) {
			parent = parent;
			this.data = data;
			left = null;
			right = null;
			this.isRed = true;
		}
	}
}
