package be.bioscoop.dao;

import be.bioscoop.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO
{
    private Connection connection;

    public FilmDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Film> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM film ORDER BY naam"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Film> films = new ArrayList<Film>();

        while (resultSet.next())
        {
            Film film = new Film(resultSet.getInt(1));

            film.setNaam(resultSet.getString(2));
            film.setCode(resultSet.getString(3));
            film.setDuur(resultSet.getInt(4));
            film.setGenre(new Genre(resultSet.getInt(5)));
            film.setBeoordeling(resultSet.getDouble(6));
            film.setDatum(resultSet.getDate(7));
            film.setRestrictie(new Restrictie(resultSet.getInt(8)));

            films.add(film);
        }

        return films;
    }
}
