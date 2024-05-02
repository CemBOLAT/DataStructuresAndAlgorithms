import java.util.ArrayList;

public class mySet <T> {
	private ArrayList<T> list;

	public mySet() {
		list = new ArrayList<T>();
	}

	public void add(T element) {
		if (!list.contains(element)) {
			list.add(element);
		}
	}

	public boolean remove(T element) {
		return list.remove(element);
	}

	public boolean contains(T element) {
		return list.contains(element);
	}

	public int size() {
		return list.size();
	}

	public void clear() {
		list.clear();
	}

	public boolean containsAll(mySet<T> other) { // submySet relation
		for (T element : other.list) {
			if (!list.contains(element)) {
				return false;
			}
		}
		return true;
	}

	public void addAll(mySet<T> other) {
		for (T element : other.list) {
			add(element);
		}
	}

	public void removeAll(mySet<T> other) {
		for (T element : other.list) {
			remove(element);
		}
	}


	public void retainAll(mySet<T> other) {
		mySet<T> temp = new mySet<T>();
		for (T element : list) {
			if (other.contains(element)) {
				temp.add(element);
			}
		}
		list = temp.list;
	}

	public void union(mySet<T> other) {
		addAll(other);
	}

	public void intersection(mySet<T> other) {
				retainAll(other);

	}

	public void difference(mySet<T> other){
		removeAll(other);
	}

	public Iterator<T> iterator() {
		return new Iterator<T>(this);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");

		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static class Iterator<T> {
		private ArrayList<T> list;
		private int index;

		public Iterator(mySet<T> set) {
			list = set.list;
			index = 0;
		}

		public boolean hasNext() {
			return index < list.size();
		}

		public T next() {
			return list.get(index++);
		}
	}
}
