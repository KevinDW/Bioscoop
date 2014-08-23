package be.bioscoop.dao;

import be.bioscoop.models.Zaal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZaalDAO implements DAOInterface<Zaal>
{
    private Connection connection;

    public ZaalDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Zaal> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, zaalNr, capaciteit, maxRij, maxKolom, verdieping, bioscoopId " +
            "FROM zaal " +
            "ORDER BY zaalNr"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Zaal> zalen = new ArrayList<Zaal>();

        while (resultSet.next())
        {
            zalen.add(
                new Zaal(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    new BioscoopDAO(this.connection).get(resultSet.getInt(2))
                )
            );
        }

        return zalen;
    }

    public Zaal get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, zaalnr, capaciteit, maxRij, maxKolom, verdieping, bioscoopId " +
            "FROM zaal " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Zaal(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getInt(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getInt(6),
            new BioscoopDAO(this.connection).get(resultSet.getInt(2))
        );
    }
}
