package be.bioscoop.views;

import be.bioscoop.models.Programmatie;

import java.sql.*;
import java.time.LocalDate;

public class Console
{
    public static void main(String[] args)
    {
        try
        {
            String bioscoop = "Kinepolis Antwerpen";
            Date startDatum = Date.valueOf(LocalDate.of(2014, 8, 1));
            Date eindDatum = Date.valueOf(LocalDate.of(2014, 8, 31));

            Programmatie programmatie = new Programmatie();
            ResultSet resultSet = programmatie.inBepaaldeBioscoopInBepaaldePeriode(bioscoop, startDatum, eindDatum);

            System.out.printf("Bioscoop: %s\n\n", bioscoop);

            while(resultSet.next())
            {
                System.out.printf("%d | ", resultSet.getInt("zaal.zaalNr"));
                System.out.printf("%s", resultSet.getString("film.naam"));
                System.out.println();
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }
}
