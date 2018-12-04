package Model;

import android.graphics.Bitmap;

public class Athlete {

    private Bitmap image;
    private String name;
    private String birthday;
    private String expirationDate;
    private String echelon;
    private String gender;
    private String notes;
    private String history;

    public Athlete(String name, String birthday, String expirationDate, String echelon, String gender , String notes, String history) {
        this.name = name;
        this.birthday = birthday;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
        this.notes = notes;
        this.history = history;
    }

    public String getNotes(){ return notes; }

    public void setNotes(String notes){ this.notes = notes; }

    public String getHistory(){ return history; }

    public void setHistory(String history) { this.history = history; }

    public Bitmap getImage(){ return image; }

    public void setImage(Bitmap image){ this.image = image; }

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
}
