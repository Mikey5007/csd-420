package com.mycompany.fandatabaseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Fan getFanById(int id) {
        String sql = "SELECT * FROM fans WHERE ID = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Fan(
                        rs.getInt("ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("favoriteteam")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateFan(Fan fan) {
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fan.getFirstName());
            ps.setString(2, fan.getLastName());
            ps.setString(3, fan.getFavoriteTeam());
            ps.setInt(4, fan.getId());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}