package be.bioscoop.entities;

public class Ticket
{
    private int id;
    private double prijs;
    private Klant klant;
    private Programmatie programmatie;

    public Ticket(int id, double prijs, Klant klant, Programmatie programmatie)
    {
        this(prijs, klant, programmatie);
        this.id = id;
    }

    public Ticket(double prijs, Klant klant, Programmatie programmatie)
    {
        this.prijs = prijs;
        this.klant = klant;
        this.programmatie = programmatie;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getPrijs()
    {
        return prijs;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }

    public Klant getKlant()
    {
        return klant;
    }

    public void setKlant(Klant klant)
    {
        this.klant = klant;
    }

    public Programmatie getProgrammatie()
    {
        return programmatie;
    }

    public void setProgrammatie(Programmatie programmatie)
    {
        this.programmatie = programmatie;
    }
}
