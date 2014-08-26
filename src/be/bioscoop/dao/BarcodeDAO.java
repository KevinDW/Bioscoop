package be.bioscoop.dao;

import be.bioscoop.entities.Barcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarcodeDAO implements DAOInterface<Barcode>
{
    private Connection connection;

    public BarcodeDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Barcode> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, code, gebruikt " +
            "FROM barcode " +
            "ORDER BY code"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Barcode> barcodes = new ArrayList<Barcode>();

        while (resultSet.next())
        {
            barcodes.add(
                new Barcode(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getBoolean(3)
                )
            );
        }

        return barcodes;
    }

    public Barcode find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, code, gebruikt " +
            "FROM barcode " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Barcode(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getBoolean(3)
        );
    }

    public boolean insert(Barcode barcode) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO barcode (code, gebruikt) " +
            "VALUES (?, ?)"
        );

        statement.setString(1, barcode.getCode());
        statement.setBoolean(2, barcode.isGebruikt());

        return statement.execute();
    }

    public boolean update(Barcode barcode) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE barcode " +
            "SET code = ?, gebruikt = ? " +
            "WHERE id = ?"
        );

        statement.setString(1, barcode.getCode());
        statement.setBoolean(2, barcode.isGebruikt());
        statement.setInt(3, barcode.getId());

        return statement.execute();
    }

    public boolean delete(Barcode barcode) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM barcode " +
            "WHERE id = ?"
        );

        statement.setInt(1, barcode.getId());

        return statement.execute();
    }
}
