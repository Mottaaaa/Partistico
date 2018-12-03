package Model;

public class Athlete {

    //public nahSei icon;
    private String name;
    private String age;
    private String expirationDate;
    private String echelon;
    private String gender;

    public Athlete(/*nahSei icon*/ String name, String age, String expirationDate, String echelon, String gender) {
        this.name = name;
        this.age = age;
        this.expirationDate = expirationDate;
        this.echelon = echelon;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
