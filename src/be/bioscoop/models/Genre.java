package be.bioscoop.models;

public class Genre
{
    private int id;
    private String naam;

    public Genre(int id, String naam)
    {
        this.id = id;
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
