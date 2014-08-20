package be.bioscoop.models;

import java.sql.Date;

public class Social
{
    private int id;
    private Date datum;
    private String type;
    private String bericht;
    private Film film;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDatum()
    {
        return datum;
    }

    public void setDatum(Date datum)
    {
        this.datum = datum;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getBericht()
    {
        return bericht;
    }

    public void setBericht(String bericht)
    {
        this.bericht = bericht;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }
}
