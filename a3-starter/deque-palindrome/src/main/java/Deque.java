public interface Deque<T> {
    public boolean isEmpty();
    public int size();
    public boolean isFull();
    public int EndtoEnd(int n);
    public void addFirst(T item);
    public void addLast(T item);
    public T removeFirst();
    public T removeLast();
    public String toString();
    public T get(int index);
    public void resize(int new_cap);
    public T checker(int n);
    public String toString2();
}