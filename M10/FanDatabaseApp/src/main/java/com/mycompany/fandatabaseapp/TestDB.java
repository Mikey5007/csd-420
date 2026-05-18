package com.mycompany.fandatabaseapp;

public class TestDB {
    public static void main(String[] args) {
        DBHelper db = new DBHelper();

        Fan fan = db.getFanById(1);
        if (fan != null) {
            System.out.println("Display test passed:");
            System.out.println(fan.getId() + " " + fan.getFirstName() + " " + fan.getLastName() + " " + fan.getFavoriteTeam());
        } else {
            System.out.println("Display test failed.");
        }

        Fan updatedFan = new Fan(1, "Tom", "Brady", "Buccaneers");
        boolean result = db.updateFan(updatedFan);

        if (result) {
            System.out.println("Update test passed.");
        } else {
            System.out.println("Update test failed.");
        }

        Fan verifyFan = db.getFanById(1);
        if (verifyFan != null) {
            System.out.println("Verify update:");
            System.out.println(verifyFan.getId() + " " + verifyFan.getFirstName() + " " + verifyFan.getLastName() + " " + verifyFan.getFavoriteTeam());
        }
    }
}