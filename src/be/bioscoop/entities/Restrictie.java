package be.bioscoop.entities;

public class Restrictie
{
    private int id;
    private String naam;

    public Restrictie(int id, String naam)
    {
        this(naam);
        this.id = id;
    }

    public Restrictie(String naam)
    {
        this.naam = naam;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }
}
