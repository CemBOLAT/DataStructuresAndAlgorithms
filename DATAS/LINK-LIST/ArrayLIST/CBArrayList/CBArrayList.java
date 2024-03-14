
public class CBArrayList<E> {
    private final int   initialCap = 8;
    private E[] data;
    private int size;
    private int capacity;

    public CBArrayList(){
        size = 0;
        capacity = initialCap;
        data = (E[]) new Object[initialCap];
    }
    public boolean add(E element){
        if (size == capacity){
            reallocate();
        }
        data[size] = element;
        size++;
        return true;
    }
    public boolean add(int index, E element) throws Exception {
        if (index < 0 || index >= size){
            throw new Exception("Out OF Bounds!");
        }
        if (size == capacity){
            reallocate();
        }
        for (int i = size; i > index; i--){
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
        return true;
    }
    public E get(int index) throws Exception {
        if (index < 0 || index >= size){
            throw new Exception("Out OF Bounds!");
        }
        return data[index];
    }
    public E remove(int index) throws Exception {
        if (index < 0 || index >= size){
            throw new Exception("Out OF Bounds!");
        }
        E   removed = data[index];
        for (int i = index; i < size; i++){
            data[i] = data[i + 1];
        }        
        size--;
        return removed;
    }
    public boolean set(int index, E element) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Out OF Bounds!");
        }
        data[index] = element;
        return true;
    }
    private void reallocate(){
        E[] newData = (E[]) new Object[capacity * 2];
        capacity *= 2;
        for (int i  = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
    public int  size(){
        return size;
    }
}