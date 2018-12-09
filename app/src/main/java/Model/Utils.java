package Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.List;

import Database.BDAthlete;
import Database.BDClub;
import Database.BDCompetition;
import Database.BDWarmUpExercise;
import Database.BDNonAthlete;
import Database.BDExercise;
import Database.BDWarmup;
import Database.DatabaseAdapter;
import Database.FirebaseAdapter;
import mlcl.partistico.R;

public class Utils {

    private Context context;
    private static Utils instance;
    private BDAthlete activeAthlete;
    private BDNonAthlete activeNonAthlete;
    private BDCompetition activeCompetition;
    private BDWarmup activeWarmup;
    private BDExercise activeExercise = new BDExercise(1, "Hitler Techno");

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

    public void delete(){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        dbAdapter.delete();
        dbAdapter.close();
    }

    public Context getContext() {
        return context;
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
        this.activeWarmup = getBDWarmupByID(id);
    }

    public BDNonAthlete getActiveNonAthlete() {
        return activeNonAthlete;
    }

    public void setActiveNonAthlete(int id) {

        this.activeNonAthlete = getNonAthleteByID(id);
    }

    public BDExercise getActiveExercise() {
        return activeExercise;
    }

    public void setActiveExercise(BDExercise activeExercise) {
        this.activeExercise = activeExercise;
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
        dbAdapter.insertAthlete(new BDAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler), "Adolf Hitler", "20/04/1889", "30/04/1945", "Führer", "Masculino","Nota", "Genocia", 1));
        dbAdapter.insertAthlete(new BDAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino", "Nota", "Arquinimigo do Hitler", 2));
        dbAdapter.insertAthlete(new BDAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino", "Nota", "Fez um brilhante discurso", 2));
        dbAdapter.insertAthlete(new BDAthlete(4, BitmapFactory.decodeResource(context.getResources(), R.drawable.mussolini), "Benito Mussolini", "29/07/1883", "28/04/1945", "Duce", "Masculino", "Nota", "Compincha do Hitler", 1));
        dbAdapter.insertAthlete(new BDAthlete(5, BitmapFactory.decodeResource(context.getResources(), R.drawable.hirohito), "Hirohito", "29/04/1901", "07/01/1989", "Emperor", "Masculino", "Nota", "Levou com duas bombas que se lixou", 1));

        //Populate NonAthletes
        dbAdapter.insertNonAthlete((new BDNonAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.salazar), "António de Oliveira Salazar", "28/04/1889", "Presidente", "Masculino", "Nota", "Caiu da cadeira", 3)));
        dbAdapter.insertNonAthlete((new BDNonAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.franco), "Francisco Franco", "04/12/1892", "Presidente", "Masculino", "Nota", "Ficou com ciúmes do Salazar", 3)));
        dbAdapter.insertNonAthlete((new BDNonAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.roosevelt), "Franklin Delano Roosevelt", "30/01/1882", "President", "Masculino", "Nota", "Ficou triste por a guerra estar a acabar e morreu", 2)));

        //Populate Competition
        dbAdapter.insertCompetition((new BDCompetition(1, "World War 1", "38.5587448/-9.0402922644068/Manjar do Norte", "28/07/1914", "11/11/1918", "WAR", "1/3/5/7", "1/3/5", "A Primeira Grande Guerra")));
        dbAdapter.insertCompetition((new BDCompetition(2, "World War 2", "38.520872350000005/-8.83894542882081/Instituto Politécnico de Setúbal", "1/09/1939", "2/09/1945", "WAR", "2/4/6/8", "2/4/6", "A Segunda Grande Guerra")));

        //Populate Exercises
        dbAdapter.insertExercise(new BDExercise(1,"Avião"));

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
    public void populateCompetitions(List<BDCompetition> competitions){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();

        for(BDCompetition competition : competitions){
            adapter.insertCompetition(competition);
        }

        adapter.close();
    }

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
    public void insertBDWarmup(BDWarmup warmup){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertWarmup(warmup);
        adapter.close();
    }

    public List<BDWarmup> getBDWarmups(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmup> warmups = adapter.getWarmups();
        adapter.close();
        return warmups;
    }

    public List<BDWarmup> getBDWarmupByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmup> warmups = adapter.getWarmupsByName(name);
        adapter.close();
        return warmups;
    }

    public BDWarmup getBDWarmupByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDWarmup warmup = adapter.getWarmupByID(id);
        adapter.close();
        return warmup;
    }

    //BDWarmUpExercise
    public void insertBDWarmUpExercise(BDWarmUpExercise exercise){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertWarmUpExercise(exercise);
        adapter.close();
    }

    public List<BDWarmUpExercise> getBDWarmUpExercises(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmUpExercise> exercises = adapter.getWarmUpExercises();
        adapter.close();
        return exercises;
    }

    public List<BDWarmUpExercise> getBDWarmUpExerciseByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmUpExercise> exercises = adapter.getWarmUpExercisesByName(name);
        adapter.close();
        return exercises;
    }

    public BDWarmUpExercise getBDWarmUpExerciseByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDWarmUpExercise exercise = adapter.getWarmUpExercisesByID(id);
        adapter.close();
        return exercise;
    }

    public List<BDWarmUpExercise> getBDWarmUpExercisesByWarmup(int warmupID){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmUpExercise> exercises = adapter.getWarmUpExercisesByWarmup(warmupID);
        adapter.close();
        return exercises;
    }

    //BDExercise
    public void populateExercises(){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        dbAdapter.deleteExerciseTable();
        dbAdapter.insertExercise(new BDExercise(1,"Avião"));
        dbAdapter.close();
    }

    public void insertBDExercise(BDExercise training){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertExercise(training);
        adapter.close();
    }

    public List<BDExercise> getBDExercises(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDExercise> trainings = adapter.getExercises();
        adapter.close();
        return trainings;
    }

    public List<BDExercise> getBDExercisesByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDExercise> trainings = adapter.getExercisesByName(name);
        adapter.close();
        return trainings;
    }

    public BDExercise getBDExerciseByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDExercise training = adapter.getExerciseByID(id);
        adapter.close();
        return training;
    }

    //Firebase
    public void getDataFromFirebase(){
        FirebaseAdapter fa = new FirebaseAdapter(context,this);
        DatabaseAdapter adapter = new DatabaseAdapter(context);

        adapter.delete();

        fa.getClubs();
        fa.getAthletes();
        fa.getNonAthletes();
        fa.getCompetitions();
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
