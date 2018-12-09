package Database;

import java.io.Serializable;

public class BDExercise implements Serializable {

    public static final String TABLE_EXERCISE = "Exercise";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    private int id;
    private String name;


    public static final String DATABASE_CREATE = "create table " + TABLE_EXERCISE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null);";

    public BDExercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BDExercise() {
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
