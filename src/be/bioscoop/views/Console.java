package be.bioscoop.views;

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
            LocalDate eindDatum = LocalDate.of(2014, 8, 31);

            vertoningenInBioscoopTussenTweeDatums(bioscoop, Date.valueOf(startDatum), Date.valueOf(eindDatum));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error code: " + e.getErrorCode());
        }
    }

    private static void vertoningenInBioscoopTussenTweeDatums(String bioscoop, Date startDatum, Date eindDatum) throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.20.200:3306/bioscoop", "root", "root");

        PreparedStatement stmt = con.prepareStatement(
            "SELECT zaal.zaalNr, film.naam " +
            "FROM programmatie " +
            "INNER JOIN film ON programmatie.filmId = film.id " +
            "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
            "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
            "WHERE bioscoop.naam = ? AND programmatie.datum BETWEEN ? AND ?"
        );

        stmt.setString(1, bioscoop);
        stmt.setDate(2, startDatum);
        stmt.setDate(3, eindDatum);

        ResultSet rs = stmt.executeQuery();

        System.out.printf("Bioscoop: %s\n\n", bioscoop);

        while(rs.next())
        {
            System.out.printf("%d %s\n", rs.getInt("zaal.zaalNr"), rs.getString("film.naam"));
        }

        rs.close();
        stmt.close();
        con.close();
    }
}
