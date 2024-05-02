public class myHash<K,T>{
	private Entry<K,T>[] table;
	private static final int START_CAP = 10;
	private static final double LOAD_FACTOR = 0.75;
	private int numKeys;
	private int numDeletes;
	private Entry<K,T> DELETED;

	public myHash(){
		table = new Entry[START_CAP];
		DELETED = new Entry<K,T>(null,null);
	}

	private static class Entry<K,T>{
		private K key;
		private T value;

		public Entry(K key, T value){
			this.key = key;
			this.value = value;
		}

		public K getKey(){
			return key;
		}

		public T getValue(){
			return value;
		}

		public T setValue(T val){
			T oldVal = value;
			value = val;
			return oldVal;
		}

		public boolean equals(Object o){
			if(!(o instanceof Entry)) return false;
			Entry<K,T> e = (Entry<K,T>) o;
			return key.equals(e.getKey());
		}
	}
}
