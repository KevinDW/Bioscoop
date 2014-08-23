package be.bioscoop.entities;

public class Bioscoop
{
    private int id;
    private String naam;
    private String straat;
    private String postcode;
    private String gemeente;

    public Bioscoop(int id, String naam, String straat, String postcode, String gemeente)
    {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public Bioscoop(String naam, String straat, String postcode, String gemeente)
    {
        this.naam = naam;
        this.straat = straat;
        this.postcode = postcode;
        this.gemeente = gemeente;
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
