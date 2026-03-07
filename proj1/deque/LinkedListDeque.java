package deque;

import java.util.Iterator;

public class LinkedListDeque<generic>{
    public intNode sentinal;
    public int size = 0;

    public LinkedListDeque(){
        sentinal = new  intNode();
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }

    public class intNode {
        public intNode prev;
        public generic item;
        public intNode next;
    }


    //addFirst
    public void addFirst(generic item){
        size += 1;
        intNode p = new intNode();
        p.item = item;

        intNode oldFirst = sentinal.next;
        p.prev = sentinal;
        p.next = oldFirst;
        sentinal.next = p;
        oldFirst.prev = p;
    }

    //addLast
    public void addLast(generic item){
        size += 1;
        intNode p = new intNode();
        p.item = item;
        intNode oldLast = sentinal.prev;
        p.next = sentinal;
        p.prev = oldLast;
        sentinal.prev = p;
        oldLast.next = p;
    }

    //isEmpty
    public boolean isEmpty(){
    if (size == 0){return true;}
    return false;
    }

    //size
    public int size(){
        return size;
    }

    //printDeque
    public void printDeque(){
        intNode p = sentinal.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();

    }


    //removeFirst
    public generic removeFirst(){
        if (isEmpty()) {
            return null;
        }
        intNode p = sentinal.next;
        generic first = p.item;
        p = p.next;
        sentinal.next = p;
        p.prev = sentinal;
        size -= 1;
        return first;
    }

    //remvoveLast
    public generic removeLast(){
        if (isEmpty()) {
            return null;
        }
        intNode p = sentinal.prev;
        generic last = p.item;
        p = p.prev;
        p.next = sentinal;
        sentinal.prev = p;
        size -= 1;
        return last;
    }

    //get element at index
    public generic get(int index){
        if(index < 0 || index >= size){return null;}

        intNode p = sentinal.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    //make deque itrabel
    private class LinkedListIterator implements Iterator<generic>{
        public int wizPos;
        public LinkedListIterator(){
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
        return new LinkedListIterator();
    }

    public boolean equals(Object o){
        if(o instanceof LinkedListDeque){
            LinkedListDeque<generic> lld = (LinkedListDeque<generic>) o;
            if(size != lld.size){return false;}
            intNode ptr1 = this.sentinal.next;
            intNode ptr2 = lld.sentinal.next;
            int i = 0;
            while( i < lld.size){
                if(!ptr1.item.equals(ptr2.item)){return false;}
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
                i++;
            }
            return true;
        }
        return false;
    }
    
}