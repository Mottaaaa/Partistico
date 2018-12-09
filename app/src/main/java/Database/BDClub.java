package Database;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import Model.Utils;

public class BDClub implements Serializable {

    public static final String TABLE_CLUB = "Club";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String FOREIGN_DATABASE_ID = "foreignID";

    private int id;
    private String name;

    public static final String DATABASE_CREATE = "create table " + TABLE_CLUB +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, "
            + FOREIGN_DATABASE_ID + " integer not null);";

    public BDClub(int id, String name) {
        this.id = id;
        this.name = name;
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

}
