package myContainerPackage;
import java.util.Iterator;

/***
 * JavaContainer interface
 *
 * @param <T> type of data
 * It is a generic interface for JavaSet and JavaVector
 * It has 4 methods:
 * 1. Add(T n) : add an element to the container
 * 2. Remove(T n) : remove an element from the container
 * 3. Size() : return the size of the container
 * 4. getIterator() : return an iterator of the container
*/
public interface JavaContainer <T> {
	/**
	 * Add method
	 *
	 * @param n element to be added
	 * It adds the given element to the container
	*/
	public void			Add(T n);
	/**
	 * Remove method
	 *
	 * @param n element to be removed
	 * It removes the given element from the container
	*/
	public void			Remove(T n);
	/**
	 * Size method
	 *
	 * @return size of the container
	 * It returns the size of the container
	*/
	public int			Size();
	/**
	 * getIterator method
	 *
	 * @return iterator of the container
	 * It returns an iterator of the container
	*/
	public Iterator<T>	getIterator();

}


