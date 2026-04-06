/*
 * Name: Mirach Erkol
 * Course: CSD420-340A
 * Module: 2
 * Assignment: 2.2
 * Description: This program reads the contents of erkol_datafile.dat
 * and displays the stored values.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadDataFile {
    public static void main(String[] args) {
        String fileName = "erkol_datafile.dat";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("The file does not exist yet.");
            return;
        }

        try (Scanner input = new Scanner(file)) {
            System.out.println("Contents of " + fileName + ":");
            System.out.println();

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}