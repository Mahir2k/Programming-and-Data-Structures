import java.util.Iterator;

public interface Collection<E>{
    public boolean add(E element);
    public boolean addAll(Collection<? extends E> c);
    public void clear();
    public boolean contains(Object o);
    public boolean containsAll(Collection<?> c);
    public boolean equals(Object o);
    public boolean isEmpty();
    public Iterator<E> iterator();
    public boolean remove(Object o);
    public boolean removeAll(Collection<?> c);
    public boolean retainAll(Collection<?> c);
    public int size();
    public Object[] toArray();
}