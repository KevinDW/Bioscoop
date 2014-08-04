package be.kdg.views;

import java.sql.*;

public class Console
{
    public static void main(String[] args)
    {
        Connection con;

        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM barcode");

            while(rs.next())
            {
                System.out.println(rs.getString("code"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
