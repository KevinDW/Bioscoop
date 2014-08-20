package be.bioscoop.dao;

import be.bioscoop.models.Film;
import be.bioscoop.models.Genre;
import be.bioscoop.models.Restrictie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM film ORDER BY naam");
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

    public Film get(int id) throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT id, naam, code, datum, duur, beoordeling, genreId, restrictieId FROM film WHERE id = ?"
        );

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.first();

        Film film = new Film(resultSet.getInt(1));

        film.setNaam(resultSet.getString(2));
        film.setCode(resultSet.getString(3));
        film.setDatum(resultSet.getDate(4));
        film.setDuur(resultSet.getInt(5));
        film.setBeoordeling(resultSet.getDouble(6));
        film.setGenre(new Genre(resultSet.getInt(7)));
        film.setRestrictie(new Restrictie(resultSet.getInt(8)));

        return film;
    }
}
