package be.kdg.views;

import java.sql.*;
import java.time.LocalDate;

public class Console
{
    public static void main(String[] args)
    {
        voegSociaalBerichtToeAanBepaaldeFilm();
        toonVertoningenVoorBioscoop();
    }

    private static void voegSociaalBerichtToeAanBepaaldeFilm()
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");
            Statement stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void toonVertoningenVoorBioscoop()
    {
        String bioscoop = "Kinepolis Antwerpen";
        LocalDate startDatum = LocalDate.of(2014, 8, 1);
        LocalDate eindDatum = LocalDate.now();

        ResultSet rs = vertoningenVoorBioscoop(bioscoop, startDatum, eindDatum);

        System.out.println("Bioscoop\t\t\tZaal\tFilm");
        System.out.println("==================================");

        try
        {
            while(rs.next())
            {
                System.out.printf("%s\t%d\t%s\n",
                    rs.getString("bioscoop.naam"),
                    rs.getInt("zaal.zaalNr"),
                    rs.getString("film.naam")
                );
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static ResultSet vertoningenVoorBioscoop(String bioscoop, LocalDate startDatum, LocalDate eindDatum)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");
            Statement stmt = con.createStatement();

            return stmt.executeQuery
            (
                "SELECT bioscoop.naam, zaal.zaalNr, film.naam " +
                "FROM programmatie " +
                "INNER JOIN film ON programmatie.filmId = film.id " +
                "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
                "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
                "WHERE bioscoop.naam = '" + bioscoop + "' " +
                "AND programmatie.datum BETWEEN '" + startDatum + "' AND '" + eindDatum + "'"
            );
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
