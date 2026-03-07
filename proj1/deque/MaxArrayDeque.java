package deque;

import java.util.Comparator;

public class MaxArrayDeque <generic>{
    public int size = 0;
    public int nextFirst;
    public int nextLast;
    public generic deque[];
    private Comparator<generic> defaultComparator;
    public MaxArrayDeque(Comparator<generic> c){
        deque = (generic[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        defaultComparator = c;
    }
    public void addFirst(generic item){
        size += 1;
        deque[nextFirst] = item;
        if(nextFirst == 0){
            nextFirst = deque.length - 1;
        } else{nextFirst -= 1;}

    }
    public void addLast(generic item){
        size += 1;
        deque[nextLast] = item;
        if(nextLast == deque.length - 1){
            nextLast = 0;
        } else{nextLast += 1;}

    }
    public generic removeFirst(){
        if(isEmpty()){
            return null;
        }
        if(nextFirst == deque.length-1){
            nextFirst = -1;
        }
        generic returnItem = deque[nextFirst+1];
        size -= 1;
        deque[nextFirst+1] = null;
        nextFirst += 1;
        return returnItem;
    }
    public generic removeLast(){
        if(isEmpty()){
            return null;
        }
        if(nextLast == 0){
            nextLast = deque.length;
        }
        generic returnItem = deque[nextLast-1];
        size -= 1;
        deque[nextLast-1] = null;
        nextLast -= 1;
        return returnItem;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }

        return false;
    }
    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public generic get(int index){
        if(index < 0 || index >= size){return null;}
        int i = (index + nextFirst + 1) % deque.length;
        return deque[i];
    }

    public generic max(){
        return max(defaultComparator);
    }
    public generic max(Comparator<generic> c){
        if(isEmpty()){return null;}
        generic maxItem = get(0);
        for(int i = 1; i < size; i++){
            int result = c.compare(get(i), maxItem);
            if(result > 0){
                maxItem = get(i);
            }
        }
        return maxItem;
    }
}
