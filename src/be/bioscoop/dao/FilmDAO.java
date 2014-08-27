package be.bioscoop.dao;

import be.bioscoop.entities.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAOInterface<Film>
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
                    new RestrictieDAO(this.connection).find(resultSet.getInt(7)),
                    new GenreDAO(this.connection).find(resultSet.getInt(8))
                )
            );
        }

        return films;
    }

    public Film find(int id) throws SQLException
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
            new RestrictieDAO(this.connection).find(resultSet.getInt(7)),
            new GenreDAO(this.connection).find(resultSet.getInt(8))
        );
    }

    public Film first() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, code, jaar, duur, beoordeling, genreId, restrictieId " +
            "FROM film " +
            "ORDER BY id ASC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Film(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getDouble(6),
            new RestrictieDAO(this.connection).find(resultSet.getInt(7)),
            new GenreDAO(this.connection).find(resultSet.getInt(8))
        );
    }

    public Film last() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, code, jaar, duur, beoordeling, genreId, restrictieId " +
            "FROM film " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Film(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getDouble(6),
            new RestrictieDAO(this.connection).find(resultSet.getInt(7)),
            new GenreDAO(this.connection).find(resultSet.getInt(8))
        );
    }

    public int lastId() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id " +
            "FROM film " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return resultSet.getInt(1);
    }

    public boolean insert(Film film) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO film (naam, code, jaar, duur, beoordeling, restrictieId, genreId) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
        );

        statement.setString(1, film.getNaam());
        statement.setString(2, film.getCode());
        statement.setInt(3, film.getJaar());
        statement.setInt(4, film.getDuur());
        statement.setDouble(5, film.getBeoordeling());
        statement.setInt(6, film.getRestrictie().getId());
        statement.setInt(7, film.getGenre().getId());

        return statement.execute();
    }

    public boolean update(Film film) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE film " +
            "SET naam = ?, code = ?, jaar = ?, duur = ?, beoordeling = ?, restrictieId = ?, genreId = ? " +
            "WHERE id = ?"
        );

        statement.setString(1, film.getNaam());
        statement.setString(2, film.getCode());
        statement.setInt(3, film.getJaar());
        statement.setInt(4, film.getDuur());
        statement.setDouble(5, film.getBeoordeling());
        statement.setInt(6, film.getRestrictie().getId());
        statement.setInt(7, film.getGenre().getId());
        statement.setInt(8, film.getId());

        return statement.execute();
    }

    public boolean delete(Film film) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM film " +
            "WHERE id = ?"
        );

        statement.setInt(1, film.getId());

        return statement.execute();
    }
}
