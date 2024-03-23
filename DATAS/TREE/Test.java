public class Test {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.add(3);
		tree.add(5);
		tree.add(7);
		tree.add(1);
		tree.remove(1);
		tree.remove(7);
		System.out.println(tree.getLargest());
		System.out.println(tree.getSmallest());
		System.out.println(tree.height());
		System.out.println(tree.size());
	}
}
