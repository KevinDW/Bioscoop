package be.bioscoop.entities;

public class Genre
{
    private int id;
    private String naam;

    public Genre(int id, String naam)
    {
        this(naam);
        this.id = id;
    }

    public Genre(String naam)
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
