import java.util.Iterator;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        // Creating a new double linked list
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        // Adding elements to the list
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        // Displaying elements using Iterator
        System.out.println("Elements in the list:");
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Removing an element at index 2
        list.remove(2);
        System.out.println("\nAfter removing element at index 2:");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Adding an element at index 1
        list.add(1, 25);
        System.out.println("\nAfter adding element 25 at index 1:");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Checking if the list contains 10
        System.out.println("\nDoes the list contain 10? " + list.contains(10));

        // Getting the index of element 20
        System.out.println("Index of 20: " + list.indexOf(20));

        // Getting the size of the list
        System.out.println("Size of the list: " + list.size());

        // Getting and setting elements
        System.out.println("Element at index 1: " + list.get(1));
        list.set(1, 30);
        System.out.println("Element at index 1 after setting: " + list.get(1));

        // Testing adding elements with iterator
        System.out.println("\nAdding 100 and 200 with iterator:");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
            if (value == 10) {
                iterator.add(100);
                iterator.add(200);
            }
        }
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Testing removing elements with iterator
        System.out.println("\nRemoving elements greater than 100 with iterator:");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
            if (value > 100) {
                iterator.remove();
            }
        }
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
