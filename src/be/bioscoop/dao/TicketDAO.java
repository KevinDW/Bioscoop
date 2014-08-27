package be.bioscoop.dao;

import be.bioscoop.entities.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAOInterface<Ticket>
{
    private Connection connection;

    public TicketDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Ticket> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, prijs, klantId, programmatieId " +
            "FROM ticket " +
            "ORDER BY id"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Ticket> tickets = new ArrayList<Ticket>();

        while (resultSet.next())
        {
            tickets.add(
                new Ticket(
                    resultSet.getInt(1),
                    resultSet.getDouble(2),
                    new KlantDAO(this.connection).find(resultSet.getInt(3)),
                    new ProgrammatieDAO(this.connection).find(resultSet.getInt(4))
                )
            );
        }

        return tickets;
    }

    public Ticket find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, prijs, klantId, programmatieId " +
            "FROM ticket " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Ticket(
            resultSet.getInt(1),
            resultSet.getDouble(2),
            new KlantDAO(this.connection).find(resultSet.getInt(3)),
            new ProgrammatieDAO(this.connection).find(resultSet.getInt(4))
        );
    }

    public boolean insert(Ticket ticket) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO ticket (prijs, klantId, programmatieId) " +
            "VALUES (?, ?, ?)"
        );

        statement.setDouble(1, ticket.getPrijs());
        statement.setInt(2, ticket.getKlant().getId());
        statement.setInt(3, ticket.getProgrammatie().getId());

        return statement.execute();
    }

    public boolean update(Ticket ticket) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE ticket " +
            "SET prijs = ?, klantId = ?, programmatieId = ? " +
            "WHERE id = ?"
        );

        statement.setDouble(1, ticket.getPrijs());
        statement.setInt(2, ticket.getKlant().getId());
        statement.setInt(3, ticket.getProgrammatie().getId());
        statement.setInt(4, ticket.getId());

        return statement.execute();
    }

    public boolean delete(Ticket ticket) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM ticket " +
            "WHERE id = ?"
        );

        statement.setInt(1, ticket.getId());

        return statement.execute();
    }
}
