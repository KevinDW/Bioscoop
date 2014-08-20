package be.bioscoop.models;

public class Ticket
{
    private int id;
    private double prijs;
    private Programmatie programmatie;
    private Barcode barcode;

    public Ticket(int id, double prijs, Programmatie programmatie, Barcode barcode)
    {
        this.id = id;
        this.prijs = prijs;
        this.programmatie = programmatie;
        this.barcode = barcode;
    }

    public Ticket(double prijs, Programmatie programmatie, Barcode barcode)
    {
        this.prijs = prijs;
        this.programmatie = programmatie;
        this.barcode = barcode;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getPrijs()
    {
        return prijs;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }

    public Programmatie getProgrammatie()
    {
        return programmatie;
    }

    public void setProgrammatie(Programmatie programmatie)
    {
        this.programmatie = programmatie;
    }

    public Barcode getBarcode()
    {
        return barcode;
    }

    public void setBarcode(Barcode barcode)
    {
        this.barcode = barcode;
    }
}
