package myContainerPackage;
import myContainerPackage.JavaContainer;
import java.util.Iterator;

public class JavaVector<T> implements JavaContainer<T> {

	private T[]	data;
	private int	size;
	private int	capacity;

	@SuppressWarnings("unchecked")
	public JavaVector() {
		data = (T[]) new Object[2];
		size = 0;
		capacity = 2;
	}

	@SuppressWarnings("unchecked")
	public JavaVector(int n) {
		if (n <= 0){
			throw new RuntimeException("Size of Capacity is invalid !");
		}
		data = (T[]) new Object[n];
		size = 0;
		capacity = n;
	}

	public JavaVector(JavaVector<T> other){
		size = other.size;
		capacity = other.capacity;
		data = (T[]) new Object[capacity];
		for(var i = 0 ; i < size ; i++){
			data[i] = other.data[i];
		}
	}

	public T getData(int index) {
		if (index < 0){
			throw new RuntimeException("Giving index is invalid !");
		}
		return data[index];
	}

	public void setExactData(int index, T newData){
		if (index < 0){
			throw new RuntimeException("Giving index is invalid !");
		}
		data[index] = newData;
	}

	public int getCapacity() {
		return capacity;
	}

	public Iterator<T> getIterator() {
		return new IteratorImpl();
	}

	@SuppressWarnings("unchecked")
	public void Add(T element) {
		if (size == capacity) {
			capacity *= 2;
			var newData = (T[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
		data[size++] = element;
	}

	public void Remove(T element) {
		if (size == 0) {
			throw new RuntimeException("Vector is empty");
		}
		if (!isIn(element)) {
			throw new RuntimeException("Element not in vector");
		}
		if (size - 1 <= capacity / 2) {
			capacity /= 2;
		}
		var newData = (T[]) new Object[capacity];
		int newSize = 0;
		for (int i = 0; i < size; i++) {
			if (!data[i].equals(element)) {
				newData[newSize++] = data[i];
			}
		}
		data = newData;
		size = newSize;
	}

	public boolean isIn(T element) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	public int Size() {
		return size;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		var other = (JavaVector<?>) obj;

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

	public String toString() {
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			s.append(data[i]);
			if (i != size - 1) {
				s.append(", ");
			}
		}
		s.append("]");
		return String.format("Vector: %s, size: %d, capacity: %d", s.toString(), size, capacity);
	}

	private class IteratorImpl implements Iterator<T> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < Size();
		}

		@Override
		public T next() {
			return data[index++];
		}
	}
}
