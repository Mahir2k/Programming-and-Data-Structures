import java.util.ListIterator;
import java.util.Comparator;

public interface List<E> extends Collection<E>{
    public void add(int index, E element);
    public void addFirst(E element);
    public void addLast(E element);
    public E get(int index);
    public E getFirst();
    public E getLast();
    public int indexOf(Object o);
    public int lastIndexOf(Object o);
    public ListIterator<E> listIterator();
    public ListIterator<E> listIterator(int index);
    public E remove(int index);
    public E removeFirst();
    public E removeLast();
    public List<E> reversed();
    public E set(int index, E element);
    public void sort(Comparator<? super E> c);
    public List<E> subList(int from, int to);
}