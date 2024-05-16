
// https://www.youtube.com/watch?v=jDM6_TnYIqE&t=1535s
// CHANNEL : ABDUL BARI
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

    private Node rotate(Node node, String symbol) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balancingFactor(node);
        if (balance > 1){ // left is heavier
            Node left = node.left;
            if (left != null && symbol.compareTo(left.data.getSymbol()) < 0){ // left left case if the inserted data is the most least.
                return rotateRight(node);
            }
            else if (left != null && symbol.compareTo(left.data.getSymbol()) > 0){ // left right case if the inserted data is more than the left child.
                node.left = rotateLeft(node.left); // important to update the left child after rotation
                return rotateRight(node); // rotate the node
            }
        }
        else if (balance < -1){ // right is heavier
            Node right = node.right;
            if (right != null && symbol.compareTo(right.data.getSymbol()) > 0){ // right right case if the inserted data is the most heaviest.
                return rotateLeft(node);
            }
            else if (right != null && symbol.compareTo(right.data.getSymbol()) < 0){ // right left case if the inserted data is less than the right child.
                node.right = rotateRight(node.right); // important to update the right child after rotation
                return rotateLeft(node); // rotate the node
            }
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
        }
        if (data.getSymbol().compareTo(node.data.getSymbol()) < 0){
            node.left = insertHelper(node.left, data);
        } else {
            node.right = insertHelper(node.right, data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height

        return rotate(node, data.getSymbol());
    }

    public void insert(Stock data) {
        root = insertHelper(root, data);
        return;
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
        }
        if (symbol.compareTo(node.data.getSymbol()) < 0){
            node.left = deleteHelper(node.left, symbol);
        } else {
            node.right = deleteHelper(node.right, symbol);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height
        return rotate(node, symbol);
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

        public Node(Stock data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 1;
        }
    }

    //public static void main(String[] args){
    //    // Give me test code for AVLTreeü
//
    //    try{
    //        AVLTree tree = new AVLTree();
    //        tree.insert(new Stock("AOOGL", 200.0, 2000, 2000000));
    //        tree.insert(new Stock("BAPL", 100.0, 1000, 1000000));
    //        tree.printInOrder();
    //        System.out.println("**********");
    //        tree.insert(new Stock("C", 300.0, 3000, 3000000));
    //        tree.printInOrder();
    //        System.out.println("**********");
//
    //        tree.insert(new Stock("D", 400.0, 4000, 4000000));
    //        tree.insert(new Stock("E", 500.0, 5000, 5000000));
    //        tree.insert(new Stock("F", 600.0, 6000, 6000000));
    //        tree.insert(new Stock("G", 700.0, 7000, 7000000));
    //        tree.insert(new Stock("H", 800.0, 8000, 8000000));
    //        tree.printInOrder();
    //        System.out.println("**********");
//
    //        tree.delete(new Stock("AOOGL", 200.0, 2000, 2000000));
//
    //        tree.printInOrder();
    //        System.out.println("**********");
//
    //    } catch (Exception e){
    //        System.out.println(e);
    //    }
    //}
}