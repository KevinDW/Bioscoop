package be.bioscoop.models;

import java.util.Date;

public class Film
{
    private int id;
    private String naam;
    private String code;
    private int duur;
    private double beoordeling;
    private Date datum;
    private Genre genre;
    private Restrictie restrictie;

    public Film(int id, String naam, String code, int duur, double beoordeling, Date datum, Genre genre, Restrictie restrictie)
    {
        this.id = id;
        this.naam = naam;
        this.code = code;
        this.duur = duur;
        this.beoordeling = beoordeling;
        this.datum = datum;
        this.genre = genre;
        this.restrictie = restrictie;
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

    public Date getDatum()
    {
        return datum;
    }

    public void setDatum(Date datum)
    {
        this.datum = datum;
    }

    public Genre getGenre()
    {
        return genre;
    }

    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }

    public Restrictie getRestrictie()
    {
        return restrictie;
    }

    public void setRestrictie(Restrictie restrictie)
    {
        this.restrictie = restrictie;
    }
}
