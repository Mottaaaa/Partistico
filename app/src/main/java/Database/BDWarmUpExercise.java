package Database;

import java.io.Serializable;

public class BDWarmUpExercise implements Serializable {

    public static final String TABLE_EXERCISE = "EXERCISE";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_REPETITIONS = "repetition";
    public static final String KEY_SERIES = "series";
    public static final String KEY_TIME = "time";
    public static final String KEY_WARMUP_ID = "warmupID";

    private int id;
    private String name;
    private String type;
    private String repetitions;
    private String series;
    private String time;
    private int warmupID;

    public static final String DATABASE_CREATE = "create table " + TABLE_EXERCISE +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, "
            + KEY_TYPE + " text not null, " + KEY_REPETITIONS + " text not null, "
            + KEY_SERIES + " text not null, "+ KEY_TIME + " text not null, " + KEY_WARMUP_ID + " integer not null);";


    public BDWarmUpExercise(String name, String type, String repetitions, String series, String time, int warmupID) {
        this.name = name;
        this.type = type;
        this.repetitions = repetitions;
        this.series = series;
        this.time = time;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWarmupID() {
        return warmupID;
    }

    public void setWarmupID(int warmupID) {
        this.warmupID = warmupID;
    }
}
