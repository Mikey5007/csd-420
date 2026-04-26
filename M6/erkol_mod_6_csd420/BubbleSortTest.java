/*
 *
 * Mirach Erkol
 * CSD420
 * Module 6
 * Assignment 6.2
 *
 * This program uses two generic bubble sort methods.
 * The first method sorts using Comparable.
 * The second method sorts using Comparator.
 * Test code is included to verify correctness.
 *
 */
import java.util.Comparator;

public class BubbleSortTest {

    public static void main(String[] args) {

        Integer[] testValues = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        String[] planets = {"Mars", "Venus", "Earth", "Mercury", "Jupiter"};
        Integer[] reverseValues = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};

        System.out.println("Comparable sort with integers");
        printArray(testValues);
        bubbleSort(testValues);
        printArray(testValues);

        System.out.println("Comparable sort with planets");
        printArray(planets);
        bubbleSort(planets);
        printArray(planets);

        System.out.println("Comparator sort with integers in reverse order");
        printArray(reverseValues);
        bubbleSort(reverseValues, Comparator.reverseOrder());
        printArray(reverseValues);

        runTests();
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        bubbleSortComparable(list, true);
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        bubbleSortComparator(list, comparator, true);
    }

    private static <E extends Comparable<E>> void bubbleSortComparable(E[] list, boolean showSteps) {
        E temp;
        boolean swapped;

        for (int i = 0; i < list.length - 1; ++i) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            if (showSteps) {
                printArray(list);
            }

            if (!swapped) {
                break;
            }
        }
    }

    private static <E> void bubbleSortComparator(E[] list, Comparator<? super E> comparator, boolean showSteps) {
        E temp;
        boolean swapped;

        for (int i = 0; i < list.length - 1; ++i) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; ++j) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }

            if (showSteps) {
                printArray(list);
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static <E> void printArray(E[] arrayParam) {
        System.out.print("\narray = {");

        for (E e : arrayParam) {
            System.out.print(" [" + e + "] ");
        }

        System.out.println("};\n");
    }

    public static void runTests() {
        Integer[] test1 = {4, 2, 7, 1, 3};
        bubbleSortComparable(test1, false);

        Integer[] expected1 = {1, 2, 3, 4, 7};
        for (int i = 0; i < test1.length; i++) {
            if (!test1[i].equals(expected1[i])) {
                throw new AssertionError("Comparable integer test failed.");
            }
        }

        String[] test2 = {"Saturn", "Earth", "Mars", "Jupiter"};
        bubbleSortComparable(test2, false);

        String[] expected2 = {"Earth", "Jupiter", "Mars", "Saturn"};
        for (int i = 0; i < test2.length; i++) {
            if (!test2[i].equals(expected2[i])) {
                throw new AssertionError("Comparable planet test failed.");
            }
        }

        Integer[] test3 = {4, 2, 7, 1, 3};
        bubbleSortComparator(test3, Comparator.reverseOrder(), false);

        Integer[] expected3 = {7, 4, 3, 2, 1};
        for (int i = 0; i < test3.length; i++) {
            if (!test3[i].equals(expected3[i])) {
                throw new AssertionError("Comparator reverse test failed.");
            }
        }

        System.out.println("Test passed: all bubble sort methods work correctly.");
    }
}