package myContainerPackage;
import java.util.Iterator;

public interface JavaContainer <T> {

	public void			Add(T n);
	public void			Remove(T n);
	public int			Size();
	public Iterator<T>	getIterator();

}


