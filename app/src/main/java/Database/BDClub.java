package Database;

import android.graphics.Bitmap;

public class BDClub {

    public static final String TABLE_CLUB = "CLUB";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";

    private String name;
    private Bitmap image;

    public static final String DATABASE_CREATE = "create table " + TABLE_CLUB +
            " (_id integer primary key autoincrement, " + KEY_NAME + " text not null, " + KEY_IMAGE + " blob not null);";

    public BDClub(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public BDClub() {
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
}
