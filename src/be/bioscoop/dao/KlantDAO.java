package be.bioscoop.dao;

import be.bioscoop.models.Klant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlantDAO
{
    private Connection connection;

    public KlantDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Klant> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, email FROM klant ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Klant> klanten = new ArrayList<Klant>();

        while (resultSet.next())
        {
            klanten.add(
                new Klant(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
                )
            );
        }

        return klanten;
    }

    public Klant get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, email FROM film WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Klant(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3)
        );
    }
}
