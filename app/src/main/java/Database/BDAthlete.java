package Database;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

import Model.Utils;

public class BDAthlete implements Serializable {

    public static final String TABLE_ATHLETE = "Athlete";
    public static final String KEY_ID = "id";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_EXPIRATION_DATE = "expirationDate";
    public static final String KEY_ECHELON = "echelon";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_HISTORY = "history";
    public static final String KEY_CLUB_ID = "club_id";
    public static final String FOREIGN_DATABASE_ID = "foreignID";

    private int id;
    private String image;
    private String name;
    private String birthday;
    private String expirationDate;
    private String echelon;
    private String gender;
    private String history;
    private int clubID;

    public static final String DATABASE_CREATE = "create table " + TABLE_ATHLETE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_IMAGE + " blob not null, " + KEY_NAME + " text not null, "
            + KEY_BIRTHDAY + " text not null, " + KEY_EXPIRATION_DATE + " text not null, "
            + KEY_ECHELON + " text not null, " + KEY_GENDER + " text not null, "
            + KEY_HISTORY + " text not null," + KEY_CLUB_ID + " integer not null, "
            + FOREIGN_DATABASE_ID + " integer not null);";

    public BDAthlete(int id, Bitmap image, String name, String birthday, String expirationDate, String echelon, String gender, String history, int clubID) {
        this.id = id;
        this.image = Utils.bitmapToString(image);
        this.name = name;
        this.birthday = birthday;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
        this.history = history;
        this.clubID = clubID;
    }

    public BDAthlete(int id, String image, String name, String birthday, String expirationDate, String echelon, String gender, String history, int clubID) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birthday = birthday;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
        this.history = history;
        this.clubID = clubID;
    }

    public BDAthlete(int id, String name, String birthday, String expirationDate, String echelon, String gender, String history, int clubID) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
        this.history = history;
        this.clubID = clubID;
    }

    public BDAthlete() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return Utils.stringToBitmap(image);
    }

    public void setImage(Bitmap image) {
        this.image = Utils.bitmapToString(image);
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}





