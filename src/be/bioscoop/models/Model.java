package be.bioscoop.models;

import be.bioscoop.config.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Model
{
    protected Connection connection;

    protected Model()
    {
        try
        {
            this.connection = Database.connect();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected Model(Connection connection)
    {
        this.connection = connection;
    }
}
