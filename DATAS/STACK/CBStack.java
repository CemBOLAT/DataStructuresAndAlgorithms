import java.util.Stack;
import java.util.Vector;

public class CBStack<E> extends Vector<E> {
    public int size;
    private Node<E> head;

    public CBStack(){
        head = null;
        size = 0;
    }
    public boolean push(E data){
        head = new Node<E>(data, head);
        size++;
        return true;
    }
    public E pop(){
        if (head == null)
            return null;
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }
    public E peek(){
        if (head == null)
            return null;
        return head.data;
    }
    public void clear(){
        head = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean empty(){
        return size == 0;
    }
    public void printStack(){
        Node<E> temp = head;
        while (temp != null){
            System.out.print(temp.data + "-");
            temp = temp.next;
        }
        System.out.println();
    }
    private class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E stored){
            data = stored;
            next = null;
        }
        private Node(E stored, Node<E> newNext){
            data = stored;
            next = newNext;
        }
    }
}