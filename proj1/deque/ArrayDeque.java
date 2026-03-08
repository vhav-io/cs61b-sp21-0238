package deque;

import java.util.Iterator;

public class ArrayDeque<generic> implements Deque<generic>{
    public int size = 0;
    public generic[] deque;
    public int nextFirst;
    public int nextLast;
    public ArrayDeque() {
        deque = (generic[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    public void addFirst(generic item){
        if (size == deque.length) {
            resize(size * 2);
        }
        size += 1;
        deque[nextFirst] = item;
        if(nextFirst == 0){
            nextFirst = deque.length - 1;
        } else{nextFirst -= 1;}

    }
    public void addLast(generic item){
        if(size == deque.length){
            resize(size * 2);
        }
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
        if (deque.length >= 16 && (size - 1) < deque.length / 4) {
            resize(deque.length / 2); // Cut the total array capacity in half!
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
        if (deque.length >= 16 && (size - 1) < deque.length / 4) {
            resize(deque.length / 2); // Cut the total array capacity in half!
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

    public boolean equals(Object o){
        if(o instanceof ArrayDeque){
            ArrayDeque<generic> a = (ArrayDeque<generic>) o;
            if(a.size() != size){return false;}
            for(int i = 0; i < size; i++){
                if(!a.get(i).equals(this.get(i))){return false;}
            }
            return true;
        }
        return false;
    }

    private class ArrayIterator implements Iterator<generic>{
        public int wizPos;
        public ArrayIterator(){
            wizPos = 0;
        }
        public boolean hasNext(){
            return wizPos < size;
        }
        public generic next(){
            generic item = get(wizPos);
            wizPos += 1;
            return item;

        }
    }

    public Iterator<generic> iterator(){
        return new ArrayIterator();
    }

    private void resize(int newSize){
        generic[] newDeque = (generic[]) new Object[newSize];
        for(int i = 0; i < size; i++){
            newDeque[i] = this.get(i);
        }
        deque = newDeque;
        nextFirst = newSize - 1;
        nextLast = size;
    }
}
