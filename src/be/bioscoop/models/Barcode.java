package be.bioscoop.models;

public class Barcode
{
    private int id;
    private String code;
    private boolean gebruikt;

    public Barcode(int id, String code, boolean gebruikt)
    {
        this.id = id;
        this.code = code;
        this.gebruikt = gebruikt;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public boolean isGebruikt()
    {
        return gebruikt;
    }

    public void setGebruikt(boolean gebruikt)
    {
        this.gebruikt = gebruikt;
    }
}
