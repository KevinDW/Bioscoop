package be.bioscoop.entities;

public class Klant
{
    private int id;
    private String naam;
    private String email;

    public Klant(int id, String naam, String email)
    {
        this(naam, email);
        this.id = id;
    }

    public Klant(String naam, String email)
    {
        this.naam = naam;
        this.email = email;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
