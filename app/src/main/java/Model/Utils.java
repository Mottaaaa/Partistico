package Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.WindowDecorActionBar;
import android.util.Base64;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import Database.BDAthlete;
import Database.BDClub;
import Database.BDCompetition;
import Database.BDExercise;
import Database.BDNonAthlete;
import Database.BDWarmup;
import Database.DatabaseAdapter;
import Database.FirebaseAdapter;
import mlcl.partistico.R;

public class Utils {

    private Context context;
    private static Utils instance;
    private Bitmap image;
    private BDAthlete activeAthlete;
    private BDNonAthlete activeNonAthlete;
    private BDCompetition activeCompetition;
    private BDWarmup activeWarmup;

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

    public BDWarmup getActiveWarmup() {
        return activeWarmup;
    }

    public void setActiveWarmup(int id) {
        this.activeWarmup = getWarmupByID(id);
    }

    public BDNonAthlete getActiveNonAthlete() {
        return activeNonAthlete;
    }

    public void setActiveNonAthlete(int id) {

        this.activeNonAthlete = getNonAthleteByID(id);
    }

    public static String bitmapToString(Bitmap image){
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, byteStream);
        byte [] b = byteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap stringToBitmap(String str){
        try{
            byte [] encodeByte = Base64.decode(str,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
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

        //Populate Competition
        dbAdapter.insertCompetition((new BDCompetition(1, "World War 1", "38.5587448/-9.0402922644068/Manjar do Norte", "28/07/1914", "11/11/1918", "WAR", "1/3/5/7", "1/3/5", "A Primeira Grande Guerra")));
        dbAdapter.insertCompetition((new BDCompetition(2, "World War 2", "38.520872350000005/-8.83894542882081/Instituto Politécnico de Setúbal", "1/09/1939", "2/09/1945", "WAR", "2/4/6/8", "2/4/6", "A Segunda Grande Guerra")));

        dbAdapter.close();

    }

    //BDClub
    public void populateClubs(List<BDClub> clubs){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();

        for(BDClub club : clubs){
            adapter.insertClub(club);
        }

        adapter.close();
    }

    public List<BDClub> getDBClubs() {

        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        List<BDClub> clubs = dbAdapter.getClubs();
        dbAdapter.close();
        return clubs;
    }

    public BDClub getClubByID(int clubID) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        //FirebaseAdapter dbAdapter = new FirebaseAdapter(context);
        dbAdapter.open();
        BDClub club = dbAdapter.getClubByID(clubID);
        dbAdapter.close();
        return club;
    }

    //BDAthlete
    public void populateAthletes(List<BDAthlete> athletes){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();

        for(BDAthlete athlete : athletes){
            adapter.insertAthlete(athlete);
        }

        adapter.close();
    }

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
    public void populateNonAthletes(List<BDNonAthlete> nonAthletes){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();

        for(BDNonAthlete nonAthlete : nonAthletes){
            adapter.insertNonAthlete(nonAthlete);
        }

        adapter.close();
    }

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

    //BDWarmup
    public void insertWarmup(BDWarmup warmup){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertWarmup(warmup);
        adapter.close();
    }

    public List<BDWarmup> getWarmups(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmup> warmups = adapter.getWarmups();
        adapter.close();
        return warmups;
    }

    public List<BDWarmup> getWarmupsByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmup> warmups = adapter.getWarmupsByName(name);
        adapter.close();
        return warmups;
    }

    public BDWarmup getWarmupByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDWarmup warmup = adapter.getWarmupByID(id);
        adapter.close();
        return warmup;
    }

    //BDExercises
    public void insertExercise(BDExercise exercise){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertExercise(exercise);
        adapter.close();
    }

    public List<BDExercise> getExercises(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDExercise> exercises = adapter.getExercises();
        adapter.close();
        return exercises;
    }

    public List<BDExercise> getExercisesByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDExercise> exercises = adapter.getExercisesByName(name);
        adapter.close();
        return exercises;
    }

    public BDExercise getExerciseByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDExercise exercise = adapter.getExerciseByID(id);
        adapter.close();
        return exercise;
    }

    public List<BDExercise> getExercisesByWarmup(int warmupID){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDExercise> exercises = adapter.getExercisesByWarmup(warmupID);
        adapter.close();
        return exercises;
    }

    //Firebase
    public void getDataFromFirebase(){
        FirebaseAdapter fa = new FirebaseAdapter(context,this);
        fa.getClubs();
        fa.getAthletes();
        fa.getNonAthletes();
    }

    public void setAthleteImg(int id, Bitmap img){
        List<BDAthlete> athletes = getDBAthletes();
        for(BDAthlete a : athletes){
            if(a.getId() == id){
                a.setImage(img);
            }
        }
    }

    public void setNonAthleteImg(int id, Bitmap img){
        List<BDNonAthlete> nonAthletes = getNonAthletes();
        for(BDNonAthlete a : nonAthletes){
            if(a.getId() == id){
                a.setImage(img);
            }
        }
    }

    public void setClubImg(int id, Bitmap img){
        List<BDClub> clubs = getDBClubs();
        for(BDClub a : clubs){
            if(a.getId() == id){
                a.setImage(img);
            }
        }
    }
}
