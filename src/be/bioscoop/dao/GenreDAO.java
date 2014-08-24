package be.bioscoop.dao;

import be.bioscoop.entities.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DAOInterface<Genre>
{
    private Connection connection;

    public GenreDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Genre> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM genre " +
            "ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Genre> genres = new ArrayList<Genre>();

        while (resultSet.next())
        {
            genres.add(
                new Genre(
                    resultSet.getInt(1),
                    resultSet.getString(2)
                )
            );
        }

        return genres;
    }

    public Genre find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM genre " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Genre(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }

    public boolean insert(Genre genre) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO genre (naam) " +
            "VALUES (?)"
        );

        statement.setString(1, genre.getNaam());

        return statement.execute();
    }

    public boolean update(Genre genre) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE genre " +
            "SET naam = ? " +
            "WHERE id = ?"
        );

        statement.setString(1, genre.getNaam());
        statement.setInt(2, genre.getId());

        return statement.execute();
    }

    public boolean delete(Genre genre) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM genre " +
            "WHERE id = ?"
        );

        statement.setInt(1, genre.getId());

        return statement.execute();
    }
}
