/*
 * Name: Mirach Erkol
 * Course: CSD420
 * Module: 3
 * Assignment: 3.2
 * Description: This program creates an ArrayList with 50 random integers
 * from 1 to 20, removes duplicates using a generic method, and tests
 * that the method works correctly.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class RemoveDuplicatesTest {

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        for (E item : list) {
            if (!uniqueList.contains(item)) {
                uniqueList.add(item);
            }
        }

        return uniqueList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1); // 1 to 20
        }

        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        System.out.println("Original List:");
        System.out.println(originalList);

        System.out.println("\nList Without Duplicates:");
        System.out.println(uniqueList);

        runTests();
    }

    public static void runTests() {
        ArrayList<Integer> testList = new ArrayList<>(
                Arrays.asList(1, 2, 2, 3, 4, 4, 5, 1, 3)
        );

        ArrayList<Integer> expected = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5)
        );

        ArrayList<Integer> actual = removeDuplicates(testList);

        if (!actual.equals(expected)) {
            throw new AssertionError("Test failed. Expected: " + expected + " but got: " + actual);
        }

        System.out.println("\nTest passed: removeDuplicates works correctly.");
    }
}