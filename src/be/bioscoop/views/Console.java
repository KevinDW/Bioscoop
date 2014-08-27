package be.bioscoop.views;

import be.bioscoop.config.Database;
import be.bioscoop.dao.BioscoopDAO;
import be.bioscoop.dao.FilmDAO;
import be.bioscoop.dao.ProgrammatieDAO;
import be.bioscoop.dao.SocialDAO;
import be.bioscoop.entities.Bioscoop;
import be.bioscoop.entities.Film;
import be.bioscoop.entities.Programmatie;
import be.bioscoop.entities.Social;
import be.bioscoop.generators.SocialGenerator;
import be.bioscoop.queues.SocialReceiver;
import be.bioscoop.queues.SocialSender;
import org.jdom.JDOMException;

import javax.jms.JMSException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Console
{
    private Connection connection;
    private Scanner scanner;

    public Console() throws SQLException
    {
        this.connection = Database.connect();
        this.scanner = new Scanner(System.in);
    }

    private void programmatiesVoorBepaaldeBioscoopTussenTweeDatums() throws SQLException
    {
        BioscoopDAO bioscoopDAO = new BioscoopDAO(connection);
        List<Bioscoop> bioscopen = bioscoopDAO.all();

        System.out.printf("%-2s | ", "ID");
        System.out.printf("%-20s | ", "Naam");
        System.out.printf("%-20s | ", "Straat");
        System.out.printf("%-8s | ", "Postcode");
        System.out.printf("%s \n", "Gemeente");

        System.out.printf("%-2s | ", "");
        System.out.printf("%-20s | ", "");
        System.out.printf("%-20s | ", "");
        System.out.printf("%-8s | ", "");
        System.out.printf("%s \n", "");

        for (Bioscoop bioscoop : bioscopen)
        {
            System.out.printf("%-2d | ", bioscoop.getId());
            System.out.printf("%-20s | ", bioscoop.getNaam());
            System.out.printf("%-20s | ", bioscoop.getStraat());
            System.out.printf("%-8s | ", bioscoop.getPostcode());
            System.out.printf("%-20s\n", bioscoop.getGemeente());
        }

        System.out.println();

        int bioscoop = 0;

        while (bioscoop == 0 || bioscoop > bioscoopDAO.lastId())
        {
            System.out.println("Kan ID niet vinden.");
            System.out.print("Bioscoop (ID uit lijst): ");
            bioscoop = Integer.parseInt(scanner.nextLine());
        }

        System.out.print("Begindatum (yyyy-mm-dd): ");
        Date beginDatum = Date.valueOf(scanner.nextLine());

        System.out.print("Einddatum (yyyy-mm-dd): ");
        Date eindDatum = Date.valueOf(scanner.nextLine());

        System.out.println();

        ProgrammatieDAO programmatieDAO = new ProgrammatieDAO(connection);
        List<Programmatie> programmaties = programmatieDAO.whereBioscoopAndDateBetween(bioscoop, beginDatum, eindDatum);

        System.out.printf("%-10s | ", "Datum");
        System.out.printf("%-8s | ", "Uur");
        System.out.printf("%-20s | ", "Bioscoop");
        System.out.printf("%-7s | ", "ZaalNr.");
        System.out.printf("%s \n", "Film");

        System.out.printf("%-10s | ", "");
        System.out.printf("%-8s | ", "");
        System.out.printf("%-20s | ", "");
        System.out.printf("%-7s | ", "");
        System.out.printf("%s \n", "");

        for (Programmatie programmatie : programmaties)
        {
            System.out.printf("%-10s | ", programmatie.getDatum());
            System.out.printf("%-8s | ", programmatie.getBeginUur());
            System.out.printf("%-20s | ", programmatie.getZaal().getBioscoop().getNaam());
            System.out.printf("%-7d | ", programmatie.getZaal().getZaalNr());
            System.out.printf("%s \n", programmatie.getFilm().getNaam());
        }
    }

    private void socialeBerichtenViaDAO() throws SQLException
    {
        FilmDAO filmDAO = new FilmDAO(connection);
        List<Film> films = filmDAO.all();

        System.out.printf("%-2s | ", "ID");
        System.out.printf("%-40s | ", "Naam");
        System.out.printf("%-4s | ", "Jaar");
        System.out.printf("%-4s | ", "Duur");
        System.out.printf("%-12s | ", "Beoordeling");
        System.out.printf("%-12s | ", "Genre");
        System.out.printf("%s \n", "Restrictie");

        System.out.printf("%-2s | ", "");
        System.out.printf("%-40s | ", "");
        System.out.printf("%-4s | ", "");
        System.out.printf("%-4s | ", "");
        System.out.printf("%-12s | ", "");
        System.out.printf("%-12s | ", "");
        System.out.printf("%s \n", "");

        for (Film film : films)
        {
            System.out.printf("%-2d | ", film.getId());
            System.out.printf("%-40s | ", film.getNaam());
            System.out.printf("%-4d | ", film.getJaar());
            System.out.printf("%-4d | ", film.getDuur());
            System.out.printf("%-12.1f | ", film.getBeoordeling());
            System.out.printf("%-12s | ", film.getGenre().getNaam());
            System.out.printf("%s \n", film.getRestrictie().getNaam());
        }

        System.out.println();

        int film = 0;

        while (film == 0 || film > filmDAO.lastId())
        {
            System.out.println("Kan ID niet vinden.");
            System.out.print("Film (ID uit lijst): ");
            film = Integer.parseInt(scanner.nextLine());
        }

        System.out.print("Datum bericht: ");
        Date datum = Date.valueOf(scanner.nextLine());

        System.out.print("Sociaal Netwerk (Twitter-FB-G+): ");
        String type = scanner.nextLine();

        System.out.print("Inhoud bericht: ");
        String bericht = scanner.nextLine();

        System.out.println();

        SocialDAO sociaalDAO = new SocialDAO(connection);
        sociaalDAO.insert(new Social(datum, type, bericht, filmDAO.find(film)));
    }

    private void socialeBerichtenOpQueue() throws JDOMException, JMSException, IOException, SQLException
    {
        SocialGenerator socialGenerator = new SocialGenerator();
        String message = socialGenerator.createXml();

        SocialSender socialSender = new SocialSender();
        socialSender.sendMessage(message);

        SocialReceiver socialReceiver = new SocialReceiver();
        socialReceiver.receiveMessage();
    }

    public static void main(String[] args)
    {
        try
        {
            Console console = new Console();

            System.out.printf("Methode: ");
            int methode = Integer.parseInt(console.scanner.nextLine());

            switch (methode)
            {
                case 1:
                    console.programmatiesVoorBepaaldeBioscoopTussenTweeDatums();
                    break;
                case 2:
                    console.socialeBerichtenViaDAO();
                    break;
                case 3:
                    console.socialeBerichtenOpQueue();
                    break;
            }

            Database.close();
        }
        catch (JDOMException exception)
        {
            exception.getMessage();
        }
        catch (JMSException exception)
        {
            exception.getMessage();
        }
        catch (IOException exception)
        {
            exception.getMessage();
        }
        catch (SQLException exception)
        {
            exception.getMessage();
        }
    }
}
