package be.bioscoop.dao;

import be.bioscoop.models.Restrictie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestrictieDAO
{
    private Connection connection;

    public RestrictieDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Restrictie> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam FROM restrictie ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Restrictie> restricties = new ArrayList<Restrictie>();

        while (resultSet.next())
        {
            restricties.add(
                new Restrictie(
                    resultSet.getInt(1), resultSet.getString(2)
                )
            );
        }

        return restricties;
    }

    public Restrictie get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam FROM film WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Restrictie(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }
}
