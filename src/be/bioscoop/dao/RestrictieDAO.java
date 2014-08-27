package be.bioscoop.dao;

import be.bioscoop.entities.Restrictie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestrictieDAO implements DAOInterface<Restrictie>
{
    private Connection connection;

    public RestrictieDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Restrictie> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM restrictie " +
            "ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Restrictie> restricties = new ArrayList<Restrictie>();

        while (resultSet.next())
        {
            restricties.add(
                new Restrictie(
                    resultSet.getInt(1), resultSet.getString(2)
                )
            );
        }

        return restricties;
    }

    public Restrictie find(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM restrictie " +
            "WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Restrictie(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }

    public Restrictie first() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM restrictie " +
            "ORDER BY id ASC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Restrictie(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }

    public Restrictie last() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam " +
            "FROM restrictie " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Restrictie(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }

    public int lastId() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id " +
            "FROM restrictie " +
            "ORDER BY id DESC " +
            "LIMIT 1"
        );

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return resultSet.getInt(1);
    }

    public boolean insert(Restrictie restrictie) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO restrictie (naam) " +
            "VALUES (?)"
        );

        statement.setString(1, restrictie.getNaam());

        return statement.execute();
    }

    public boolean update(Restrictie restrictie) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "UPDATE restrictie " +
            "SET naam = ? " +
            "WHERE id = ?"
        );

        statement.setString(1, restrictie.getNaam());
        statement.setInt(2, restrictie.getId());

        return statement.execute();
    }

    public boolean delete(Restrictie restrictie) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM restrictie " +
            "WHERE id = ?"
        );

        statement.setInt(1, restrictie.getId());

        return statement.execute();
    }
}
