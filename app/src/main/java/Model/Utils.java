package Model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.util.List;

import Database.BDAthlete;
import Database.BDClub;
import Database.BDCompetition;
import Database.BDNonAthlete;
import Database.DatabaseAdapter;
import mlcl.partistico.R;

public class Utils {

    private Context context;
    private static Utils instance;

    private BDAthlete activeAthlete;
    private BDNonAthlete activeNonAthlete;
    private BDCompetition activeCompetition;

    private Utils() {
    }

    public static Utils getInstance() {

        if (instance == null) {
            instance = new Utils();
        }

        return instance;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public BDAthlete getActiveAthlete() {
        return activeAthlete;
    }

    public void setActiveAthlete(int id) {

        this.activeAthlete = getAthleteByID(id);
    }

    public BDNonAthlete getActiveNonAthlete() {
        return activeNonAthlete;
    }

    public void setActiveNonAthlete(int id) {

        this.activeNonAthlete = getNonAthleteByID(id);
    }

    public BDCompetition getActiveCompetition() {
        return activeCompetition;
    }

    public void setActiveCompetition(int id) {
        this.activeCompetition = getCompetitionByID(id);
    }

    public void populateDB() {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();

        //Populate Clubs
        dbAdapter.insertClub(new BDClub(1, "Axis", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)));
        dbAdapter.insertClub(new BDClub(2, "Allies", BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill)));
        dbAdapter.insertClub(new BDClub(3, "Neutrals", BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin)));

        //Populate Athletes
        dbAdapter.insertAthlete(new BDAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler), "Adolf Hitler", "20/04/1889", "30/04/1945", "Führer", "Masculino", "Genocia", 1));
        dbAdapter.insertAthlete(new BDAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino", "Arquinimigo do Hitler", 2));
        dbAdapter.insertAthlete(new BDAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino", "Fez um brilhante discurso", 2));
        dbAdapter.insertAthlete(new BDAthlete(4, BitmapFactory.decodeResource(context.getResources(), R.drawable.mussolini), "Benito Mussolini", "29/07/1883", "28/04/1945", "Duce", "Masculino", "Compincha do Hitler", 1));
        dbAdapter.insertAthlete(new BDAthlete(5, BitmapFactory.decodeResource(context.getResources(), R.drawable.hirohito), "Hirohito", "29/04/1901", "07/01/1989", "Emperor", "Masculino", "Levou com duas bombas que se lixou", 1));

        //Populate NonAthletes
        dbAdapter.insertNonAthlete((new BDNonAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.salazar), "António de Oliveira Salazar", "28/04/1889", "Presidente", "Masculino", "Caiu da cadeira", 3)));
        dbAdapter.insertNonAthlete((new BDNonAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.franco), "Francisco Franco", "04/12/1892", "Presidente", "Masculino", "Ficou com ciúmes do Salazar", 3)));
        dbAdapter.insertNonAthlete((new BDNonAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.roosevelt), "Franklin Delano Roosevelt", "30/01/1882", "President", "Masculino", "Ficou triste por a guerra estar a acabar e morreu", 2)));

        //Populate Cometition
        dbAdapter.insertCompetition((new BDCompetition(1, "World War 1", "World", "28/07/1914", "11/11/1918", "WAR", "UK, US, Germany, France, Russia", "Trench and Gas", "A Primeira Grande Guerra")));
        dbAdapter.insertCompetition((new BDCompetition(2, "World War 2", "World", "1/09/1939", "2/09/1945", "WAR", "UK, US, Germany, Italy, France, Russia, Japan, China", "Blitzkrieg, Nukes, Mass Bombing...", "A Segunda Grande Guerra")));

        dbAdapter.close();
    }

    //BDClub
    public List<BDClub> getDBClubs() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDClub> clubs = dbAdapter.getClubs();
        dbAdapter.close();
        return clubs;
    }

    public BDClub getClubByID(int clubID) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        BDClub club = dbAdapter.getClubByID(clubID);
        dbAdapter.close();
        return club;
    }

    //BDAthlete
    public List<BDAthlete> getDBAthletes() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDAthlete> athletes = dbAdapter.getAthletes();
        dbAdapter.close();

        return athletes;
    }

    public List<BDAthlete> getAthletesByName(String name) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDAthlete> athletes = dbAdapter.getAthleteByName(name);
        dbAdapter.close();
        return athletes;
    }

    public BDAthlete getAthleteByID(int id) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        BDAthlete athlete = dbAdapter.getAthleteByID(id);
        dbAdapter.close();
        return athlete;
    }

    //BDNonAthlete
    public List<BDNonAthlete> getNonAthletes() {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDNonAthlete> nonAthletes = dbAdapter.getNonAthletes();
        dbAdapter.close();
        return nonAthletes;
    }

    public List<BDNonAthlete> getNonAthleteByName(String name) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDNonAthlete> nonAthletes = dbAdapter.getNonAthleteByName(name);
        dbAdapter.close();
        return nonAthletes;
    }

    public BDNonAthlete getNonAthleteByID(int id) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        BDNonAthlete nonAthlete = dbAdapter.getNonAthleteByID(id);
        dbAdapter.close();
        return nonAthlete;
    }

    //BDCompetition
    public List<BDCompetition> getDBCompetitions() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDCompetition> competitions = dbAdapter.getCompetitions();
        dbAdapter.close();
        return competitions;
    }

    public List<BDCompetition> getDBCompetitionsByName(String name) {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDCompetition> competitions = dbAdapter.getCompetitionsByName(name);
        dbAdapter.close();
        return competitions;
    }

    public BDCompetition getCompetitionByID(int id) {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        BDCompetition competition = dbAdapter.getCompetitionByID(id);
        dbAdapter.close();
        return competition;
    }
}
