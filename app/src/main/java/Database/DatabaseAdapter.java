package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    // Escrever abaixo desta linha para fazer os gets e sets à base de dados

    public long insertClub(BDClub club) {
        long id = -1;
        try {
            ContentValues newValues = new ContentValues();
            newValues.put(BDClub.KEY_NAME, club.getName());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            club.getImage().compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            newValues.put(BDClub.KEY_IMAGE, bArray);

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDClub.TABLE_CLUB, null, newValues);
        } catch (Exception ex) {
            System.out.println("Esceptions:" + ex);
        }
        return id;
    }

    public List<BDClub> getClubs() {
        List<BDClub> clubs = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDClub.TABLE_CLUB, new String[]{BDClub.KEY_NAME, BDClub.KEY_IMAGE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                BDClub club = new BDClub();
                club.setName(cursor.getString(cursor.getColumnIndex(BDClub.KEY_NAME)));
                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex(BDClub.KEY_IMAGE));
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                club.setImage(image);
                clubs.add(club);
            } while (cursor.moveToNext());
        }

        return clubs;
    }

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

            db = dbHelper.getWritableDatabase();
            id = db.insert(BDAthlete.TABLE_ATHLETE, null, newValues);
        } catch (Exception ex) {
            System.out.println("Esceptions:" + ex);
        }
        return id;
    }

    public List<BDAthlete> getAthletes() {
        List<BDAthlete> athletes = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().query(BDAthlete.TABLE_ATHLETE, new String[]{BDAthlete.KEY_NAME, BDAthlete.KEY_IMAGE,
                        BDAthlete.KEY_BIRTHDAY, BDAthlete.KEY_EXPIRATION_DATE, BDAthlete.KEY_ECHELON, BDAthlete.KEY_GENDER, BDAthlete.KEY_NOTES, BDAthlete.KEY_HISTORY},
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

                athletes.add(athelete);
            } while (cursor.moveToNext());
        }

        return athletes;
    }
}
