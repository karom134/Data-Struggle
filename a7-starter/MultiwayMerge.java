import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MultiwayMerge {
    public static LinkedList<Integer> mergeAll(LinkedList<Integer>[] lists) {
        LinkedList<Integer> answer = new LinkedList<Integer>();
        PriorityQueue<LinkedList<Integer>> PQ = new PriorityQueue<LinkedList<Integer>>(
                new Comparator<LinkedList<Integer>>() {
                    @Override
                    public int compare(LinkedList<Integer> a, LinkedList<Integer> b) {
                        return a.element() - b.element();
                    }
                });
        for (LinkedList<Integer> ele : lists) {
            if (!ele.isEmpty()) {
                PQ.offer(ele);
            }
        }
        while (!PQ.isEmpty()) {
            LinkedList<Integer> tmp = PQ.poll();
            Integer min = tmp.removeFirst();
            answer.addLast(min);
            if (!tmp.isEmpty()) {
                PQ.offer(tmp);
            }
        }
        return answer;
    }
    public static void main(String arg[]){
        LinkedList<Integer> sample1=new LinkedList<Integer>();
        LinkedList<Integer> sample2=new LinkedList<Integer>();
        LinkedList<Integer> sample3=new LinkedList<Integer>();
        sample1.addLast(3);
        sample1.addLast(4);
        sample1.addLast(9);
        sample2.addLast(0);
        sample2.addLast(5);
        sample2.addLast(6);
        sample3.addLast(1);
        sample3.addLast(2);
        sample3.addLast(7);
        sample3.addLast(8);
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] tester = (LinkedList<Integer>[]) new LinkedList<?>[3];
        tester[0]=sample1;
        tester[1]=sample2;
        tester[2]=sample3;
        System.out.println(mergeAll(tester).toString());
        int[] test=new int[8];
        System.out.println(test.length);
    }
}