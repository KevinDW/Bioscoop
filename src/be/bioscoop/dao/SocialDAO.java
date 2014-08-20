package be.bioscoop.dao;

import be.bioscoop.models.Film;
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
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM social ORDER BY datum");
        ResultSet resultSet = statement.executeQuery();
        List<Social> socials = new ArrayList<Social>();

        while (resultSet.next())
        {
            Social social = new Social(resultSet.getInt(1));

            social.setDatum(resultSet.getDate(2));
            social.setType(resultSet.getString(3));
            social.setBericht(resultSet.getString(4));
            social.setFilm(new Film(resultSet.getInt(5)));

            socials.add(social);
        }

        return socials;
    }
}
