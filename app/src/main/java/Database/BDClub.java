package Database;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import Model.Utils;

public class BDClub {

    public static final String TABLE_CLUB = "CLUB";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String FOREIGN_DATABASE_ID = "foreignID";

    private int id;
    private String name;
    private String image;

    public static final String DATABASE_CREATE = "create table " + TABLE_CLUB +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, " + KEY_IMAGE + " blob not null, "
            + FOREIGN_DATABASE_ID + " integer not null);";

    public BDClub(int id, String name, Bitmap image) {
        this.id = id;
        this.name = name;
        this.image = Utils.bitmapToString(image);
    }


    public BDClub() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return Utils.stringToBitmap(this.image);
    }

    public void setImage(Bitmap image) {
        this.image = Utils.bitmapToString(image);
    }
}
