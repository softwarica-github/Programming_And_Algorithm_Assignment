import java.util.*;

public class Question1 {
    // Created queue for the all input data
    Queue<Integer> list;
    int totalSize;

    Question1(LinkedList[] a) {

        // int size = a.length;
        // Whole is sum of all sub-linkedlist length
        int whole = 0;

        // lopping through all sub-linkedlist

        for (int i = 0; i < a.length; i++) {

            whole += a[i].size();

        }

        totalSize = whole;

        // creating priority-Queue for sorting purpose

        list = new PriorityQueue<Integer>(whole);

        LinkedList current;

        // lopping through linkedlists to add data in
        // prority Queue

        for (int i = 0; i < a.length; i++) {

            current = a[i];

            int internalItterator = 0;

            while (internalItterator < current.size()) {

                // System.out.println(current.get(0));
                int adder = (Integer) current.get(0);
                list.add(adder);
                current.removeFirst();
            }
            internalItterator++;

        }

    }

    // method to return out the whole
    // sorted numbers in the form of array
    int[] queueItterator() {

        int[] returner = new int[totalSize];

        for (int i = 0; i < totalSize; i++) {
            returner[i] = list.remove();
        }

        return returner;

    }

    public static void main(String[] args) {

        LinkedList a = new LinkedList<Integer>();

        a.add(2);
        a.add(4);
        a.add(7);
        a.add(5);
        a.add(10);

        LinkedList b = new LinkedList<Integer>();

        b.add(3);
        b.add(2);
        b.add(7);
        b.add(9);

        LinkedList c = new LinkedList<Integer>();

        c.add(12);
        c.add(5);
        c.add(6);
        c.add(9);

        LinkedList[] list = { a, b, c };

        Question1 medianFinder = new Question1(list);

        int[] requiredArr = medianFinder.queueItterator();

        // median formula:(n+1)/2

        int median = (requiredArr.length + 1) / 2;

        System.out.println(requiredArr.length);

        System.out.println(median);

        System.out.println(requiredArr[median]);

    }

}
