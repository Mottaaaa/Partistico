package Database;

import java.io.Serializable;

public class BDWarmUpExercise implements Serializable {

    public static final String TABLE_WARM_UP_EXERCISE = "WarmUpExercise";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IS_DONE = "isDone";

    public static final String KEY_WARMUP_ID = "warmupID";

    private int id;
    private String name;
    private boolean isDone;
    private int warmupID;

    public static final String DATABASE_CREATE = "create table " + TABLE_WARM_UP_EXERCISE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, "
            + KEY_IS_DONE + " integer not null, " + KEY_WARMUP_ID + " integer not null);";


    public BDWarmUpExercise(String name, int isDone, int warmupID) {
        this.name = name;
        this.isDone = (isDone==0)? false:true;
        this.warmupID = warmupID;
    }

    public BDWarmUpExercise() {
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(int done) {
        isDone = (done==0)? false:true;
    }

    public int getWarmupID() {
        return warmupID;
    }

    public void setWarmupID(int warmupID) {
        this.warmupID = warmupID;
    }
}
