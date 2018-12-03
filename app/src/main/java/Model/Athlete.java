package Model;

public class Athlete {

    //public nahSei icon;
    public String name;
    public String age;
    public String expirationDate;
    public String echelon;

    public Athlete(/*nahSei icon*/ String name, String age, String expirationDate, String echelon) {
        this.name = name;
        this.age = age;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
