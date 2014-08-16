package be.bioscoop.models;

import java.sql.Time;
import java.util.Date;

public class Programmatie
{
    private int id;
    private Date datum;
    private Time beginUur;
    private Zaal zaal;
    private Film film;

    public Programmatie(int id)
    {
        this.id = id;
    }

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

    public Time getBeginUur()
    {
        return beginUur;
    }

    public void setBeginUur(Time beginUur)
    {
        this.beginUur = beginUur;
    }

    public Zaal getZaal()
    {
        return zaal;
    }

    public void setZaal(Zaal zaal)
    {
        this.zaal = zaal;
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
