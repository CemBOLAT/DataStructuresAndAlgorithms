import java.util.Iterator;

public class DoubleLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        Node<E> tmp = head;
        while (tmp != null) {
            if (element.equals(tmp.data))
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    public int indexOf(E element) {
        Node<E> tmp = head;
        int index = 0;
        while (tmp != null) {
            if (element.equals(tmp.data))
                return index;
            tmp = tmp.next;
            index++;
        }
        return -1;
    }

    public void add(E element) {
        if (size == 0) {
            Node<E> node = new Node<>(element);
            head = node;
            tail = node;
        } else {
            Node<E> node = new Node<>(element, null, tail);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        if (index == 0) {
            Node<E> node = new Node<>(element, head, null);
            if (head != null)
                head.prev = node;
            head = node;
            if (size == 0)
                tail = node;
        } else if (index == size) {
            Node<E> node = new Node<>(element, null, tail);
            tail.next = node;
            tail = node;
        } else {
            Node<E> tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            Node<E> node = new Node<>(element, tmp, tmp.prev);
            tmp.prev.next = node;
            tmp.prev = node;
        }
        size++;
    }

    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        Node<E> nodeToRemove;
        if (index == 0) {
            nodeToRemove = head;
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
        } else if (index == size - 1) {
            nodeToRemove = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<E> tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            nodeToRemove = tmp;
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
        }
        size--;
        return nodeToRemove.data;
    }

    public E set(int index, E element) throws IndexOutOfBoundsException {
        Node<E> tmp = getNodeAtIndex(index);
        E oldValue = tmp.data;
        tmp.data = element;
        return oldValue;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        return getNodeAtIndex(index).data;
    }

    private Node<E> getNodeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        Node<E> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public int size() {
        return size;
    }

    public Iterator<E> listIterator() {
        return new ListIterator<>(head);
    }

    public Iterator<E> listIterator(int index) {
        return new ListIterator<>(getNodeAtIndex(index));
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E data) {
            this(data, null, null);
        }

        private Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ListIterator<T> implements Iterator<E> {
        private Node<E> node;

        public ListIterator(Node<E> start) {
            node = start;
        }

        public boolean hasNext() {
            return node != null;
        }

        public boolean hasPrevious() {
            return node != null && node.prev != null;
        }

        public E next() {
            if (!hasNext())
                throw new IllegalStateException("No next element");
            E value = node.data;
            node = node.next;
            return value;
        }

        public E previous() {
            if (!hasPrevious())
                throw new IllegalStateException("No previous element");
            if (node == null)
                node = tail;
            else
                node = node.prev;
            return node.data;
        }

        public void add(E obj) {
            Node<E> newNode = new Node<>(obj);
            if (node == null) { // Adding at the end
                if (tail != null) {
                    tail.next = newNode;
                    newNode.prev = tail;
                }
                tail = newNode;
                if (head == null) {
                    head = newNode;
                }
            } else if (node.prev == null) { // Adding at the beginning
                newNode.next = node;
                node.prev = newNode;
                head = newNode;
            } else { // Adding in the middle
                newNode.next = node;
                newNode.prev = node.prev;
                node.prev.next = newNode;
                node.prev = newNode;
            }
            size++;
        }

        public void remove() {
            if (node == null)
                throw new IllegalStateException("No element to remove");
            Node<E> prevNode = node.prev;
            Node<E> nextNode = node.next;
            if (prevNode != null)
                prevNode.next = nextNode;
            if (nextNode != null)
                nextNode.prev = prevNode;
            if (node == head)
                head = nextNode;
            if (node == tail)
                tail = prevNode;
            node = nextNode;
            size--;
        }
    }
}
