package be.bioscoop.views;

import be.bioscoop.config.Database;

import java.sql.*;
import java.time.LocalDate;

public class Console
{
    public static void main(String[] args)
    {
        try
        {
            String bioscoop = "Kinepolis Antwerpen";
            Date startDatum = Date.valueOf(LocalDate.of(2014, 8, 1));
            Date eindDatum = Date.valueOf(LocalDate.of(2014, 8, 31));

            Connection connection = Database.connect();

            PreparedStatement statement = connection.prepareStatement(
                "SELECT zaal.zaalNr, film.naam " +
                "FROM programmatie " +
                "INNER JOIN film ON programmatie.filmId = film.id " +
                "INNER JOIN zaal ON programmatie.zaalId = zaal.id " +
                "INNER JOIN bioscoop ON zaal.bioscoopId = bioscoop.id " +
                "WHERE bioscoop.naam = ? AND programmatie.datum BETWEEN ? AND ?"
            );

            statement.setString(1, bioscoop);
            statement.setDate(2, startDatum);
            statement.setDate(3, eindDatum);

            ResultSet resultSet = statement.executeQuery();

            System.out.printf("Bioscoop: %s\n\n", bioscoop);

            while(resultSet.next())
            {
                System.out.printf("%d | ", resultSet.getInt("zaal.zaalNr"));
                System.out.printf("%s", resultSet.getString("film.naam"));
                System.out.println();
            }

            Database.close(statement, resultSet);
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }
}
