package be.bioscoop.entities;

public class Zaal
{
    private int id;
    private int zaalNr;
    private int capaciteit;
    private int maxRij;
    private int maxKolom;
    private int verdieping;
    private Bioscoop bioscoop;

    public Zaal(int id, int zaalNr, int capaciteit, int maxRij, int maxKolom, int verdieping, Bioscoop bioscoop)
    {
        this(zaalNr, capaciteit, maxRij, maxKolom, verdieping, bioscoop);
        this.id = id;
    }

    public Zaal(int zaalNr, int capaciteit, int maxRij, int maxKolom, int verdieping, Bioscoop bioscoop)
    {
        this.zaalNr = zaalNr;
        this.capaciteit = capaciteit;
        this.maxRij = maxRij;
        this.maxKolom = maxKolom;
        this.verdieping = verdieping;
        this.bioscoop = bioscoop;
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

    public int getMaxRij()
    {
        return maxRij;
    }

    public void setMaxRij(int maxRij)
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
