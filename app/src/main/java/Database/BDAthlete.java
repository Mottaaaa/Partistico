package Database;

import android.graphics.Bitmap;

import java.util.Date;

public class BDAthlete {

    public static final String TABLE_ATHLETE = "Athlete";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_EXPIRATION_DATE = "expirationDate";
    public static final String KEY_ECHELON = "echelon";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_HISTORY = "history";

    private Bitmap image;
    private String name;
    private String birthday;
    private String expirationDate;
    private String echelon;
    private String gender;
    private String notes;
    private String history;

    public static final String DATABASE_CREATE = "create table " + TABLE_ATHLETE +
            " (_id integer primary key autoincrement, " + KEY_IMAGE + " blob not null, " + KEY_NAME + " text not null, "
            + KEY_BIRTHDAY + " text not null, " + KEY_EXPIRATION_DATE + " text not null, "
            + KEY_ECHELON + " text not null, " + KEY_GENDER + " text not null, "
            + KEY_NOTES + " text not null, " + KEY_HISTORY + " text not null);";

    public BDAthlete(Bitmap image, String name, String birthday, String expirationDate, String echelon, String gender, String notes, String history) {
        this.image = image;
        this.name = name;
        this.birthday = birthday;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
        this.notes = notes;
        this.history = history;
    }

    public BDAthlete() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
}





