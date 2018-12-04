package Model;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.Image;

import mlcl.partistico.R;

public class Utils {

    private static Utils instance;

    private Athlete[] athletes;
    private Athlete activeAthlete;
    private Activity activity;

    private Utils() {

        athletes = new Athlete[3];
        athletes[0] = new Athlete("Adolf Hitler", "20/04/1898", "30/04/1945", "Führer", "Masculino", "", "Genocida");
        athletes[1] = new Athlete("Joseph Stalin","18/12/1878","05/03/1953","General Secretary","Masculino", "", "Arquinimigo do Hitler");
        athletes[2] = new Athlete("Winston Churchill","30/11/1874","24/01/1965","Prime Minister","Masculino", "BOSTA", "Fez um brilhante discurso");
    }

    public static Utils getInstance(){

        if (instance == null){
            instance = new Utils();
        }

        return instance;
    }

    public Activity getActivity(){ return activity; }

    public void setActivity(Activity activity){
        this.activity = activity;
        athletes[0].setImage(BitmapFactory.decodeResource(activity.getResources(),R.drawable.hitler));
        athletes[1].setImage(BitmapFactory.decodeResource(activity.getResources(),R.drawable.stalin));
        athletes[2].setImage(BitmapFactory.decodeResource(activity.getResources(),R.drawable.churchill));
    }

    public Athlete[] getAthletes() {
        return athletes;
    }

    public Athlete getActiveAthlete() {
        return activeAthlete;
    }

    public void setActiveAthlete(int activeAthleteIndex) {
        this.activeAthlete = athletes[activeAthleteIndex];
    }
}
