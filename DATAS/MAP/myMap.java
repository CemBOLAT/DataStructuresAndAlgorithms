import java.util.ArrayList;

public class myMap<T> {
	private ArrayList<T> list;
	private ArrayList<T> key;
	private ArrayList<T> value;

	public myMap() {
		list = new ArrayList<T>();
		key = new ArrayList<T>();
		value = new ArrayList<T>();
	}

	public void put(T key, T value) {
		if (!list.contains(key)) {
			list.add(key);
			this.key.add(key);
			this.value.add(value);
		}
	}

	public T get(T key) {
		int index = list.indexOf(key);
		if (index == -1) {
			return null;
		}
		return value.get(index);
	}

	public boolean remove(T key) {
		int index = list.indexOf(key);
		if (index == -1) {
			return false;
		}
		list.remove(index);
		this.key.remove(index);
		value.remove(index);
		return true;
	}

	public boolean containsKey(T key) {
		return list.contains(key);
	}

	public boolean containsValue(T value) {
		return this.value.contains(value);
	}

	public int size() {
		return list.size();
	}

	public void clear() {
		list.clear();
		key.clear();
		value.clear();
	}

	public boolean containsAll(myMap<T> other) { // submyMap relation
		for (T key : other.list) {
			if (!list.contains(key)) {
				return false;
			}
		}
		return true;
	}

	public void putAll(myMap<T> other) {
		for (int i = 0; i < other.size(); i++) {
			put(other.key.get(i), other.value.get(i));
		}
	}

	public void removeAll(myMap<T> other) {
		for (T key : other.list) {
			remove(key);
		}
	}

	public void retainAll(myMap<T> other) {
		myMap<T> temp = new myMap<T>();
		for (T key : list) {
			if (other.containsKey(key)) {
				temp.put(key, get(key));
			}
		}
		list = temp.list;
		key = temp.key;
		value = temp.value;
	}
}
