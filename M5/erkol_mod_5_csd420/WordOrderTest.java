/*
 * Name: Mirach Erkol
 * Course: CSD420
 * Module: 5
 * Assignment: 5.2
 * Description: This program reads words from collection_of_words.txt,
 * displays all non-duplicate words in ascending order and then in
 * descending order, and includes test code to verify correctness.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class WordOrderTest {

    public static void main(String[] args) {
        String fileName = "collection_of_words.txt";

        try {
            TreeSet<String> words = readUniqueWords(fileName);

            System.out.println("Ascending Order:");
            for (String word : words) {
                System.out.println(word);
            }

            System.out.println("\nDescending Order:");
            for (String word : words.descendingSet()) {
                System.out.println(word);
            }

            runTests();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Error during test execution: " + e.getMessage());
        }
    }

    public static TreeSet<String> readUniqueWords(String fileName) throws FileNotFoundException {
        TreeSet<String> uniqueWords = new TreeSet<>();
        File file = new File(fileName);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String word = input.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }

        input.close();
        return uniqueWords;
    }

    public static void runTests() throws IOException {
        String testFileName = "test_words_temp.txt";

        try {
            PrintWriter writer = new PrintWriter(testFileName);
            writer.println("banana apple orange apple kiwi orange");
            writer.close();

            TreeSet<String> result = readUniqueWords(testFileName);

            TreeSet<String> expected = new TreeSet<>();
            expected.add("apple");
            expected.add("banana");
            expected.add("kiwi");
            expected.add("orange");

            if (!result.equals(expected)) {
                throw new AssertionError("Test failed. Expected " + expected + " but got " + result);
            }

            System.out.println("\nTest passed: program correctly reads and sorts non-duplicate words.");
        } finally {
            File tempFile = new File(testFileName);
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}