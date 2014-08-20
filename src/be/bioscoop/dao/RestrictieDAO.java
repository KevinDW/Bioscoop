package be.bioscoop.dao;

import be.bioscoop.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestrictieDAO
{
    private Connection connection;

    public RestrictieDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Restrictie> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM restrictie ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Restrictie> restricties = new ArrayList<Restrictie>();

        while (resultSet.next())
        {
            Restrictie restrictie = new Restrictie(resultSet.getInt(1));

            restrictie.setNaam(resultSet.getString(2));


            restricties.add(restrictie);
        }

        return restricties;
    }
}
