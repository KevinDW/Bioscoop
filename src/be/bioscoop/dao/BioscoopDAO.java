package be.bioscoop.dao;

import be.bioscoop.entities.Bioscoop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BioscoopDAO implements DAOInterface<Bioscoop>
{
    private Connection connection;

    public BioscoopDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Bioscoop> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, straat, postcode, gemeente " +
            "FROM bioscoop " +
            "ORDER BY postcode"
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

    public Bioscoop find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, straat, postcode, gemeente " +
            "FROM bioscoop " +
            "WHERE id = ?"
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

    public boolean insert(Bioscoop bioscoop) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO bioscoop (naam, straat, postcode, gemeente) " +
            "VALUES (?, ?, ?, ?)"
        );

        statement.setString(1, bioscoop.getNaam());
        statement.setString(2, bioscoop.getStraat());
        statement.setString(3, bioscoop.getPostcode());
        statement.setString(4, bioscoop.getGemeente());

        return statement.execute();
    }
}
