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
            "FROM restrictie " +
            "ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Genre> restricties = new ArrayList<Genre>();

        while (resultSet.next())
        {
            restricties.add(
                new Genre(
                    resultSet.getInt(1),
                    resultSet.getString(2)
                )
            );
        }

        return restricties;
    }

    public Genre find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM restrictie " +
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
}
