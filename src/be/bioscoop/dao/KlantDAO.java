package be.bioscoop.dao;

import be.bioscoop.models.Klant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlantDAO implements DAOInterface<Klant>
{
    private Connection connection;

    public KlantDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Klant> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, email " +
            "FROM klant " +
            "ORDER BY id"
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
            "SELECT id, naam, email " +
            "FROM klant " +
            "WHERE id = ?"
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

    public boolean insert(Klant klant) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO klant (naam, email) " +
            "VALUES (?, ?)"
        );

        statement.setString(1, klant.getNaam());
        statement.setString(2, klant.getEmail());

        return statement.execute();
    }
}
