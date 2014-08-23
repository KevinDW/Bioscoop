package be.bioscoop.dao;

import be.bioscoop.models.Programmatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgrammatieDAO implements DAOInterface<Programmatie>
{
    private Connection connection;

    public ProgrammatieDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Programmatie> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, datum, beginUur, zaalId, filmId " +
            "FROM programmatie " +
            "ORDER BY datum"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Programmatie> programmaties = new ArrayList<Programmatie>();

        while (resultSet.next())
        {
            programmaties.add(
                new Programmatie(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getTime(3),
                    new ZaalDAO(this.connection).get(resultSet.getInt(4)),
                    new FilmDAO(this.connection).get(resultSet.getInt(5))
                )
            );
        }

        return programmaties;
    }

    public Programmatie get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, datum, beginuur, zaalId, filmId " +
            "FROM programmatie " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Programmatie(
            resultSet.getInt(1),
            resultSet.getDate(2),
            resultSet.getTime(3),
            new ZaalDAO(this.connection).get(resultSet.getInt(4)),
            new FilmDAO(this.connection).get(resultSet.getInt(5))
        );
    }

    public List<Programmatie> whereBioscoopAndDateBetween(String bioscoop, Date beginDatum, Date eindDatum) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT p.id, p.datum, p.beginUur, p.zaalId, p.filmid" +
            "FROM programmatie AS p " +
            "INNER JOIN film AS f ON p.filmId = f.id " +
            "INNER JOIN zaal AS z ON p.zaalId = z.id " +
            "INNER JOIN bioscoop AS b ON z.bioscoopId = b.id " +
            "WHERE b.naam = ? " +
            "AND p.datum BETWEEN ? AND ?"
        );

        statement.setString(1, bioscoop);
        statement.setDate(2, beginDatum);
        statement.setDate(3, eindDatum);

        ResultSet resultSet = statement.executeQuery();
        List<Programmatie> programmaties = new ArrayList<Programmatie>();

        while (resultSet.next())
        {
            programmaties.add(
                new Programmatie(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getTime(3),
                    new ZaalDAO(this.connection).get(resultSet.getInt(4)),
                    new FilmDAO(this.connection).get(resultSet.getInt(5))
                )
            );
        }

        return programmaties;
    }
}
