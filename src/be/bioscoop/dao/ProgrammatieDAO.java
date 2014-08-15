package be.bioscoop.dao;

import java.sql.Connection;

public class ProgrammatieDAO
{
    private Connection connection;

    public ProgrammatieDAO(Connection connection)
    {
        this.connection = connection;
    }
}
