public class AVLTree {

    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private Node rotateRight(Node node){
        Node left = node.left;
        Node left_right = left.right;

        left.right = node;
        node.left = left_right;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));

        return left;
    }

    private Node rotateLeft(Node node){
        Node right = node.right;
        Node right_left = right.left;

        right.left = node;
        node.right = right_left;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));

        return right;
    }

    private Node rotate(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balancingFactor(node);
        if (balance > 1){
            // left right case
            if (node.data.getSymbol().compareTo(node.left.data.getSymbol()) < 0){
                node.left = rotateLeft(node.left); // important to update the left child after rotation
                return rotateRight(node); // rotate the node
            }
            else
                return rotateRight(node); // left left case
        }

        if (balance < -1){
            if (node.data.getSymbol().compareTo(node.right.data.getSymbol()) > 0){
                node.right = rotateRight(node.right); // important to update the right child after rotation
                return rotateLeft(node); // rotate the node
            }
            else
                return rotateLeft(node); // right right case
        }
        return node;
    }

    private Node insertHelper(Node node, Stock data) {
        if (node == null){
            return new Node(data);
        }
        if (data.getSymbol().equals(node.data.getSymbol())){
            node.data.setVolume(data.getVolume());
            node.data.setPrice(data.getPrice());
            node.data.setMarketCap(data.getMarketCap());
            return node;
        }
        if (data.getSymbol().compareTo(node.data.getSymbol()) < 0){
            node.left = insertHelper(node.left, data);
        } else {
            node.right = insertHelper(node.right, data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height

        return rotate(node);
    }

    public void insert(Stock data) {
        root = insertHelper(root, data);
    }

    private Node deleteHelper(Node node, String symbol){
        if (node == null){
            return null;
        }
        if (symbol.equals(node.data.getSymbol())){
            if (node.left == null && node.right == null){
                return null;
            }
            if (node.left == null){
                return node.right;
            }
            if (node.right == null){
                return node.left;
            }
            Node min = findMin(node.right);
            node.data = min.data;
            node.right = deleteHelper(node.right, min.data.getSymbol());
        } else if (symbol.compareTo(node.data.getSymbol()) < 0){
            node.left = deleteHelper(node.left, symbol);
        } else {
            node.right = deleteHelper(node.right, symbol);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height
        return rotate(node);
    }

    public void delete(String symbol) {
        root = deleteHelper(root, symbol);
    }

    private Stock searchHelper(Node node, String symbol) {
        if (node == null) {
            return null;
        }
        if (symbol.equals(node.data.getSymbol())) {
            return node.data;
        }
        if (symbol.compareTo(node.data.getSymbol()) < 0) {
            return searchHelper(node.left, symbol);
        } else {
            return searchHelper(node.right, symbol);
        }
    }

    private Node findMin(Node node){
        if (node.left == null){
            return node;
        }
        return findMin(node.left);
    }

    public Stock search(String symbol) {
        return searchHelper(root, symbol);
    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private int balancingFactor(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private void printInOrderHelper(Node node) {
        if (node == null) {
            return;
        }
        printInOrderHelper(node.left);
        System.out.println(node.data);
        printInOrderHelper(node.right);
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printPreOrderHelper(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        printPreOrderHelper(node.left);
        printPreOrderHelper(node.right);
    }

    public void printPreOrder() {
        printPreOrderHelper(root);
    }

    private void printPostOrderHelper(Node node) {
        if (node == null) {
            return;
        }
        printPostOrderHelper(node.left);
        printPostOrderHelper(node.right);
        System.out.println(node.data);
    }

    public void printPostOrder() {
        printPostOrderHelper(root);
    }

    private int getSizeHelper(Node node){
        if (node == null){
            return 0;
        }
        return 1 + getSizeHelper(node.left) + getSizeHelper(node.right);
    }

    public int getSize(){
        return getSizeHelper(root);
    }


    private static class Node {
        Stock data;
        Node left;
        Node right;
        int height;

        public Node(Stock data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private void printLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.println(node.data);
        } else if (level > 1) {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    public void levelOrder() {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            System.out.println("Level " + i + ":");
            printLevel(root, i);
        }
    }

    public static void main(String[] args){
        // Give me test code for AVLTree

        try{
            AVLTree tree = new AVLTree();
            for (int i = 0; i < 26; i++){
                tree.insert(new Stock("A" + (char)('A' + i), 1.0, 1, 1));
            }
            tree.levelOrder();
            

        } catch (Exception e){
            System.out.println(e);
        }
    }
}

