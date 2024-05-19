class TwoThreeTree {
    private Node root;

    private class Node {
        int[] keys = new int[3];
        Node[] children = new Node[4];
        int size = 0;

        boolean isLeaf() {
            return children[0] == null;
        }
    }

    public void insert(int key) {
        Node newRoot = insert(root, key);
        if (newRoot != root) {
            Node oldRoot = root;
            root = new Node();
            root.keys[0] = newRoot.keys[1];
            root.children[0] = oldRoot;
            root.children[1] = newRoot;
            root.size = 1;
        }
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            node = new Node();
            node.keys[0] = key;
            node.size = 1;
            return node;
        }

        if (node.isLeaf()) {
            if (node.size < 2) {
                insertIntoNode(node, key);
                return node;
            } else {
                return splitNode(node, key);
            }
        } else {
            Node child = getNextChild(node, key);
            Node newChild = insert(child, key);
            if (newChild != child) {
                return handleSplitChild(node, newChild);
            } else {
                return node;
            }
        }
    }

    private Node handleSplitChild(Node node, Node newChild) {
        if (node.size < 2) {
            insertIntoNode(node, newChild.keys[1]);
            int index = findChildIndex(node, newChild.keys[1]);
            shiftChildrenRight(node, index);
            node.children[index + 1] = newChild;
            return node;
        } else {
            return splitNodeWithChild(node, newChild);
        }
    }

    private void insertIntoNode(Node node, int key) {
        if (node.keys[0] > key) {
            node.keys[1] = node.keys[0];
            node.keys[0] = key;
        } else {
            node.keys[1] = key;
        }
        node.size++;
    }

    private void shiftChildrenRight(Node node, int index) {
        for (int i = node.size; i > index; i--) {
            node.children[i + 1] = node.children[i];
        }
    }

    private int findChildIndex(Node node, int key) {
        if (key < node.keys[0]) return 0;
        if (node.size == 1 || key < node.keys[1]) return 1;
        return 2;
    }

    private Node getNextChild(Node node, int key) {
        if (key < node.keys[0]) return node.children[0];
        if (node.size == 1 || key < node.keys[1]) return node.children[1];
        return node.children[2];
    }

    private Node splitNode(Node node, int key) {
        Node newNode = new Node();
        if (key < node.keys[0]) {
            newNode.keys[1] = node.keys[0];
            node.keys[0] = key;
        } else if (key < node.keys[1]) {
            newNode.keys[1] = key;
        } else {
            newNode.keys[1] = node.keys[1];
            node.keys[1] = key;
        }
        newNode.size = 1;
        node.size = 1;
        return newNode;
    }

    private Node splitNodeWithChild(Node node, Node child) {
        Node newNode = new Node();
        int middleKey;
        if (child.keys[1] < node.keys[0]) {
            middleKey = node.keys[0];
            newNode.keys[1] = node.keys[1];
            node.keys[0] = child.keys[1];
        } else if (child.keys[1] < node.keys[1]) {
            middleKey = child.keys[1];
            newNode.keys[1] = node.keys[1];
        } else {
            middleKey = node.keys[1];
            newNode.keys[1] = child.keys[1];
        }
        node.size = 1;
        newNode.size = 1;
        return newNode;
    }


	public Node search(int key) {
		return search(root, key);
	}

	private Node search(Node node, int key) {
		if (node == null) return null;
	}
    // Additional methods like search, delete, etc. can be added here.
}
