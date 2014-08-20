package be.bioscoop.dao;

import be.bioscoop.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BestellingDAO
{
    private Connection connection;

    public BestellingDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Bestelling> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM bestelling ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Bestelling> bestellingen = new ArrayList<Bestelling>();

        while (resultSet.next())
        {
            Bestelling bestelling = new Bestelling(resultSet.getInt(1));

            bestelling.setKlant(new Klant(resultSet.getInt(2)));
            bestelling.setTicket(new Ticket(resultSet.getInt(3)));

            bestellingen.add(bestelling);
        }

        return bestellingen;
    }
}
