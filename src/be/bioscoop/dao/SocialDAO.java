package be.bioscoop.dao;

import be.bioscoop.models.Social;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocialDAO
{
    private Connection connection;

    public SocialDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Social> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, datum, type, bericht, filmId FROM social ORDER BY datum"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Social> socials = new ArrayList<Social>();

        while (resultSet.next())
        {
            socials.add(
                new Social(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    new FilmDAO(this.connection).get(resultSet.getInt(5))
                )
            );
        }

        return socials;
    }

    public boolean insert(Social social) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO social (datum, type, bericht, filmId) VALUES (?, ?, ?, ?)"
        );

        statement.setDate(1, social.getDatum());
        statement.setString(2, social.getType());
        statement.setString(3, social.getBericht());
        statement.setInt(4, social.getFilm().getId());

        return statement.execute();
    }

    public Social get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, datum, type, bericht, filmId FROM social WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        return new Social(
            resultSet.getInt(1),
            resultSet.getDate(2),
            resultSet.getString(3),
            resultSet.getString(4),
            new FilmDAO(this.connection).get(resultSet.getInt(5))
        );
    }
}
