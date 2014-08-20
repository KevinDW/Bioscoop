package be.bioscoop.dao;

import be.bioscoop.models.Barcode;
import be.bioscoop.models.Programmatie;
import be.bioscoop.models.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO
{
    private Connection connection;

    public TicketDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Ticket> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM ticket ORDER BY id");
        ResultSet resultSet = statement.executeQuery();
        List<Ticket> tickets = new ArrayList<Ticket>();

        while (resultSet.next())
        {
            Ticket ticket = new Ticket(resultSet.getInt(1));

            ticket.setPrijs(resultSet.getDouble(2));
            ticket.setProgrammatie(new Programmatie(resultSet.getInt(3)));
            ticket.setBarcode(new Barcode(resultSet.getInt(4)));

            tickets.add(ticket);
        }

        return tickets;
    }
}
