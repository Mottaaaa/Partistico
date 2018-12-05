package Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.ContactsContract;

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
        BDClub club1 = new BDClub("WW2", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler));
        dbAdapter.insertClub(club1);

        //Populate Athletes
        dbAdapter.insertAthlete(new BDAthlete(BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler), "Adolf Hitler", "20/04/1898", "30/04/1945", "Führer", "Masculino", "Tirou os óculos a tremer", "Genocida"));
        dbAdapter.insertAthlete(new BDAthlete(BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino", "Gritou viva o comunismo", "Arquinimigo do Hitler"));
        dbAdapter.insertAthlete(new BDAthlete(BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino", "Não se percebia o que ele falava", "Fez um brilhante discurso"));

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
}
