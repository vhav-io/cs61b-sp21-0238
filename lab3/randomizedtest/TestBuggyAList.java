package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Vaibhav.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        BuggyAList<Integer> list2 = new BuggyAList<>();
        //adding at last
        list1.addLast(2);
        list2.addLast(2);

        list1.addLast(3);
        list2.addLast(3);

        list1.addLast(5);
        list2.addLast(5);


        //removing at last
        assertEquals(list1.size(), list2.size());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
    }

    @Test
    public void randomizeTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), L2.size());
            } else if (operationNumber == 2){
                // getLast
                if(L.size() == 0){
                    continue;
                } else {
                    assertEquals(L.getLast(), L2.getLast());
                }
            } else if(operationNumber == 3){
                //removeLast
                if(L.size() == 0){
                    continue;
                } else {
                    assertEquals(L.removeLast(), L2.removeLast());
                }
            }
        }
    }
}
