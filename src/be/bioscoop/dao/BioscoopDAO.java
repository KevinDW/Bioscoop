package be.bioscoop.dao;

import be.bioscoop.models.Bioscoop;
import be.bioscoop.models.Film;
import be.bioscoop.models.Programmatie;
import be.bioscoop.models.Zaal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BioscoopDAO
{
    private Connection connection;

    public BioscoopDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Bioscoop> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM bioscoop ORDER BY postcode"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Bioscoop> bioscopen = new ArrayList<Bioscoop>();

        while (resultSet.next())
        {
            Bioscoop bioscoop = new Bioscoop(resultSet.getInt(1));

            bioscoop.setNaam(resultSet.getString(2));
            bioscoop.setPostcode(resultSet.getString(3));
            bioscoop.setGemeente(resultSet.getString(4));
            bioscoop.setStraat(resultSet.getString(5));

            bioscopen.add(bioscoop);
        }

        return bioscopen;
    }
}
