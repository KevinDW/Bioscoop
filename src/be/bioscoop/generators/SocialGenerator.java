package be.bioscoop.generators;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import java.util.Random;

public class SocialGenerator
{
    public String createXml()
    {
        Random random = new Random();

        Document document = new Document();
        Element social = new Element("Social");

        String[] types = {"Twitter", "Facebook", "Google+"};
        String[] berichten = {"Mooie film", "Spannend!", "Angstaanjagend!", "Saai..", "Slechtste film ooit!"};

        for (int i = 0; i < 5; i++)
        {
            Element message = new Element("Message");

            Element datum = new Element("Datum");
            datum.addContent("2014-" + (random.nextInt(12) + 1) + "-" + (random.nextInt(28) + 1));
            message.addContent(datum);

            Element type = new Element("Type");
            type.addContent(types[random.nextInt(3)]);
            message.addContent(type);

            Element bericht = new Element("Bericht");
            bericht.addContent(berichten[random.nextInt(5)]);
            message.addContent(bericht);

            Element film = new Element("Film");
            film.addContent("" + (random.nextInt(3) + 1));
            message.addContent(film);

            social.addContent(message);
        }

        document.addContent(social);

        return new XMLOutputter().outputString(document);
    }
}
