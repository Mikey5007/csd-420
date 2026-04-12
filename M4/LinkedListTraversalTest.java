/*
 * Name: Mirach Erkol
 * Course: CSD420
 * Module: 4
 * Assignment: 4.2
 * Description: This program stores integers in a LinkedList and compares
 * traversal time using an iterator and get(index) for 50,000 and 500,000 items.
 *
 * Results Discussion:
 * For 50,000 integers, iterator traversal took about 2598300 ns, while
 * get(index) traversal took about 642568900 ns.
 * For 500,000 integers, iterator traversal took about 2953900 ns, while
 * get(index) traversal took about 69575045800 ns.
 * Iterator traversal was much faster in both tests. The difference became
 * much larger with 500,000 items because LinkedList is efficient for
 * sequential access, but get(index) must move through the list to reach
 * each position.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTraversalTest {

    public static void main(String[] args) {
        runTest(50000);
        System.out.println();

        runTest(500000);
        System.out.println();

        runCorrectnessTests();
    }

    public static void runTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long startIterator = System.nanoTime();
        long iteratorSum = traverseWithIterator(list);
        long endIterator = System.nanoTime();

        long startGet = System.nanoTime();
        long getSum = traverseWithGet(list);
        long endGet = System.nanoTime();

        long iteratorTime = endIterator - startIterator;
        long getTime = endGet - startGet;

        System.out.println("Testing with " + size + " integers");
        System.out.println("Iterator traversal sum: " + iteratorSum);
        System.out.println("Iterator traversal time: " + iteratorTime + " ns");
        System.out.println("get(index) traversal sum: " + getSum);
        System.out.println("get(index) traversal time: " + getTime + " ns");
    }

    public static long traverseWithIterator(LinkedList<Integer> list) {
        long sum = 0;
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    public static long traverseWithGet(LinkedList<Integer> list) {
        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }

    public static void runCorrectnessTests() {
        LinkedList<Integer> testList = new LinkedList<>();
        testList.add(10);
        testList.add(20);
        testList.add(30);
        testList.add(40);

        long iteratorResult = traverseWithIterator(testList);
        long getResult = traverseWithGet(testList);

        if (iteratorResult != 100) {
            throw new AssertionError("Iterator test failed. Expected 100 but got " + iteratorResult);
        }

        if (getResult != 100) {
            throw new AssertionError("get(index) test failed. Expected 100 but got " + getResult);
        }

        if (iteratorResult != getResult) {
            throw new AssertionError("Traversal methods do not match.");
        }

        System.out.println("Test passed: both traversal methods return the correct result.");
    }
}