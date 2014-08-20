package be.bioscoop.dao;

import be.bioscoop.models.Barcode;
import be.bioscoop.models.Film;
import be.bioscoop.models.Programmatie;
import be.bioscoop.models.Zaal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarcodeDAO
{
    private Connection connection;

    public BarcodeDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Barcode> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM barcode ORDER BY code"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Barcode> barcodes = new ArrayList<Barcode>();

        while (resultSet.next())
        {
            Barcode barcode = new Barcode(resultSet.getInt(1));

            barcode.setCode(resultSet.getString(2));
            barcode.setGebruikt(resultSet.getBoolean(3));

            barcodes.add(barcode);
        }

        return barcodes;
    }
}
