package Database;

public class BDCompetition {


    public static final String TABLE_COMPETITION = "Competition";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_START_DATE = "startDate";
    public static final String KEY_END_DATE = "endDate";
    public static final String KEY_TYPE_OF_COMPETITION = "typeOfCompetition";
    public static final String KEY_ECHELONS = "echelons";
    public static final String KEY_SPECIALIZATIONS = "specializations";
    public static final String KEY_INFORMATION = "information";
    public static final String FOREIGN_DATABASE_ID = "foreignID";

    private int id;
    private String name;
    private String address;
    private String startDate;
    private String endDate;
    private String typeOfCompetition;
    private String echelons;
    private String specializations;
    private String information;

    public static final String DATABASE_CREATE = "create table " + TABLE_COMPETITION +
            " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null, "
            + KEY_ADDRESS + " text not null, "+ KEY_START_DATE + " text not null, "
            + KEY_END_DATE + " text not null, " + KEY_TYPE_OF_COMPETITION + " text not null, "
            + KEY_ECHELONS + " text not null, " + KEY_SPECIALIZATIONS + " text not null,"
            + KEY_INFORMATION + " text not null, " + FOREIGN_DATABASE_ID + " integer not null);";

    public BDCompetition(int id, String name, String address, String startDate, String endDate, String typeOfCompetition, String echelons, String specializations, String information) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeOfCompetition = typeOfCompetition;
        this.echelons = echelons;
        this.specializations = specializations;
        this.information = information;
    }

    public BDCompetition() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTypeOfCompetition() {
        return typeOfCompetition;
    }

    public void setTypeOfCompetition(String typeOfCompetition) {
        this.typeOfCompetition = typeOfCompetition;
    }

    public String getEchelons() {
        return echelons;
    }

    public void setEchelons(String echelons) {
        this.echelons = echelons;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
