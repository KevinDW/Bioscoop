package be.bioscoop.dao;

import be.bioscoop.models.*;

import java.sql.*;
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
                "SELECT * FROM klant ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Klant> klanten = new ArrayList<Klant>();

        while (resultSet.next())
        {
            Klant klant = new Klant(resultSet.getInt(1));

            klant.setNaam(resultSet.getString(2));
            klant.setEmail(resultSet.getString(3));

            klanten.add(klant);
        }

        return klanten;
    }
}
