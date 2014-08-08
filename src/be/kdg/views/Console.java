package be.kdg.views;

import java.sql.*;
import java.time.LocalDate;

public class Console
{
    public static void main(String[] args)
    {
        try
        {
            String bioscoop = "Kinepolis Antwerpen";
            LocalDate startDatum = LocalDate.of(2014, 8, 1);
            LocalDate eindDatum = LocalDate.now();

            ResultSet rs = vertoningenVoorBioscoop(bioscoop, startDatum, eindDatum);

            while(rs.next())
            {
                System.out.println(rs.getString("film.naam"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static ResultSet vertoningenVoorBioscoop(String bioscoop, LocalDate startDatum, LocalDate eindDatum) throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");
        Statement stmt = con.createStatement();

        return stmt.executeQuery("SELECT film.naam FROM programmatie " +
            "INNER JOIN film ON programmatie.filmId = film.id " +
            "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
            "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
            "WHERE bioscoop.naam = '" + bioscoop + "' " +
            "AND programmatie.datum BETWEEN '" + startDatum + "' AND '" + eindDatum + "'"
        );
    }
}
