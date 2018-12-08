package Database;

import java.io.Serializable;

public class BDTraining implements Serializable {

    public static final String TABLE_TRAINING= "TRAINING";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    private int id;
    private String name;


    public static final String DATABASE_CREATE = "create table " + TABLE_TRAINING +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null);";

    public BDTraining(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BDTraining() {
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
