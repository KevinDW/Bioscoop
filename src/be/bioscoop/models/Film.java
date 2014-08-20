package be.bioscoop.models;

public class Film
{
    private int id;
    private String naam;
    private String code;
    private int jaar;
    private int duur;
    private double beoordeling;
    private Restrictie restrictie;
    private Genre genre;

    public Film(int id, String naam, String code, int jaar, int duur, double beoordeling, Restrictie restrictie, Genre genre)
    {
        this.id = id;
        this.naam = naam;
        this.code = code;
        this.jaar = jaar;
        this.duur = duur;
        this.beoordeling = beoordeling;
        this.restrictie = restrictie;
        this.genre = genre;
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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public int getJaar()
    {
        return jaar;
    }

    public void setJaar(int jaar)
    {
        this.jaar = jaar;
    }

    public int getDuur()
    {
        return duur;
    }

    public void setDuur(int duur)
    {
        this.duur = duur;
    }

    public double getBeoordeling()
    {
        return beoordeling;
    }

    public void setBeoordeling(double beoordeling)
    {
        this.beoordeling = beoordeling;
    }

    public Restrictie getRestrictie()
    {
        return restrictie;
    }

    public void setRestrictie(Restrictie restrictie)
    {
        this.restrictie = restrictie;
    }

    public Genre getGenre()
    {
        return genre;
    }

    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }
}
