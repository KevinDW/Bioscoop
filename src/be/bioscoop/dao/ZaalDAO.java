package be.bioscoop.dao;

import be.bioscoop.entities.Zaal;

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
                    new BioscoopDAO(this.connection).find(resultSet.getInt(2))
                )
            );
        }

        return zalen;
    }

    public Zaal find(int id) throws SQLException
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
            new BioscoopDAO(this.connection).find(resultSet.getInt(7))
        );
    }

    public Zaal first() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, zaalnr, capaciteit, maxRij, maxKolom, verdieping, bioscoopId " +
            "FROM zaal " +
            "ORDER BY id ASC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Zaal(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getInt(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getInt(6),
            new BioscoopDAO(this.connection).find(resultSet.getInt(7))
        );
    }

    public Zaal last() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, zaalnr, capaciteit, maxRij, maxKolom, verdieping, bioscoopId " +
            "FROM zaal " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Zaal(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getInt(3),
            resultSet.getInt(4),
            resultSet.getInt(5),
            resultSet.getInt(6),
            new BioscoopDAO(this.connection).find(resultSet.getInt(7))
        );
    }

    public int lastId() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id " +
            "FROM zaal " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return resultSet.getInt(1);
    }

    public boolean insert(Zaal zaal) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO zaal (zaalNr, capaciteit, maxRij, maxKolom, verdieping, bioscoopId) " +
            "VALUES (?, ?, ?, ?, ?, ?)"
        );

        statement.setInt(1, zaal.getZaalNr());
        statement.setInt(2, zaal.getCapaciteit());
        statement.setInt(3, zaal.getMaxRij());
        statement.setInt(4, zaal.getMaxKolom());
        statement.setInt(5, zaal.getVerdieping());
        statement.setInt(6, zaal.getBioscoop().getId());

        return statement.execute();
    }

    public boolean update(Zaal zaal) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE zaal " +
            "SET zaalNr = ?, capaciteit = ?, maxRij = ?, maxKolom = ?, verdieping = ?, bioscoopId = ? " +
            "WHERE id = ?"
        );

        statement.setInt(1, zaal.getZaalNr());
        statement.setInt(2, zaal.getCapaciteit());
        statement.setInt(3, zaal.getMaxRij());
        statement.setInt(4, zaal.getMaxKolom());
        statement.setInt(5, zaal.getVerdieping());
        statement.setInt(6, zaal.getBioscoop().getId());
        statement.setInt(7, zaal.getId());

        return statement.execute();
    }

    public boolean delete(Zaal zaal) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM zaal " +
            "WHERE id = ?"
        );

        statement.setInt(1, zaal.getId());

        return statement.execute();
    }
}
