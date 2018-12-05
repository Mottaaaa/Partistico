package Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.ContactsContract;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.BDAthlete;
import Database.BDClub;
import Database.DatabaseAdapter;
import mlcl.partistico.R;

public class Utils {

    private Context context;
    private static Utils instance;
    private BDAthlete activeAthlete;

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

    public void setActiveAthlete(int activeAthleteIndex) {

        List<BDAthlete> athletes = Utils.getInstance().getDBAthletes();

        this.activeAthlete = athletes.get(activeAthleteIndex);
    }

    public void populateDB() {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();

        //Populate Clubs
        dbAdapter.insertClub(new BDClub(1, "Axis", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)));
        dbAdapter.insertClub(new BDClub(2, "Allies", BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill)));

        //Populate Athletes
        dbAdapter.insertAthlete(new BDAthlete(1,BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler), "Adolf Hitler", "20/04/1898", "30/04/1945", "Führer", "Masculino", "Genocida", 1));
        dbAdapter.insertAthlete(new BDAthlete(2,BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino",  "Arquinimigo do Hitler", 2));
        dbAdapter.insertAthlete(new BDAthlete(3,BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino",  "Fez um brilhante discurso", 2));

        dbAdapter.close();
    }

    public List<BDClub> getDBClubs() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDClub> clubs = dbAdapter.getClubs();
        dbAdapter.close();
        return clubs;
    }

    public List<BDAthlete> getDBAthletes() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDAthlete> athletes = dbAdapter.getAthletes();
        dbAdapter.close();
        return athletes;
    }

    public BDClub getClubByID(int clubID){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        BDClub club = dbAdapter.getClubByID(clubID);
        dbAdapter.close();
        return  club;
    }

    public List<BDAthlete> getAthletesByName(String name){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDAthlete> athletes = dbAdapter.getAthleteByName(name);
        dbAdapter.close();
        return  athletes;
    }
}