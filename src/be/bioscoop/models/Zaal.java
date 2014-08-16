package be.bioscoop.models;

public class Zaal
{
    private int id;
    private int zaalNr;
    private int capaciteit;
    private String maxRij;
    private int maxKolom;
    private int verdieping;
    private Bioscoop bioscoop;

    public Zaal(int id)
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

    public int getZaalNr()
    {
        return zaalNr;
    }

    public void setZaalNr(int zaalNr)
    {
        this.zaalNr = zaalNr;
    }

    public int getCapaciteit()
    {
        return capaciteit;
    }

    public void setCapaciteit(int capaciteit)
    {
        this.capaciteit = capaciteit;
    }

    public String getMaxRij()
    {
        return maxRij;
    }

    public void setMaxRij(String maxRij)
    {
        this.maxRij = maxRij;
    }

    public int getMaxKolom()
    {
        return maxKolom;
    }

    public void setMaxKolom(int maxKolom)
    {
        this.maxKolom = maxKolom;
    }

    public int getVerdieping()
    {
        return verdieping;
    }

    public void setVerdieping(int verdieping)
    {
        this.verdieping = verdieping;
    }

    public Bioscoop getBioscoop()
    {
        return bioscoop;
    }

    public void setBioscoop(Bioscoop bioscoop)
    {
        this.bioscoop = bioscoop;
    }
}
