package be.kdg.views;

import java.sql.*;
import java.time.LocalDate;

public class Console
{
    public static void main(String[] args)
    {
        voegSociaalBerichtToeAanBepaaldeFilm();

        String bioscoop = "Kinepolis Brussel";
        Date startDatum = Date.valueOf(LocalDate.of(2014, 8, 1));
        Date eindDatum = Date.valueOf(LocalDate.of(2014, 8, 31));

        ResultSet rs = vertoningenVoorBioscoopTussenTweeDatums(bioscoop, startDatum, eindDatum);

        System.out.println("Bioscoop\t\t\tZaal\tFilm");
        System.out.println("==================================");

        try
        {
            while(rs.next())
            {
                System.out.printf("%s\t%d\t\t%s\n",
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

    private static ResultSet vertoningenVoorBioscoopTussenTweeDatums(String bioscoop, Date startDatum, Date eindDatum)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioscoop", "root", "Iam4life");

            PreparedStatement stmt = con.prepareStatement(
                "SELECT bioscoop.naam, zaal.zaalNr, film.naam " +
                "FROM programmatie " +
                "INNER JOIN film ON programmatie.filmId = film.id " +
                "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
                "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
                "WHERE bioscoop.naam = ? " +
                "AND programmatie.datum BETWEEN ? AND ?"
            );

            stmt.setString(1, bioscoop);
            stmt.setDate(2, startDatum);
            stmt.setDate(3, eindDatum);

            return stmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
