package be.bioscoop.dao;

import be.bioscoop.models.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAOInterface<Film>g
{
    private Connection connection;

    public FilmDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Film> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, code, jaar, duur, beoordeling, restrictieId, genreId " +
            "FROM film " +
            "ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Film> films = new ArrayList<Film>();

        while (resultSet.next())
        {
            films.add(
                new Film(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    new RestrictieDAO(this.connection).get(resultSet.getInt(7)),
                    new GenreDAO(this.connection).get(resultSet.getInt(8))
                )
            );
        }

        return films;
    }

    public Film get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, code, jaar, duur, beoordeling, genreId, restrictieId " +
            "FROM film " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Film(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getDouble(6),
            new RestrictieDAO(this.connection).get(resultSet.getInt(7)),
            new GenreDAO(this.connection).get(resultSet.getInt(8))
        );
    }
}
