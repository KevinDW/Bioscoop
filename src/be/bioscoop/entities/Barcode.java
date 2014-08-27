package be.bioscoop.entities;

public class Barcode
{
    private int id;
    private String code;
    private boolean gebruikt;
    private Ticket ticket;

    public Barcode(int id, String code, boolean gebruikt, Ticket ticket)
    {
        this(code, gebruikt, ticket);
        this.id = id;
    }

    public Barcode(String code, boolean gebruikt, Ticket ticket)
    {
        this.code = code;
        this.gebruikt = gebruikt;
        this.ticket = ticket;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public boolean isGebruikt()
    {
        return gebruikt;
    }

    public void setGebruikt(boolean gebruikt)
    {
        this.gebruikt = gebruikt;
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
