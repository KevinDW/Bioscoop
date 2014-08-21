package be.bioscoop.dao;

import be.bioscoop.models.Bioscoop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            "SELECT id, naam, straat, postcode, gemeente FROM bioscoop ORDER BY postcode"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Bioscoop> bioscopen = new ArrayList<Bioscoop>();

        while (resultSet.next())
        {
            bioscopen.add(
                new Bioscoop(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
                )
            );
        }

        return bioscopen;
    }

    public Bioscoop get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, straat, postcode, gemeente FROM bioscoop WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Bioscoop(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5)
        );
    }
}
