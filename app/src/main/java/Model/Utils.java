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
import Database.BDWarmUp;
import Database.DatabaseAdapter;
import Database.FirebaseAdapter;
import mlcl.partistico.R;

public class Utils {

    private Context context;
    private static Utils instance;
    private BDAthlete activeAthlete;
    private BDNonAthlete activeNonAthlete;
    private BDCompetition activeCompetition;
    private BDWarmUp activeWarmup;
    private BDExercise activeExercise;

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

    public BDWarmUp getActiveWarmup() {
        return activeWarmup;
    }

    public void setActiveWarmup(int id) {
        this.activeWarmup = getBDWarmUpByID(id);
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

    public void setActiveExercise(int id) {
        this.activeExercise = getBDExerciseByID(id);
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
    public void insertBDWarmUp(BDWarmUp warmup){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertWarmUp(warmup);
        adapter.close();
    }

    public void deleteBDWarmup(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.deleteWarmup(id);
        adapter.close();
    }

    public void updateBDWarmup(int id, String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.updateWarmup(id, name);
        adapter.close();
    }

    public List<BDWarmUp> getBDWarmUps(){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmUp> warmups = adapter.getWarmups();
        adapter.close();
        return warmups;
    }

    public List<BDWarmUp> getBDWarmUpByName(String name){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        List<BDWarmUp> warmups = adapter.getWarmupsByName(name);
        adapter.close();
        return warmups;
    }

    public BDWarmUp getBDWarmUpByID(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        BDWarmUp warmup = adapter.getWarmupByID(id);
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

    public void updateBDWarmUpExercise(int id, String name, boolean isDone){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.updateWarmUpExercise(id,name,isDone);
        adapter.close();
    }

    public void deleteBDWarmUpExercise(int id){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.deleteWarmUpExercise(id);
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
    public void populateTESTE(){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();
        dbAdapter.deleteExerciseTable();
        dbAdapter.insertExercise(new BDExercise(1, "Avião"));
        dbAdapter.insertExercise(new BDExercise(2, "Perna ao Peito"));

        /*
        dbAdapter.insertWarmUp(new BDWarmUp("Aquecimento1"));
        dbAdapter.insertWarmUpExercise(new BDWarmUpExercise("Ex1", 1, 1));
        dbAdapter.insertWarmUpExercise(new BDWarmUpExercise("Ex2",0,1));
        dbAdapter.insertWarmUpExercise(new BDWarmUpExercise("Ex3",0,1));
        dbAdapter.insertWarmUpExercise(new BDWarmUpExercise("Ex4",0,1));
        */
        dbAdapter.close();
    }

    public void insertBDExercise(BDWarmUpExercise exercise){
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        adapter.open();
        adapter.insertWarmUpExercise(exercise);
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
}
