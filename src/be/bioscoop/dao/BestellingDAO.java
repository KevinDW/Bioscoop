package be.bioscoop.dao;

import be.bioscoop.entities.Bestelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BestellingDAO implements DAOInterface<Bestelling>
{
    private Connection connection;

    public BestellingDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Bestelling> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, klantId, ticketId " +
            "FROM bestelling " +
            "ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Bestelling> bestellingen = new ArrayList<Bestelling>();

        while (resultSet.next())
        {
            bestellingen.add(
                new Bestelling(
                    resultSet.getInt(1),
                    new KlantDAO(this.connection).find(resultSet.getInt(2)),
                    new TicketDAO(this.connection).find(resultSet.getInt(3))
                )
            );
        }

        return bestellingen;
    }

    public Bestelling find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, klantId, ticketId " +
            "FROM bestelling " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Bestelling(
            resultSet.getInt(1),
            new KlantDAO(this.connection).find(resultSet.getInt(2)),
            new TicketDAO(this.connection).find(resultSet.getInt(3))
        );
    }

    public boolean insert(Bestelling bestelling) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO bestelling (klantId, ticketId) " +
            "VALUES (?, ?)"
        );

        statement.setInt(1, bestelling.getKlant().getId());
        statement.setInt(2, bestelling.getTicket().getId());

        return statement.execute();
    }

    public boolean update(Bestelling bestelling) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE bestelling " +
            "SET klantId = ?, ticketId = ? " +
            "WHERE id = ?"
        );

        statement.setInt(1, bestelling.getKlant().getId());
        statement.setInt(2, bestelling.getTicket().getId());
        statement.setInt(3, bestelling.getId());

        return statement.execute();
    }

    public boolean delete(Bestelling bestelling) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM bestelling " +
            "WHERE id = ?"
        );

        statement.setInt(1, bestelling.getId());

        return statement.execute();
    }
}
