
// Java program to implement 2-3 Tree
public class TwoThreeTree {

    // Inner class representing a Node in the 2-3 tree
    private static class Node {
        int[] keys; // node can have 1 or 2 keys
        Node[] children; // Children means the number of children in the node
        int numKeys; // Number of keys in the node (1 or 2)
        boolean isLeaf; // True if the node is a leaf

        Node(int key) {
            keys = new int[2];
            children = new Node[3];
            keys[0] = key;
            numKeys = 1;
            isLeaf = true;
        }

        // Insert a key into the node
        void insert(int key) {
            if (numKeys == 1) {
                if (key < keys[0]) {
                    keys[1] = keys[0];
                    keys[0] = key;
                } else {
                    keys[1] = key;
                }
                numKeys++;
            } else { // numKeys == 2
                if (key < keys[0]) { // new key is smallest
                    keys[2] = keys[1];
                    keys[1] = keys[0];
                    keys[0] = key;
                } else if (key < keys[1]) { // new key is middle key
                    keys[2] = keys[1];
                    keys[1] = key;
                } else { // new key is largest
                    keys[2] = key;
                }
                numKeys++;
            }
        }
    }

    private Node root;

    public TwoThreeTree() {
        root = null;
    }

    // Insert a key into the 2-3 Tree
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(root, key); // Call the helper method to insert the key
        }
    }

    // Helper method to insert a key into the tree rooted at node
    private void insert(Node node, int key) {
        if (node.isLeaf) {
            node.insert(key);
        } else {
            if (node.numKeys == 1) {
                if (key < node.keys[0]) {
                    insert(node.children[0], key);
                } else {
                    insert(node.children[1], key);
                }
            } else { // node.numKeys == 2
                if (key < node.keys[0]) {
                    insert(node.children[0], key);
                } else if (key < node.keys[1]) {
                    insert(node.children[1], key);
                } else {
                    insert(node.children[2], key);
                }
            }
        }
    }

    // Method to print the 2-3 Tree
    public void print() {
        if (root != null) {
            print(root);
        }
    }

    // Helper method to search for a key in the tree rooted at node
    private boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.keys[0] == key || (node.numKeys == 2 && node.keys[1] == key)) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        if (node.numKeys == 1) {
            if (key < node.keys[0]) {
                return search(node.children[0], key);
            } else {
                return search(node.children[1], key);
            }
        } else { // node.numKeys == 2
            if (key < node.keys[0]) {
                return search(node.children[0], key);
            } else if (key < node.keys[1]) {
                return search(node.children[1], key);
            } else {
                return search(node.children[2], key);
            }
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    // Helper method to print the tree rooted at node
    private void print(Node node) {
        if (node != null) {
            if (!node.isLeaf) {
                print(node.children[0]);
                System.out.print(node.keys[0] + " ");
                print(node.children[1]);
                if (node.numKeys == 2) {
                    System.out.print(node.keys[1] + " ");
                    print(node.children[2]);
                }
            } else {
                for (int i = 0; i < node.numKeys; i++) {
                    System.out.print(node.keys[i] + " ");
                }
            }
        }
    }

    // Test the TwoThreeTree implementation
    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);

        tree.print(); // Output should be: 5 6 7 10 12 20 30
    }
}
