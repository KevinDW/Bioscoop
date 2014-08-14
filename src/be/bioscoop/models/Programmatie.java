package be.bioscoop.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Programmatie extends Model
{
    public ResultSet inBepaaldeBioscoopTussenTweeDatums(String bioscoop, Date startDatum, Date eindDatum) throws SQLException
    {
        PreparedStatement statement = super.connection.prepareStatement(
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
