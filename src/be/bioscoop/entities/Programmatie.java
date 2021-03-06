package be.bioscoop.entities;

import java.sql.Date;
import java.sql.Time;

public class Programmatie
{
    private int id;
    private Date datum;
    private Time beginUur;
    private Zaal zaal;
    private Film film;

    public Programmatie(int id, Date datum, Time beginUur, Zaal zaal, Film film)
    {
        this(datum, beginUur, zaal, film);
        this.id = id;
    }

    public Programmatie(Date datum, Time beginUur, Zaal zaal, Film film)
    {
        this.datum = datum;
        this.beginUur = beginUur;
        this.zaal = zaal;
        this.film = film;
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
