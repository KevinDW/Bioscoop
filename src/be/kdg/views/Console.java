package be.kdg.views;

import java.sql.*;

// TEST KLASSE

public class Console
{
    public static void main(String[] args)
    {
        Connection con;

        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
