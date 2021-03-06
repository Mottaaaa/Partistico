package Database;

import android.graphics.Bitmap;

import java.io.Serializable;

import Model.Utils;

public class BDNonAthlete implements Serializable {

    public static final String TABLE_NON_ATHLETE = "NonAthlete";
    public static final String KEY_ID = "id";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_ROLE = "role";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_HISTORY = "history";
    public static final String KEY_CLUB_ID = "club_id";
    public static final String FOREIGN_DATABASE_ID = "foreignID";

    private int id;
    private String image;
    private String name;
    private String birthday;
    private String role;
    private String gender;
    private String notes;
    private String history;
    private int clubID;

    public static final String DATABASE_CREATE = "create table " + TABLE_NON_ATHLETE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_IMAGE + " blob not null, " + KEY_NAME + " text not null, "
            + KEY_BIRTHDAY + " text not null, " + KEY_ROLE + " text not null, "
            + KEY_GENDER + " text not null, " + KEY_NOTES + " text not null, " + KEY_HISTORY + " text not null,"
            + KEY_CLUB_ID + " integer not null, " + FOREIGN_DATABASE_ID + " integer not null);";


    public BDNonAthlete(int id, Bitmap image, String name, String birthday, String role, String gender,String notes, String history, int clubID) {
        this.id = id;
        this.image = Utils.bitmapToString(image);
        this.name = name;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.notes = notes;
        this.history = history;
        this.clubID = clubID;
    }

    public BDNonAthlete(int id, String image, String name, String birthday, String role, String gender, String notes, String history, int clubID) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.notes = notes;
        this.history = history;
        this.clubID = clubID;
    }

    public BDNonAthlete(int id, String image, String name, String birthday, String role, String gender, String history, int clubID) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.history = history;
        this.clubID = clubID;
    }

    public BDNonAthlete() {
    }

    public BDNonAthlete(int id, String name, String birthday, String role, String gender, String history, int clubID) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.history = history;
        this.clubID = clubID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return Utils.stringToBitmap(image);
    }

    public void setImage(Bitmap image) {
        this.image = Utils.bitmapToString(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }
}
