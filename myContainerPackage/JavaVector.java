package myContainerPackage;
import myContainerPackage.JavaContainer;
import java.util.Iterator;
import java.util.Scanner;

/**
 * JavaVector class
 *
 * @param <T> type of data
 * It is a generic class for JavaVector.
 * It has 15 methods:
 *
 * 1. Add(T n) : add an element to the container
 * 2. Remove(T n) : remove an element from the container
 * 3. Size() : return the size of the container
 * 4. getCapacity() : return the capacity of the container
 * 5. getData(int index) : return the data at the given index
 * 6. isIn(T element) : return true if the element is in the container
 * 7. getIterator() : return an iterator of the container
 * 8. toString() : return a string representation of the container
 * 9. equals(Object obj) : return true if the given object is equal to the container
 * 10. JavaVector() : default constructor
 * 11. JavaVector(int n) : constructor with capacity
 * 12. JavaVector(JavaVector other) : copy constructor
 * 13. IteratorImpl : private class for iterator
 * 14. hasNext() : return true if the iterator has next element
 * 15. next() : return the next element of the iterator
*/
public class JavaVector<T> implements JavaContainer<T> {

	private T[]	data;
	private int	size;
	private int	capacity;

	/**
	 * JavaVector constructor

	 * It creates a JavaVector object with default capacity 2
	*/
	@SuppressWarnings("unchecked")
	public JavaVector() {
		data = (T[]) new Object[2];
		size = 0;
		capacity = 2;
	}
	/**
	 * JavaVector constructor

	 * @param n capacity of the JavaVector
	 * It creates a JavaVector object with given capacity
	*/
	@SuppressWarnings("unchecked")
	public JavaVector(int n) {
		if (n <= 0){
			throw new RuntimeException("Size of Capacity is invalid !");
		}
		data = (T[]) new Object[n];
		size = 0;
		capacity = n;
	}
	/**
	 * JavaVector constructor

	 * @param other JavaVector object
	 * It creates a JavaVector object with given JavaVector object
	*/
	public JavaVector(JavaVector<T> other){
		size = other.size;
		capacity = other.capacity;
		data = (T[]) new Object[capacity];
		for(var i = 0 ; i < size ; i++){
			data[i] = other.data[i];
		}
	}
	/**
	 * getData method

	 * @param index index of the data
	 * @return data at the given index
	 * It returns the data at the given index
	*/
	public T getData(int index) {
		if (index < 0){
			throw new RuntimeException("Giving index is invalid !");
		}
		return data[index];
	}
	/**
	 * getExactData method

	 * @param index index of the data
	 * @param newData new data of the given index
	 * It returns the data at the given index
	*/
	public void setExactData(int index, T newData){
		if (index < 0){
			throw new RuntimeException("Giving index is invalid !");
		}
		data[index] = newData;
	}

	/**
	 * getCapacity method

	 * @return capacity of the container
	 * It returns the capacity of the container
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	 * getIterator method

	 * @return iterator of the container
	 * It returns an iterator of the container
	*/
	public Iterator<T> getIterator() {
		return new IteratorImpl();
	}

	/**
	 * Add method.
	 * @param element element to be added.
	 * It adds the given element to the container.
	 * If the size is equal to capacity, it doubles the capacity.
	 * If the size is equal to 0, it adds the element to the first index.
	 * Otherwise, it adds the element to the end of the container.
	 * It increases the size by 1.
	 * It gives a warning if the element is already in the container.
	 * It throws RuntimeException if the element is null.
	 * It throws RuntimeException if the element is not comparable.
	 */
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

	/**
	 * Remove method.
	 * @param element element to be removed.
	 * It removes the given element from the container.
	 * If the size is equal to 0, it throws RuntimeException.
	 * If the element is not in the container, it throws RuntimeException.
	 * If the size is less than or equal to capacity / 2, it halves the capacity.
	 * It creates a new array with the new capacity.
	 * It copies the elements except the given element to the new array.
	 * It assigns the new array to the data.
	 * It decreases the size by 1.
	 */
	public void Remove(T element) {
		if (size == 0) {
			throw new RuntimeException("Vector is empty");
		}
		if (!isIn(element)) {
			System.out.println("Element not in vector");
			return;
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

	/**
	 * isIn method.
	 * @param element element to be checked.
	 * @return true if the element is in the container.
	 * It returns true if the element is in the container.
	 * Otherwise, it returns false.
	 */
	public boolean isIn(T element) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(element)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Size method.
	 * @return size of the container.
	 * It returns the size of the container.
	 */
	@Override
	public int Size() {
		return size;
	}
	/**
	 * hasNext method
	 * @return true if the iterator has next element
	 * It returns true if the iterator has next element.
	 * Otherwise, it returns false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		var other = (JavaVector<T>) obj;

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

	/**
	 * next method
	 * @return the next element of the iterator.
	 * It returns the next element of the iterator.
	 */
	@Override
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

	/**
	 * IteratorImpl class.
	 * It is a private class for iterator.
	 */
	private class IteratorImpl implements Iterator<T> {
		private int index = 0;
		/**
		 * hasNext() method.
		 * @return true if the iterator has next element.
		 * It returns true if the iterator has next element.
		 * Otherwise, it returns false.
		 */
		@Override
		public boolean hasNext() {
			return index < Size();
		}
		/**
		 * next() method
		 * @return the next element of the iterator
		 * It returns the next element of the iterator.
		 */
		@Override
		public T next() {
			return data[index++];
		}
	}
}
