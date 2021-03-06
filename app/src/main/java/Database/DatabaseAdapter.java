package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private static final String DATABASE_NAME = "Database.db";
    static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase db;
    private static DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void delete() {
        db = dbHelper.getWritableDatabase();
        db.delete(BDClub.TABLE_CLUB, null, null);
        db.delete(BDAthlete.TABLE_ATHLETE, null, null);
        db.delete(BDNonAthlete.TABLE_NON_ATHLETE, null, null);
        db.delete(BDCompetition.TABLE_COMPETITION, null, null);
    }

    public void deleteClubTable(){
        db = dbHelper.getWritableDatabase();
        db.delete(BDClub.TABLE_CLUB, null, null);
    }

    public void deleteAthleteTable(){
        db = dbHelper.getWritableDatabase();
        db.delete(BDAthlete.TABLE_ATHLETE, null, null);
    }

    public void deleteNonAthleteTable(){
        db = dbHelper.getWritableDatabase();
        db.delete(BDNonAthlete.TABLE_NON_ATHLETE, null, null);
    }

    public void deleteExerciseTable(){
        db = dbHelper.getWritableDatabase();
        db.delete(BDExercise.TABLE_EXERCISE, null, null);
    }

    public void deleteCompetitionTable(){
        db = dbHelper.getWritableDatabase();
        db.delete(BDCompetition.TABLE_COMPETITION, null, null);
    }

    // Escrever abaixo desta linha para fazer os gets e sets à base de dados

    //BDClub
    public long insertClub(BDClub club) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDClub.KEY_NAME, club.getName());
            newValues.put(BDClub.FOREIGN_DATABASE_ID, club.getId());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDClub.TABLE_CLUB, null, newValues);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDClub> getClubs() {
        List<BDClub> clubs = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDClub.TABLE_CLUB, new String[]{BDClub.KEY_NAME, BDClub.FOREIGN_DATABASE_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDClub club = new BDClub();
                club.setName(cursor.getString(cursor.getColumnIndex(BDClub.KEY_NAME)));
                club.setId(cursor.getInt(cursor.getColumnIndex(BDClub.FOREIGN_DATABASE_ID)));
                clubs.add(club);
            } while (cursor.moveToNext());
        }

        return clubs;
    }

    public BDClub getClubByID(int clubID) {
        BDClub club = new BDClub();

        Cursor cursor = db.query(BDClub.TABLE_CLUB, new String[]{BDClub.KEY_NAME, BDClub.FOREIGN_DATABASE_ID},
                "foreignID = ?", new String[]{clubID + ""}, null, null, null);

        if (cursor.moveToFirst()) {
            club.setName(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NAME)));
            club.setId(cursor.getInt(cursor.getColumnIndex(BDClub.FOREIGN_DATABASE_ID)));
        }

        return club;
    }

    //BDAthlete
    public long insertAthlete(BDAthlete athlete) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDAthlete.KEY_NAME, athlete.getName());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            athlete.getImage().compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            newValues.put(BDAthlete.KEY_IMAGE, bArray);
            newValues.put(BDAthlete.KEY_BIRTHDAY, athlete.getBirthday());
            newValues.put(BDAthlete.KEY_EXPIRATION_DATE, athlete.getExpirationDate());
            newValues.put(BDAthlete.KEY_ECHELON, athlete.getEchelon());
            newValues.put(BDAthlete.KEY_GENDER, athlete.getGender());
            newValues.put(BDAthlete.KEY_NOTES, athlete.getNotes());
            newValues.put(BDAthlete.KEY_HISTORY, athlete.getHistory());
            newValues.put(BDAthlete.KEY_CLUB_ID, athlete.getClubID());
            newValues.put(BDAthlete.FOREIGN_DATABASE_ID, athlete.getId());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDAthlete.TABLE_ATHLETE, null, newValues);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDAthlete> getAthletes() {
        List<BDAthlete> athletes = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDAthlete.TABLE_ATHLETE, new String[]{BDAthlete.KEY_NAME, BDAthlete.KEY_IMAGE,
                        BDAthlete.KEY_BIRTHDAY, BDAthlete.KEY_EXPIRATION_DATE, BDAthlete.KEY_ECHELON, BDAthlete.KEY_GENDER, BDAthlete.KEY_NOTES, BDAthlete.KEY_HISTORY, BDAthlete.KEY_CLUB_ID, BDAthlete.FOREIGN_DATABASE_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDAthlete athelete = new BDAthlete();

                athelete.setName(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                athelete.setImage(image);
                athelete.setBirthday(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_BIRTHDAY)));
                athelete.setExpirationDate(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_EXPIRATION_DATE)));
                athelete.setEchelon(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_ECHELON)));
                athelete.setGender(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_GENDER)));
                athelete.setNotes(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NOTES)));
                athelete.setHistory(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_HISTORY)));
                athelete.setClubID(cursor.getInt(cursor.getColumnIndex(BDAthlete.KEY_CLUB_ID)));
                athelete.setId(cursor.getInt(cursor.getColumnIndex(BDAthlete.FOREIGN_DATABASE_ID)));

                athletes.add(athelete);
            } while (cursor.moveToNext());
        }

        return athletes;
    }

    public List<BDAthlete> getAthleteByName(String name) {
        List<BDAthlete> athletes = new ArrayList<>();

        Cursor cursor = db.query(BDAthlete.TABLE_ATHLETE, new String[]{BDAthlete.KEY_NAME, BDAthlete.KEY_IMAGE,
                        BDAthlete.KEY_BIRTHDAY, BDAthlete.KEY_EXPIRATION_DATE, BDAthlete.KEY_ECHELON, BDAthlete.KEY_GENDER, BDAthlete.KEY_NOTES, BDAthlete.KEY_HISTORY, BDAthlete.KEY_CLUB_ID, BDAthlete.FOREIGN_DATABASE_ID},
                BDAthlete.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDAthlete athelete = new BDAthlete();

                athelete.setName(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                athelete.setImage(image);
                athelete.setBirthday(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_BIRTHDAY)));
                athelete.setExpirationDate(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_EXPIRATION_DATE)));
                athelete.setEchelon(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_ECHELON)));
                athelete.setGender(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_GENDER)));
                athelete.setNotes(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NOTES)));
                athelete.setHistory(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_HISTORY)));
                athelete.setClubID(cursor.getInt(cursor.getColumnIndex(BDAthlete.KEY_CLUB_ID)));
                athelete.setId(cursor.getInt(cursor.getColumnIndex(BDAthlete.FOREIGN_DATABASE_ID)));

                athletes.add(athelete);
            } while (cursor.moveToNext());
        }

        return athletes;
    }

    public BDAthlete getAthleteByID(int id) {
        BDAthlete athelete = new BDAthlete();

        Cursor cursor = db.query(BDAthlete.TABLE_ATHLETE, new String[]{BDAthlete.KEY_NAME, BDAthlete.KEY_IMAGE,
                        BDAthlete.KEY_BIRTHDAY, BDAthlete.KEY_EXPIRATION_DATE, BDAthlete.KEY_ECHELON, BDAthlete.KEY_GENDER, BDAthlete.KEY_NOTES, BDAthlete.KEY_HISTORY, BDAthlete.KEY_CLUB_ID, BDAthlete.FOREIGN_DATABASE_ID},
                BDAthlete.FOREIGN_DATABASE_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                athelete.setName(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                athelete.setImage(image);
                athelete.setBirthday(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_BIRTHDAY)));
                athelete.setExpirationDate(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_EXPIRATION_DATE)));
                athelete.setEchelon(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_ECHELON)));
                athelete.setGender(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_GENDER)));
                athelete.setNotes(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NOTES)));
                athelete.setHistory(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_HISTORY)));
                athelete.setClubID(cursor.getInt(cursor.getColumnIndex(BDAthlete.KEY_CLUB_ID)));
                athelete.setId(cursor.getInt(cursor.getColumnIndex(BDAthlete.FOREIGN_DATABASE_ID)));

            } while (cursor.moveToNext());
        }

        return athelete;
    }

    public List<BDAthlete> getAthletesByClub(int clubID) {
        List<BDAthlete> athletes = new ArrayList<>();

        Cursor cursor = db.query(BDAthlete.TABLE_ATHLETE, new String[]{BDAthlete.KEY_NAME, BDAthlete.KEY_IMAGE,
                        BDAthlete.KEY_BIRTHDAY, BDAthlete.KEY_EXPIRATION_DATE, BDAthlete.KEY_ECHELON, BDAthlete.KEY_GENDER, BDAthlete.KEY_HISTORY, BDAthlete.KEY_CLUB_ID, BDAthlete.FOREIGN_DATABASE_ID},
                "club_id=?", new String[]{clubID + ""}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDAthlete athelete = new BDAthlete();

                athelete.setName(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                athelete.setImage(image);
                athelete.setBirthday(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_BIRTHDAY)));
                athelete.setExpirationDate(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_EXPIRATION_DATE)));
                athelete.setEchelon(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_ECHELON)));
                athelete.setGender(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_GENDER)));
                athelete.setHistory(cursor.getString(cursor.getColumnIndex(BDAthlete.KEY_HISTORY)));
                athelete.setClubID(cursor.getInt(cursor.getColumnIndex(BDAthlete.KEY_CLUB_ID)));
                athelete.setId(cursor.getInt(cursor.getColumnIndex(BDAthlete.FOREIGN_DATABASE_ID)));

                athletes.add(athelete);
            } while (cursor.moveToNext());
        }

        return athletes;
    }

    //BDNonAthlete
    public long insertNonAthlete(BDNonAthlete nonAthlete) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDNonAthlete.KEY_NAME, nonAthlete.getName());

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            nonAthlete.getImage().compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            newValues.put(BDNonAthlete.KEY_IMAGE, bArray);
            newValues.put(BDNonAthlete.KEY_BIRTHDAY, nonAthlete.getBirthday());
            newValues.put(BDNonAthlete.KEY_ROLE, nonAthlete.getRole());
            newValues.put(BDNonAthlete.KEY_GENDER, nonAthlete.getGender());
            newValues.put(BDNonAthlete.KEY_NOTES, nonAthlete.getNotes());
            newValues.put(BDNonAthlete.KEY_HISTORY, nonAthlete.getHistory());
            newValues.put(BDNonAthlete.KEY_CLUB_ID, nonAthlete.getClubID());
            newValues.put(BDNonAthlete.FOREIGN_DATABASE_ID, nonAthlete.getId());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDNonAthlete.TABLE_NON_ATHLETE, null, newValues);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDNonAthlete> getNonAthletes() {
        List<BDNonAthlete> nonAthletes = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDNonAthlete.TABLE_NON_ATHLETE, new String[]{BDNonAthlete.KEY_NAME, BDNonAthlete.KEY_IMAGE,
                        BDNonAthlete.KEY_BIRTHDAY, BDNonAthlete.KEY_ROLE, BDNonAthlete.KEY_GENDER, BDNonAthlete.KEY_NOTES, BDNonAthlete.KEY_HISTORY, BDNonAthlete.KEY_CLUB_ID, BDNonAthlete.FOREIGN_DATABASE_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDNonAthlete nonAthlete = new BDNonAthlete();

                nonAthlete.setName(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDNonAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                nonAthlete.setImage(image);
                nonAthlete.setBirthday(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_BIRTHDAY)));
                nonAthlete.setRole(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_ROLE)));
                nonAthlete.setGender(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_GENDER)));
                nonAthlete.setNotes(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NOTES)));
                nonAthlete.setHistory(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_HISTORY)));
                nonAthlete.setClubID(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.KEY_CLUB_ID)));
                nonAthlete.setId(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.FOREIGN_DATABASE_ID)));

                nonAthletes.add(nonAthlete);
            } while (cursor.moveToNext());
        }

        return nonAthletes;
    }

    public List<BDNonAthlete> getNonAthleteByName(String name) {
        List<BDNonAthlete> nonAthletes = new ArrayList<>();

        Cursor cursor = db.query(BDNonAthlete.TABLE_NON_ATHLETE, new String[]{BDNonAthlete.KEY_NAME, BDNonAthlete.KEY_IMAGE,
                        BDNonAthlete.KEY_BIRTHDAY, BDNonAthlete.KEY_ROLE, BDNonAthlete.KEY_GENDER, BDNonAthlete.KEY_NOTES, BDNonAthlete.KEY_HISTORY, BDNonAthlete.KEY_CLUB_ID, BDNonAthlete.FOREIGN_DATABASE_ID},
                BDNonAthlete.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDNonAthlete nonAthlete = new BDNonAthlete();

                nonAthlete.setName(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDNonAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                nonAthlete.setImage(image);
                nonAthlete.setBirthday(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_BIRTHDAY)));
                nonAthlete.setRole(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_ROLE)));
                nonAthlete.setGender(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_GENDER)));
                nonAthlete.setNotes(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NOTES)));
                nonAthlete.setHistory(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_HISTORY)));
                nonAthlete.setClubID(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.KEY_CLUB_ID)));
                nonAthlete.setId(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.FOREIGN_DATABASE_ID)));

                nonAthletes.add(nonAthlete);
            } while (cursor.moveToNext());
        }

        return nonAthletes;
    }

    public BDNonAthlete getNonAthleteByID(int id) {
        BDNonAthlete nonAthlete = new BDNonAthlete();

        Cursor cursor = db.query(BDNonAthlete.TABLE_NON_ATHLETE, new String[]{BDNonAthlete.KEY_NAME, BDNonAthlete.KEY_IMAGE,
                        BDNonAthlete.KEY_BIRTHDAY, BDNonAthlete.KEY_ROLE, BDNonAthlete.KEY_GENDER, BDNonAthlete.KEY_NOTES, BDNonAthlete.KEY_HISTORY, BDNonAthlete.KEY_CLUB_ID, BDNonAthlete.FOREIGN_DATABASE_ID},
                BDNonAthlete.FOREIGN_DATABASE_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                nonAthlete.setName(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NAME)));

                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDNonAthlete.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                nonAthlete.setImage(image);
                nonAthlete.setBirthday(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_BIRTHDAY)));
                nonAthlete.setRole(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_ROLE)));
                nonAthlete.setGender(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_GENDER)));
                nonAthlete.setNotes(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_NOTES)));
                nonAthlete.setHistory(cursor.getString(cursor.getColumnIndex(BDNonAthlete.KEY_HISTORY)));
                nonAthlete.setClubID(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.KEY_CLUB_ID)));
                nonAthlete.setId(cursor.getInt(cursor.getColumnIndex(BDNonAthlete.FOREIGN_DATABASE_ID)));

            } while (cursor.moveToNext());
        }

        return nonAthlete;
    }

    //BDCompetition
    public long insertCompetition(BDCompetition competition) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDCompetition.KEY_NAME, competition.getName());
            newValues.put(BDCompetition.KEY_COORDINATES, competition.getCoordinates());
            newValues.put(BDCompetition.KEY_START_DATE, competition.getStartDate());
            newValues.put(BDCompetition.KEY_END_DATE, competition.getEndDate());
            newValues.put(BDCompetition.KEY_TYPE_OF_COMPETITION, competition.getTypeOfCompetition());
            newValues.put(BDCompetition.KEY_ECHELONS, competition.getEchelons());
            newValues.put(BDCompetition.KEY_SPECIALIZATIONS, competition.getSpecializations());
            newValues.put(BDCompetition.KEY_INFORMATION, competition.getInformation());
            newValues.put(BDCompetition.FOREIGN_DATABASE_ID, competition.getId());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDCompetition.TABLE_COMPETITION, null, newValues);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDCompetition> getCompetitions() {

        List<BDCompetition> competitions = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDCompetition.TABLE_COMPETITION, new String[]{BDCompetition.KEY_NAME, BDCompetition.KEY_COORDINATES, BDCompetition.KEY_START_DATE,
                        BDCompetition.KEY_END_DATE, BDCompetition.KEY_TYPE_OF_COMPETITION, BDCompetition.KEY_ECHELONS, BDCompetition.KEY_SPECIALIZATIONS, BDCompetition.KEY_INFORMATION, BDCompetition.FOREIGN_DATABASE_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                BDCompetition competition = new BDCompetition();

                competition.setName(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_NAME)));
                competition.setCoordinates(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_COORDINATES)));
                competition.setStartDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_START_DATE)));
                competition.setEndDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_END_DATE)));
                competition.setTypeOfCompetition(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_TYPE_OF_COMPETITION)));
                competition.setEchelons(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_ECHELONS)));
                competition.setSpecializations(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_ECHELONS)));
                competition.setEchelons(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_SPECIALIZATIONS)));
                competition.setInformation(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_INFORMATION)));
                competition.setId(cursor.getInt(cursor.getColumnIndex(BDCompetition.FOREIGN_DATABASE_ID)));


                competitions.add(competition);
            } while (cursor.moveToNext());
        }

        return competitions;
    }

    public List<BDCompetition> getCompetitionsByName(String name) {

        List<BDCompetition> competitions = new ArrayList<>();

        Cursor cursor = db.query(BDCompetition.TABLE_COMPETITION, new String[]{BDCompetition.KEY_NAME, BDCompetition.KEY_COORDINATES, BDCompetition.KEY_START_DATE,
                        BDCompetition.KEY_END_DATE, BDCompetition.KEY_TYPE_OF_COMPETITION, BDCompetition.KEY_ECHELONS, BDCompetition.KEY_SPECIALIZATIONS, BDCompetition.KEY_INFORMATION, BDCompetition.FOREIGN_DATABASE_ID},
                BDCompetition.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                BDCompetition competition = new BDCompetition();

                competition.setName(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_NAME)));
                competition.setCoordinates(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_COORDINATES)));
                competition.setStartDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_START_DATE)));
                competition.setEndDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_END_DATE)));
                competition.setTypeOfCompetition(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_TYPE_OF_COMPETITION)));
                competition.setEchelons(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_ECHELONS)));
                competition.setSpecializations(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_ECHELONS)));
                competition.setEchelons(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_SPECIALIZATIONS)));
                competition.setInformation(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_INFORMATION)));
                competition.setId(cursor.getInt(cursor.getColumnIndex(BDCompetition.FOREIGN_DATABASE_ID)));


                competitions.add(competition);
            } while (cursor.moveToNext());
        }

        return competitions;
    }

    public BDCompetition getCompetitionByID(int id) {

        BDCompetition competition = new BDCompetition();

        Cursor cursor = db.query(BDCompetition.TABLE_COMPETITION, new String[]{BDCompetition.KEY_NAME, BDCompetition.KEY_COORDINATES, BDCompetition.KEY_START_DATE,
                        BDCompetition.KEY_END_DATE, BDCompetition.KEY_TYPE_OF_COMPETITION, BDCompetition.KEY_ECHELONS, BDCompetition.KEY_SPECIALIZATIONS, BDCompetition.KEY_INFORMATION, BDCompetition.FOREIGN_DATABASE_ID},
                BDCompetition.FOREIGN_DATABASE_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                competition.setName(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_NAME)));
                competition.setCoordinates(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_COORDINATES)));
                competition.setStartDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_START_DATE)));
                competition.setEndDate(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_END_DATE)));
                competition.setTypeOfCompetition(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_TYPE_OF_COMPETITION)));
                competition.setEchelons(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_ECHELONS)));
                competition.setSpecializations(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_SPECIALIZATIONS)));
                competition.setInformation(cursor.getString(cursor.getColumnIndex(BDCompetition.KEY_INFORMATION)));
                competition.setId(cursor.getInt(cursor.getColumnIndex(BDCompetition.FOREIGN_DATABASE_ID)));

            } while (cursor.moveToNext());
        }

        return competition;
    }

    //BDWarmup
    public long insertWarmUp(BDWarmUp warmup) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDWarmUp.KEY_NAME, warmup.getName());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDWarmUp.TABLE_WARMUP, null, newValues);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDWarmUp> getWarmups() {
        List<BDWarmUp> warmups = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDWarmUp.TABLE_WARMUP, new String[]{BDWarmUp.KEY_ID, BDWarmUp.KEY_NAME},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDWarmUp warmup = new BDWarmUp();
                warmup.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUp.KEY_ID)));
                warmup.setName(cursor.getString(cursor.getColumnIndex(BDWarmUp.KEY_NAME)));
                warmups.add(warmup);
            } while (cursor.moveToNext());
        }

        return warmups;
    }

    public List<BDWarmUp> getWarmupsByName(String name) {

        List<BDWarmUp> warmups = new ArrayList<>();

        Cursor cursor = db.query(BDWarmUp.TABLE_WARMUP, new String[]{BDWarmUp.KEY_ID, BDCompetition.KEY_NAME},
                BDWarmUp.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                BDWarmUp warmup = new BDWarmUp();

                warmup.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUp.KEY_ID)));
                warmup.setName(cursor.getString(cursor.getColumnIndex(BDWarmUp.KEY_NAME)));


                warmups.add(warmup);
            } while (cursor.moveToNext());
        }

        return warmups;
    }

    public BDWarmUp getWarmupByID(int id) {

        BDWarmUp warmup = new BDWarmUp();

        Cursor cursor = db.query(BDWarmUp.TABLE_WARMUP, new String[]{BDWarmUp.KEY_ID, BDWarmUp.KEY_NAME},
                BDWarmUp.KEY_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                warmup.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUp.KEY_ID)));
                warmup.setName(cursor.getString(cursor.getColumnIndex(BDWarmUp.KEY_NAME)));

            } while (cursor.moveToNext());
        }

        return warmup;
    }

    public void deleteWarmup(int id){
        db = dbHelper.getWritableDatabase();
        db.delete(BDWarmUp.TABLE_WARMUP, "id = ?",new String[]{""+id});
        for(BDWarmUpExercise exercise : getWarmUpExercisesByWarmup(id)){
            deleteWarmUpExercise(exercise.getId());
        }
    }

    public void updateWarmup(int id, String name){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BDWarmUp.KEY_NAME,name);
        db.update(BDWarmUp.TABLE_WARMUP, cv, "id = ?", new String[]{""+id});
    }

    //BDExercise
    public long insertWarmUpExercise(BDWarmUpExercise exercise) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDWarmUpExercise.KEY_NAME, exercise.getName());
            int done = (exercise.isDone())? 1:0;
            newValues.put(BDWarmUpExercise.KEY_IS_DONE, done);
            newValues.put(BDWarmUpExercise.KEY_WARMUP_ID, exercise.getWarmupID());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, null, newValues);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public void deleteWarmUpExercise(int id){
        db = dbHelper.getWritableDatabase();
        db.delete(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, "id = ?",new String[]{""+id});
    }

    public void updateWarmUpExercise(int id, String name, boolean isDone){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BDWarmUpExercise.KEY_NAME,name);
        int done = (isDone)? 1:0;
        cv.put(BDWarmUpExercise.KEY_IS_DONE, done);
        db.update(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, cv, "id = ?", new String[]{""+id});
    }

    public List<BDWarmUpExercise> getWarmUpExercises() {
        List<BDWarmUpExercise> exercises = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, new String[]{BDWarmUpExercise.KEY_ID, BDWarmUpExercise.KEY_NAME,
                        BDWarmUpExercise.KEY_IS_DONE, BDWarmUpExercise.KEY_WARMUP_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDWarmUpExercise exercise = new BDWarmUpExercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(BDWarmUpExercise.KEY_NAME)));
                exercise.setDone(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_IS_DONE)));
                exercise.setWarmupID(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_WARMUP_ID)));
                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        return exercises;
    }

    public List<BDWarmUpExercise> getWarmUpExercisesByName(String name) {

        List<BDWarmUpExercise> exercises = new ArrayList<>();

        Cursor cursor = db.query(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, new String[]{BDWarmUpExercise.KEY_NAME,
                        BDWarmUpExercise.KEY_IS_DONE, BDWarmUpExercise.KEY_WARMUP_ID},
                BDWarmUpExercise.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                BDWarmUpExercise exercise = new BDWarmUpExercise();

                exercise.setName(cursor.getString(cursor.getColumnIndex(BDWarmUpExercise.KEY_NAME)));
                exercise.setDone(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_IS_DONE)));
                exercise.setWarmupID(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_WARMUP_ID)));


                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        return exercises;
    }

    public BDWarmUpExercise getWarmUpExercisesByID(int id) {

        BDWarmUpExercise exercise = new BDWarmUpExercise();

        Cursor cursor = db.query(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, new String[]{BDWarmUpExercise.KEY_ID, BDWarmUpExercise.KEY_NAME,
                        BDWarmUpExercise.KEY_IS_DONE, BDWarmUpExercise.KEY_WARMUP_ID},
                BDWarmUpExercise.KEY_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                exercise.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(BDWarmUpExercise.KEY_NAME)));
                exercise.setDone(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_IS_DONE)));
                exercise.setWarmupID(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_WARMUP_ID)));

            } while (cursor.moveToNext());
        }

        return exercise;
    }

    public List<BDWarmUpExercise> getWarmUpExercisesByWarmup(int warmupID) {
        List<BDWarmUpExercise> exercises = new ArrayList<>();

        Cursor cursor = db.query(BDWarmUpExercise.TABLE_WARM_UP_EXERCISE, new String[]{BDWarmUpExercise.KEY_ID, BDWarmUpExercise.KEY_NAME,
                        BDWarmUpExercise.KEY_IS_DONE, BDWarmUpExercise.KEY_WARMUP_ID},
                "warmupID = ?", new String[]{warmupID + ""}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDWarmUpExercise exercise = new BDWarmUpExercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(BDWarmUpExercise.KEY_NAME)));
                exercise.setDone(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_IS_DONE)));
                exercise.setWarmupID(cursor.getInt(cursor.getColumnIndex(BDWarmUpExercise.KEY_WARMUP_ID)));

                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        return exercises;
    }

    //BDTraining
    public long insertExercise(BDExercise training) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDExercise.KEY_ID,training.getId());
            newValues.put(BDExercise.KEY_NAME, training.getName());

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDExercise.TABLE_EXERCISE, null, newValues);
            training.setId((int) id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public List<BDExercise> getExercises() {
        List<BDExercise> exercises = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDExercise.TABLE_EXERCISE, new String[]{BDExercise.KEY_ID, BDExercise.KEY_NAME},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDExercise exercise = new BDExercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndex(BDExercise.KEY_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(BDExercise.KEY_NAME)));

                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        return exercises;
    }

    public List<BDExercise> getExercisesByName(String name) {

        List<BDExercise> trainings = new ArrayList<>();

        Cursor cursor = db.query(BDExercise.TABLE_EXERCISE, new String[]{BDExercise.KEY_ID, BDExercise.KEY_NAME},
                BDExercise.KEY_NAME + " like ?", new String[]{"%" + name + "%"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                BDExercise training = new BDExercise();

                training.setId(cursor.getInt(cursor.getColumnIndex(BDExercise.KEY_ID)));
                training.setName(cursor.getString(cursor.getColumnIndex(BDExercise.KEY_NAME)));


                trainings.add(training);
            } while (cursor.moveToNext());
        }

        return trainings;
    }

    public BDExercise getExerciseByID(int id) {

        BDExercise training = new BDExercise();

        Cursor cursor = db.query(BDExercise.TABLE_EXERCISE, new String[]{BDExercise.KEY_ID, BDExercise.KEY_NAME},
                BDExercise.KEY_ID + " = ?", new String[]{"" + id}, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                training.setId(cursor.getInt(cursor.getColumnIndex(BDExercise.KEY_ID)));
                training.setName(cursor.getString(cursor.getColumnIndex(BDExercise.KEY_NAME)));

            } while (cursor.moveToNext());
        }

        return training;
    }
}
