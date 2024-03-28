import java.util.EmptyStackException;

public class StackTest {
    public static void main(String[] args) {
        // Creating a new stack
        Stack<Integer> stack = new Stack<>();

        // Pushing elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Printing the size of the stack
        System.out.println("Size of the stack: " + stack.size()); // Should print 3

        // Peeking at the top element
        System.out.println("Top element: " + stack.peek()); // Should print 3

        // Popping elements from the stack
        System.out.println("Popped element: " + stack.pop()); // Should print 3
        System.out.println("Popped element: " + stack.pop()); // Should print 2
        System.out.println("Popped element: " + stack.pop()); // Should print 1

        // Trying to pop from an empty stack (should throw an exception)
        try {
            stack.pop();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Tried to pop from an empty stack. Exception caught: " + e.getMessage());
        }

        // Checking if the stack is empty
        System.out.println("Is the stack empty? " + stack.isEmpty()); // Should print true
    }
}
