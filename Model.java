package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

    private String url = "jdbc:mysql://localhost:3306/college1"; // Fixed URL
    private String userName = "root";
    private String password = "chandi@1606";

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;

    private String usn = null;
    private String name = null;
    private int marks1 = 0;
    private int marks2 = 0;
    private int marks3 = 0;
    private float percentage = 0.0f;

    public Model() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            if (con == null) {
                System.out.println("Database connection failed!");
            } else {
                System.out.println("Database connected successfully.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks1() {
        return marks1;
    }

    public void setMarks1(int marks1) {
        this.marks1 = marks1;
    }

    public int getMarks2() {
        return marks2;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
    }

    public int getMarks3() {
        return marks3;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    // Executing query
    public void getResult() {
        try {
            if (con == null) {
                System.out.println("Cannot execute query: Database connection is null.");
                return;
            }

            pstmt = con.prepareStatement("SELECT * FROM student1 WHERE USN=?");
            pstmt.setString(1, usn);
            res = pstmt.executeQuery();

            if (res.next()) {
                usn = res.getString(1);
                name = res.getString(2);
                marks1 = res.getInt(3);
                marks2 = res.getInt(4);
                marks3 = res.getInt(5);
                percentage = res.getFloat(6);
            } else {
                System.out.println("No record found for USN: " + usn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (res != null) res.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
