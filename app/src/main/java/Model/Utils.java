package Model;

public class Utils {

    private static Utils instance;

    private Athlete[] athletes;
    private Athlete activeAthlete;

    public Utils() {

        athletes = new Athlete[2];
        athletes[0] = new Athlete("Adolf Hitler","56","30 April 1945","Führer", "M");
        athletes[1] = new Athlete("Joseph Stalin","74","5 Março 1953","General Secretary","M");
    }

    public static Utils getInstance(){

        if (instance == null){
            instance = new Utils();
        }

        return instance;
    }

    public Athlete[] getAthletes() {
        return athletes;
    }

    public Athlete getActiveAthlete() {
        return activeAthlete;
    }

    public void setActiveAthlete(int activeAthleteIndex) {
        this.activeAthlete = athletes[activeAthleteIndex];
    }
}
