package be.kdg.models;

import java.time.LocalDate;

/**
 * Created by Kevin on 8/4/2014.
 */
public class Film {
    private int id;
    private String naam;
    private String duur;
    private String genre;
    private double beoordeling;
    private LocalDate release; //verbetering tov Date / DateTime
    private Restrictie restrictie;


}
