package deque;

public interface Deque<generic> {
    public void addFirst(generic item);
    public void addLast(generic item);
    public int size() ;
    default boolean isEmpty(){
        return size() == 0;
    }

    public void printDeque();
    public generic removeFirst();
    public generic removeLast();
    public generic get(int index);
}
