package be.bioscoop.models;

public class Bioscoop
{
    private int id;
    private String naam;
    private String straat;
    private String postcode;
    private String gemeente;

    public Bioscoop(int id)
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

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public String getStraat()
    {
        return straat;
    }

    public void setStraat(String straat)
    {
        this.straat = straat;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getGemeente()
    {
        return gemeente;
    }

    public void setGemeente(String gemeente)
    {
        this.gemeente = gemeente;
    }
}
