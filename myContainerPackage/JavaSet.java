package myContainerPackage;

import myContainerPackage.JavaContainer;
import java.util.Iterator;

public class JavaSet<T extends Comparable<T>> implements JavaContainer<T> {
	private T[] data;
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	public JavaSet() {
		data = (T[]) new Comparable[2];
		capacity = 2;
		size = 0;
	}

	public JavaSet(int n) {
		if (n <= 0) {
			throw new RuntimeException("Size of Capacity is invalid !");
		}
		capacity = n;
		data = (T[]) new Comparable[n];
		size = 0;
	}

	public JavaSet(JavaSet<T> other) {
		size = other.size;
		capacity = other.capacity;
		data = (T[]) new Comparable[capacity];
		for (int i = 0; i < size; i++) {
			data[i] = other.data[i];
		}
	}

	public T getData(int index) {
		if (index < 0) {
			throw new RuntimeException("Giving index is invalid !");
		}
		return data[index];
	}

	public int Size() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	public Iterator<T> getIterator() {
		return new IteratorImpl();
	}

	public void Add(T n) {
		if (isIn(n))
			throw new RuntimeException("Element is in Set");
		if (size == capacity) {
			capacity *= 2;
		}
		if (size == 0) {
			data[0] = n;
			size++;
			return;
		}
		T[] newData = (T[]) new Comparable[capacity];
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (data[i].compareTo(n) < 0) {
				newData[index++] = data[i];
			} else {
				newData[index++] = n;
				for (int j = i; j < size; j++) {
					newData[index++] = data[j];
				}
				break;
			}
		}
		if (index == size) { // if the element is the largest
			newData[index] = n;
		}
		data = newData;
		size++;
	}

	public void Remove(T n) {
		if (!isIn(n))
			throw new RuntimeException("Element is not in Set");
		if (size - 1 == capacity / 2)
			capacity /= 2;
		var newData = (T[]) new Comparable[capacity];
		int index = 0;
		for (var i = 0; i < size; i++) {
			if (!data[i].equals(n))
				newData[index++] = data[i];
		}
		data = newData;
		size--;
	}

	public boolean isIn(T element) {
		for (var i = 0; i < size; i++) {
			if (data[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder s = new StringBuilder("[");
		for (var i = 0; i < size; i++) {
			s.append(data[i]);
			if (i != size - 1) {
				s.append(", ");
			}
		}
		s.append("]");
		return String.format("Set: %s, size: %d, capacity: %d", s.toString(), size, capacity);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		JavaSet<?> other = (JavaSet<?>) obj;

		if (size != other.size) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!data[i].equals(other.data[i])) {
				return false;
			}
		}

		return true;
	}

	private class IteratorImpl implements Iterator<T> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			return data[index++];
		}
	}
}
