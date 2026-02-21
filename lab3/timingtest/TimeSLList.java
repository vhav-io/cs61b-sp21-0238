package timingtest;
import edu.princeton.cs.algs4.Stopwatch;


/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(SLList<Integer> Ns, SLList<Double> times, SLList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> L2 = new SLList<Integer>();
        SLList<Double> time = new SLList<Double>();
        SLList<Integer> ops = new SLList<Integer>();
        int M = 10000;
        
        for(int N = 1000; N<=128000; N*=2){
            SLList<Integer> testList = new SLList<>();
            for(int i = 0; i<N; i++){
                testList.addLast(i);
            }
            L2.addLast(N);
            ops.addLast(M);

            Stopwatch s1 = new Stopwatch();
            for(int i = 0; i < M; i++){
                testList.getLast();

            }
            double timeinseconds = s1.elapsedTime();
            time.addLast(timeinseconds);
        }



        printTimingTable(L2, time, ops);
    }
}