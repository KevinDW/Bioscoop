package be.bioscoop.dao;

import be.bioscoop.models.Film;
import be.bioscoop.models.Programmatie;
import be.bioscoop.models.Zaal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgrammatieDAO
{
    private Connection connection;

    public ProgrammatieDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<Programmatie> all() throws SQLException
    {
        PreparedStatement statement = this.connection.prepareStatement(
            "SELECT * FROM programmatie ORDER BY datum"
        );

        ResultSet resultSet = statement.executeQuery();
        List<Programmatie> programmaties = new ArrayList<Programmatie>();

        while (resultSet.next())
        {
            Programmatie programmatie = new Programmatie(resultSet.getInt(1));

            programmatie.setDatum(resultSet.getDate(2));
            programmatie.setBeginUur(resultSet.getTime(3));
            programmatie.setZaal(new Zaal(resultSet.getInt(4)));
            programmatie.setFilm(new Film(resultSet.getInt(5)));

            programmaties.add(programmatie);
        }

        return programmaties;
    }
}
