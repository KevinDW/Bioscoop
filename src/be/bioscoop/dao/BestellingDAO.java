package be.bioscoop.dao;

import be.bioscoop.models.Bestelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            "SELECT id, klantId, ticketId FROM bestelling ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Bestelling> bestellingen = new ArrayList<Bestelling>();

        while (resultSet.next())
        {
            bestellingen.add(
                new Bestelling(
                    resultSet.getInt(1),
                    new KlantDAO(this.connection).get(resultSet.getInt(2)),
                    new TicketDAO(this.connection).get(resultSet.getInt(3))
                )
            );
        }

        return bestellingen;
    }

    public Bestelling get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, klantId, ticketId FROM film WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Bestelling(
            resultSet.getInt(1),
            new KlantDAO(this.connection).get(resultSet.getInt(2)),
            new TicketDAO(this.connection).get(resultSet.getInt(3))
        );
    }
}
