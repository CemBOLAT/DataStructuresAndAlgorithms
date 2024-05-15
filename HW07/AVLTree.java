
public class AVLTree {

    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private void rotateRight(Node node){
        Node left = node.left;
        Node left_right = left.right;

        left.right = node;
        node.left = left_right;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));

        return left;
    }

    private void rotateLeft(Node node){
        Node right = node.right;
        Node right_left = right.left;

        right.left = node;
        node.right = right_left;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));

        return right;
    }

    private void rotate(Node node, Stock data) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balancingFactor(node);
        if (balance > 1){ // left is heavier
            Node left = node.left;
            if (left != null && data.getSymbol().compareTo(left.data.getSymbol()) < 0){ // left left case if the inserted data is the most least.
                return rightRotate(node);
            }
            else if (left != null && data.getSymbol().compareTo(left.data.getSymbol()) > 0){ // left right case if the inserted data is more than the left child.
                node.left = leftRotate(node.left); // important to update the left child after rotation
                return rightRotate(node); // rotate the node
            }
        }
        else if (balance < -1){ // right is heavier
            Node right = node.right;
            if (right != null && data.getSymbol().compareTo(right.data.getSymbol()) > 0){ // right right case if the inserted data is the most heaviest.
                return leftRotate(node);
            }
            else if (right != null && data.getSymbol().compareTo(right.data.getSymbol()) < 0){ // right left case if the inserted data is less than the right child.
                node.right = rightRotate(node.right); // important to update the right child after rotation
                return leftRotate(node); // rotate the node
            }
        }
    }

    private void insertHelper(Node node, Stock data) throws InvalidOperation {
        if (node == null){
            return new Node(data);
        }
        if (data.getSymbol().equals(node.data.getSymbol())){
            throw new InvalidOperation("Stock already exists");
        }
        if (data.getSymbol().compareTo(node.data.getSymbol()) < 0){
            node.left = insertHelper(node.left, data);
        } else {
            node.right = insertHelper(node.right, data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height

        rotate(node, data);
    }

    public void insert(Stock data) {
        root = insertHelper(root, data);
        return;
    }

    private void deleteHelper(Node node, Stock data) {
        if (node == null){
            return null;
        }
        if (data.getSymbol().equals(node.data.getSymbol())){
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
            node.right = deleteHelper(node.right, min.data);
        }
        if (data.getSymbol().compareTo(node.data.getSymbol()) < 0){
            node.left = deleteHelper(node.left, data);
        } else {
            node.right = deleteHelper(node.right, data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // update height

        rotate(node, data);
        
    }

    public void delete(Stock data) {
        root = deleteHelper(root, data);
    }

    private boolean searchHelper(Node node, Stock data) {
        if (node == null) {
            return false;
        }
        if (data.getSymbol().equals(node.data.getSymbol())) {
            return true;
        }
        if (data.getSymbol().compareTo(node.data.getSymbol()) < 0) {
            return searchHelper(node.left, data);
        } else {
            return searchHelper(node.right, data);
        }
        return false; // unreachable
    }

    public boolean search(Stock data) {
        return searchHelper(root, data);
    }

    private height(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private balancingFactor(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
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

    public static void main(String[] args){
        
    }
}