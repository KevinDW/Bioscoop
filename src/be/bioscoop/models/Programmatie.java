package be.bioscoop.models;

import be.bioscoop.config.Database;

import java.sql.*;

public class Programmatie
{
    public ResultSet inBepaaldeBioscoopInBepaaldePeriode(String bioscoop, Date startDatum, Date eindDatum) throws SQLException
    {
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

        return statement.executeQuery();
    }
}
