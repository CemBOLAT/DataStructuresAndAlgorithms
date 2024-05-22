public class AVLTree {
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    // Insertion
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node node){
        Node left = node.left;
        Node leftRight = left.right;
        left.right = node;
        node.left = leftRight;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));
        return left;
    }

    private Node leftRotate(Node node){
        Node right = node.right;
        Node rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));
        return right;
    }

    private Node insert(Node node, Stock stock) {
        // Implementation of AVL Tree insertion logic (we have two parts: insertion and balancing)

        // Insertion
        if (node == null)
            return new Node(stock);
        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0)
            node.left = insert(node.left, stock);
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0)
            node.right = insert(node.right, stock);
        else
            return node;

        // Balancing
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && node.left != null && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance < -1 && node.right != null && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance > 1 && node.left != null && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right != null && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // Deletion
    public void delete(String symbol) {
        root = delete(root, symbol);
    }


    private Node findMin(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node delete(Node node, String symbol) {
        // Implementation of AVL Tree deletion logic
        if (node == null)
            return null;
        if (symbol.compareTo(node.stock.getSymbol()) < 0)
            node.left = delete(node.left, symbol);
        else if (symbol.compareTo(node.stock.getSymbol()) > 0)
            node.right = delete(node.right, symbol);
        else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left == null) ? node.right : node.left;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = findMin(node.right);
                node.stock = temp.stock;
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }

        // Balancing
        if (node == null)
            return null;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // Search
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    private Node search(Node node, String symbol) {
        // Implementation of AVL Tree search logic
        if (node == null)
            return null;
        if (symbol.compareTo(node.stock.getSymbol()) < 0)
            return search(node.left, symbol);
        else if (symbol.compareTo(node.stock.getSymbol()) > 0)
            return search(node.right, symbol);
        return node;
    }

    // Balancing methods (left rotation, right rotation, etc.)
    // Height update and balance factor calculations

    // In-order, pre-order, post-order traversals
    // For example:

    private void preOrderTraversal(Node node) {
        if (node == null)
            return;
        System.out.println(node.stock);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    private void postOrderTraversal(Node node) {
        if (node == null)
            return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.stock);
    }

    private void inOrderTraversal(Node node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        System.out.println(node.stock);
        inOrderTraversal(node.right);
    }


    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    //public static void main(String[] args) {
    //    AVLTree tree = new AVLTree();
    //    for (int i = 3; i < 10; i++) {
    //        Stock stock = new Stock("Stock" + i, 100.0 + i, 1000 + i, 10000 + i);
    //        tree.insert(stock);
    //    }
    //    tree.insert(new Stock("Stock0", 100.0, 1000, 10000));
    //    tree.insert(new Stock("Stock2", 100.0, 1000, 10000));
    //    tree.insert(new Stock("Stock1", 100.0, 1000, 10000));
    //    tree.inOrderTraversal();
    //    
    //}

}
