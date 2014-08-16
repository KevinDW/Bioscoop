package be.bioscoop.models;

public class Bestelling
{
    private int id;
    private Klant klant;
    private Ticket ticket;

    public Bestelling(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Klant getKlant()
    {
        return klant;
    }

    public void setKlant(Klant klant)
    {
        this.klant = klant;
    }

    public Ticket getTicket()
    {
        return ticket;
    }

    public void setTicket(Ticket ticket)
    {
        this.ticket = ticket;
    }
}
