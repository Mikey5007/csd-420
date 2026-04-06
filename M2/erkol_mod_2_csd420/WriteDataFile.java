/*
 * Name: Mirach Erkol
 * Course: CSD420-340A
 * Module: 2
 * Assignment: 2.2
 * Description: This program creates an array of five random integers
 * and an array of five random double values, then appends the data
 * to erkol_datafile.dat.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class WriteDataFile {
    public static void main(String[] args) {
        String fileName = "erkol_datafile.dat";
        Random random = new Random();

        int[] integers = new int[5];
        double[] doubles = new double[5];

        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(100); // 0 to 99
        }

        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = random.nextDouble() * 100; // 0.0 to under 100.0
        }

        try (PrintWriter output = new PrintWriter(new FileWriter(fileName, true))) {
            output.println("New Data Set");

            output.println("Integers:");
            for (int value : integers) {
                output.print(value + " ");
            }
            output.println();

            output.println("Doubles:");
            for (double value : doubles) {
                output.printf("%.2f ", value);
            }
            output.println();
            output.println("--------------------");

            System.out.println("Data written successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}