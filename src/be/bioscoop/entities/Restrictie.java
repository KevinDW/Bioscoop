package be.bioscoop.entities;

public class Restrictie
{
    private int id;
    private String naam;

    public Restrictie(int id, String naam)
    {
        this.id = id;
        this.naam = naam;
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
