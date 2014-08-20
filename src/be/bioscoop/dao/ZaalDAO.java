package be.bioscoop.dao;

import be.bioscoop.models.Bioscoop;
import be.bioscoop.models.Zaal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZaalDAO
{
    private Connection connection;

    public ZaalDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Zaal> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM zaal ORDER BY zaalNr");
        ResultSet resultSet = statement.executeQuery();
        List<Zaal> zalen = new ArrayList<Zaal>();

        while (resultSet.next())
        {
            Zaal zaal = new Zaal(resultSet.getInt(1));

            zaal.setZaalNr(resultSet.getInt(2));
            zaal.setCapaciteit(resultSet.getInt(3));
            zaal.setMaxRij(resultSet.getString(4));
            zaal.setMaxKolom(resultSet.getInt(5));
            zaal.setVerdieping(resultSet.getInt(6));
            zaal.setBioscoop(new Bioscoop(resultSet.getInt(7)));

            zalen.add(zaal);
        }

        return zalen;
    }
}
