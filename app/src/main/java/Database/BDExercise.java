package Database;

import java.io.Serializable;

public class BDExercise implements Serializable {

    public static final String TABLE_EXERCISE = "EXERCISE";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_WARMUP_ID = "warmupID";

    private int id;
    private String name;
    private int warmupID;

    public static final String DATABASE_CREATE = "create table " + TABLE_EXERCISE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, "
            + KEY_WARMUP_ID + " integer not null);";

    public BDExercise(int id, String name, int warmupID) {
        this.id = id;
        this.name = name;
        this.warmupID = warmupID;
    }

    public BDExercise(String name, int warmupID) {
        this.name = name;
        this.warmupID = warmupID;
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

    public int getWarmupID() {
        return warmupID;
    }

    public void setWarmupID(int warmupID) {
        this.warmupID = warmupID;
    }
}
